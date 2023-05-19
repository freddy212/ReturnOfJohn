package com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.Hydra

import EnemyCollition
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.*
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.EdgeOfLocationStrategies.MoveRegardless
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.Boss
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.GameObjects.MoveableEntities.IceClone
import com.mygdx.game.Interfaces.Collition
import com.mygdx.game.Interfaces.CollitionMask
import com.mygdx.game.Interfaces.MovementStrategy
import com.mygdx.game.ItemAbilities.Shield
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.handleMoveCollitions
import com.mygdx.game.rotate

class HydraHeadCollitionMask() : CollitionMask {
    override val canCollideWith: (GameObject) -> Boolean = { gameObject -> gameObject !is Boss }

}

class HydraEarthHead(initPosition: Vector2, defaultLocation: DefaultLocation?, val hydra: Hydra,val rotation: Float) :
    GameObject(initPosition, Vector2(37f, 97f), defaultLocation) {
    override val texture = DefaultTextureHandler.getTexture("HydraEarthHead.png")
    override val layer = Layer.AIR
    override val collitionMask = HydraHeadCollitionMask()
    val scaleModifier = 0.015f
    val rotateModifier = 3f
    var rotatingForward: Boolean = false

    override val collition = HydraEarthHeadCollition(hydra, this)

    init {
        sprite.setPosition(sprite.x + 55f, sprite.y + 110f)
        sprite.setOrigin(this.size.x / 2, 0f)
        polygon.setOrigin(sprite.x + sprite.originX, sprite.y + sprite.originY)
        this.rotate(rotation)
    }

        override fun frameTask() {
            super.frameTask()
            handleMoveCollitions(this, this.polygon, LocationManager.ActiveGameObjects)
    }

    fun rotateForward() {
        val scaleY = 1f + scaleModifier
        rotate(rotateModifier)
        sprite.setScale(1f, sprite.scaleY * scaleY)
        polygon.setScale(1f, sprite.scaleY * scaleY)
    }

    fun rotateBack() {
        val scaleY = 1f - scaleModifier
        rotate(-rotateModifier)
        sprite.setScale(1f, sprite.scaleY * scaleY)
        polygon.setScale(1f, sprite.scaleY * scaleY)
    }
}

class HydraEarthHeadCollition(enemy: Enemy, val hydraEarthHead: HydraEarthHead) : EnemyCollition(enemy) {

    override fun collitionHappened(collidedObject: GameObject) {
        super.collitionHappened(collidedObject)
        if (collidedObject is IceClone || collidedObject is Shield) {
            hydraEarthHead.rotatingForward = false
        }
        if (collidedObject is Projectile){
            collidedObject.removeFromLocation()
        }
    }
}