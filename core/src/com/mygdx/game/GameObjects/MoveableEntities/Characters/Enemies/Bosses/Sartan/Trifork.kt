package com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.Sartan

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.*
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.EdgeOfLocationStrategies.NoAction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.Boss
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.SandGhost.Sartan
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.GameObjects.MoveableEntities.IceClone
import com.mygdx.game.Interfaces.CollitionMask
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.ItemAbilities.Shield
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.plus

class TriforkCollitionMask: CollitionMask {
    override val canCollideWith: (GameObject) -> Boolean = {it is Player || it is Shield || it is IceClone}
}

class Trifork(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation?, private val sartan: Sartan) :
    MoveableObject(Position, size, defaultLocation), RotationalObject by DefaultRotationalObject() {
    override var baseSpeed = 8f
    override val movementStrategy = DefaultMovement(NoAction())
    override var unitVectorDirection = Vector2(0f, 0f)
    override val texture = DefaultTextureHandler.getTexture("Trifork.png")
    override val layer = Layer.ONGROUND
    override val collition = TriforkCollision(this)
    var neutralState = true;
    var movingTowards = false;
    override val collitionMask = TriforkCollitionMask()

    val origOrigin = Vector2(sartan.sprite.originX, sartan.sprite.originY + 30f);

    init {
        sprite.setOrigin(origOrigin.x, origOrigin.y)
        polygon.setOrigin(sprite.x + sprite.originX, sprite.y + sprite.originY)
        this.setCurrentSpeed(sartan.getCurrentSpeed())
    }

    override fun frameTask() {
        if (neutralState) {
            this.setPosition(sartan.currentPosition() + Vector2(0f, -30f))
            this.unitVectorDirection = sartan.unitVectorDirection
            this.setRotation(unitVectorDirection, this, 90f)
        }
        super.frameTask()
    }

}

class TriforkCollision(val trifork: Trifork) : MoveCollition {
    override fun collitionHappened(collidedObject: GameObject) {
        if (collidedObject is Player) {
            trifork.movingTowards = false
            collidedObject.isHit(trifork)
        }
        if (collidedObject is Shield) {
            trifork.movingTowards = false
        }
        if (collidedObject is IceClone){
            collidedObject.removeFromLocation()
            trifork.movingTowards = false
        }
    }

    override val canMoveAfterCollition = true
}