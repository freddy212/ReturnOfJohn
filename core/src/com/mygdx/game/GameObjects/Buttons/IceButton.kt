package com.mygdx.game.GameObjects.Buttons

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Events.ButtonEvent
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.GameObjects.MoveableEntities.IceClone
import com.mygdx.game.GetCollidingObjects
import com.mygdx.game.Interfaces.*
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Saving.DefaultSaveStateHandler
import com.mygdx.game.Saving.SaveStateEntity


class IceButton(
    Position: Vector2,
    size: Vector2,
    defaultLocation: DefaultLocation?,
    val iceGate: ButtonGate,
    val iceButtonEvent: ButtonEvent
) :
    GameObject(Position, size, defaultLocation), Button, SaveStateEntity by DefaultSaveStateHandler() {
    override val texture = DefaultTextureHandler.getTexture("GateButton.png")
    override val layer = Layer.ONGROUND
    override var activated = false
    override val collition = IceButtonCollition(this, iceGate, iceButtonEvent)

    init {
        iceButtonEvent.addButton(this)
        polygon.setScale(0.5f, 1f)
    }

    override fun render(batch: PolygonSpriteBatch) {
        sprite.color = if (activated) Color.GREEN else Color.RED
        super.render(batch)
    }

    //PERFORMANCE!! THIS CAN BE OPTIMISED
    //OPTIMIZING TIME
}

class IceButtonCollition(val iceButton: IceButton, val gate: ButtonGate, val event: ButtonEvent) :
    DefaultAreaEntranceCollition() {
    override val canMoveAfterCollition = true

    override fun collitionHappened(collidedObject: GameObject) {
        if (collidedObject is Player || collidedObject is IceClone) {
            super.collitionHappened(collidedObject)
        }
    }

    override fun movedInsideAction(objectEntered: GameObject) {
        if (!event.emitWhenActivated || !event.isAllButtonsActivated()) {
            iceButton.activated = true
            gate.buttonPressed()
            event.execute()
        }
    }

    override fun movedOutside(objectLeaved: GameObject) {
        if(objectLeaved is Player || objectLeaved is IceClone){
            val collidingObjects = GetCollidingObjects(iceButton, iceButton.polygon, LocationManager.MoveCollitionGameObjects)
            val playerOrIceclone = collidingObjects.filter{it is Player || it is IceClone}.minus(objectLeaved)
            if(playerOrIceclone.isEmpty()){
                super.movedOutside(objectLeaved)
            }
            if(insideCollition.getOrDefault(objectLeaved,true)) {
                insideCollition[objectLeaved] = false
            }
        }
    }

    override fun movedOutsideAction(objectLeaved: GameObject) {
        if (!event.emitWhenActivated || !event.isAllButtonsActivated()) {
            iceButton.activated = false
            gate.buttonReleased()
        }
    }

}