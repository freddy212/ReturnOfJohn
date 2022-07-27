package com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.Sartan

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.*
import com.mygdx.game.Collitions.DefaultProjectileCollition
import com.mygdx.game.Collitions.PlayerHitCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.EdgeOfLocationStrategies.MoveRegardless
import com.mygdx.game.EdgeOfLocationStrategies.NoAction
import com.mygdx.game.EdgeOfLocationStrategies.RemoveObject
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Enums.getDirectionUnitVector
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.Boss
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.SandGhost.Sartan
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.Interfaces.FightableEntity
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Interfaces.MovementStrategy
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.plus

class Trifork(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation?, private val sartan: Sartan) :
    MoveableObject(Position, size, defaultLocation), RotationalObject by DefaultRotationalObject() {
    override var baseSpeed = 8f
    override val movementStrategy = DefaultMovement(NoAction())
    override var unitVectorDirection = Vector2(0f,0f)
    override val texture = DefaultTextureHandler.getTexture("Trifork.png")
    override val layer = Layer.ONGROUND
    override val collition = TriforkCollision()
    var neutralState = true;

    val origOrigin = Vector2(sartan.sprite.originX, sartan.sprite.originY + 30f);
    init {
        sprite.setOrigin(origOrigin.x, origOrigin.y)
        polygon.setOrigin(sprite.x + sprite.originX, sprite.y + sprite.originY)
        this.setCurrentSpeed(sartan.getCurrentSpeed())
    }

    override fun frameTask(){
        if(neutralState) {
            this.setPosition(sartan.currentPosition() + Vector2(0f, -30f))
            this.unitVectorDirection = sartan.unitVectorDirection
            this.setRotation(unitVectorDirection, this, 90f)
        }
        super.frameTask()
    }

}

class TriforkCollision: MoveCollition {
    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(collidedObject is Player){
            collidedObject.HitAction(entity,collidedObject)
        }
        if(entity is Player){
            entity.HitAction(collidedObject,entity)
        }
    }

    override fun filterCollitions(gameObjects: List<GameObject>): List<GameObject> {
        val objects = gameObjects.filterIsInstance<Boss>()
        return gameObjects.minus(objects.toSet())
    }

    override val canMoveAfterCollition = true
}