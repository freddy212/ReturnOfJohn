package com.mygdx.game.GameObjects.Gates

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.GameObjects.Other.GenericGameObject
import com.mygdx.game.Interfaces.AreaEntranceCollition
import com.mygdx.game.Interfaces.Collition
import com.mygdx.game.Interfaces.DefaultAreaEntranceCollition
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Timer.DefaultTimer
import com.mygdx.game.player
import com.mygdx.game.plus
import com.mygdx.game.renderRepeatedTexture

enum class FiregateState{INACTIVE, CLOSING, CLOSED}
class FireGate(initPosition: Vector2, size: Vector2, defaultLocation: DefaultLocation?) :
    GameObject(initPosition, size, defaultLocation) {
    override val texture = DefaultTextureHandler.getTexture("FiregatePillar.png")
    val brickTexture = DefaultTextureHandler.getTexture("FiregateBrick.png")
    override val layer = Layer.ONGROUND
    val maxTicks = ((size.x - 20) / 2)
    var counter = 0f;
    var firegateState: FiregateState = FiregateState.INACTIVE
    val timer = DefaultTimer(0.75f)

    val firegateCollitionObject = FiregateCollitionObject(this)
    init {
        firegateCollitionObject.addToLocation(defaultLocation!!)
    }

    override val collition = FiregateCollition(this)

    override fun frameTask() {
        super.frameTask()
        if(firegateState == FiregateState.CLOSING && timer.tryUseCooldown()){
            firegateState = FiregateState.CLOSED
        }
    }

    init {
        polygon.setScale(1f, 6.5f)
    }

        override fun render(batch: PolygonSpriteBatch) {
        //super.render(batch)
        batch.draw(texture, sprite.x + size.x - texture.width, sprite.y)
        batch.draw(texture, sprite.x, sprite.y)
        if(firegateState == FiregateState.CLOSING ){
            val currentStepLeft = counter % maxTicks
            renderRepeatedTexture(batch, brickTexture, Vector2(initPosition + Vector2(10f,0f)), Vector2(currentStepLeft, 100f))
            renderRepeatedTexture(batch, brickTexture, Vector2(initPosition + Vector2(this.width - 10f,0f)), Vector2(-currentStepLeft, 100f))
            counter = counter + 3
        }
        if(firegateState == FiregateState.CLOSED){
            renderRepeatedTexture(batch, brickTexture, Vector2(initPosition + Vector2(10f,0f)), Vector2(maxTicks, 100f))
            renderRepeatedTexture(batch, brickTexture, Vector2(initPosition + Vector2(this.width - 10f,0f)), Vector2(-maxTicks, 100f))
        }
    }
}

class FiregateCollition(val fireGate: FireGate): DefaultAreaEntranceCollition(){
    override fun movedInsideAction(objectEntered: GameObject) {
        if(objectEntered is Player){
            fireGate.firegateState = FiregateState.CLOSING
            fireGate.timer.reset()
            fireGate.timer.tryUseCooldown()
        }
    }

    override fun movedOutsideAction(objectLeaved: GameObject) {
        if(objectLeaved is Player){
            fireGate.firegateState = FiregateState.INACTIVE
            fireGate.counter = 0f
        }
    }

}

class FiregateCollitionObject(fireGate: FireGate) :
    GameObject(fireGate.initPosition, fireGate.size, fireGate.defaultLocation){
    override val texture = fireGate.texture
    override val layer = fireGate.layer

    override fun frameTask() {
    }

    override val collition = object: MoveCollition{
        override val canMoveAfterCollition: Boolean
            get() = fireGate.firegateState != FiregateState.CLOSED

        override fun collitionHappened(collidedObject: GameObject) {
            if(collidedObject is Player && !canMoveAfterCollition){
                val playerBottomDistance = (player.sprite.y + player.size.y / 2) - fireGate.y
                val playerTopDistance = fireGate.topleft.y - (player.sprite.y + player.size.y / 2)
                if(playerBottomDistance < playerTopDistance){
                    player.setPosition(Vector2(player.sprite.x, initPosition.y - player.size.y - 10f))
                }else{
                    player.setPosition(Vector2(player.sprite.x, fireGate.topleft.y + 10f))
                }
            }
        }

    }

}