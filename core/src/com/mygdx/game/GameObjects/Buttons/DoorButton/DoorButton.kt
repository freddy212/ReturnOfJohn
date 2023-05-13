package com.mygdx.game.GameObjects.Buttons.DoorButton

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Events.ButtonEvent
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Icicle
import com.mygdx.game.Interfaces.Button
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.SignalManager
import com.mygdx.game.Saving.DefaultSaveStateHandler
import com.mygdx.game.Saving.SaveStateEntity
import com.mygdx.game.Signal.Signals.ButtonAcceptedSignal

open class DoorButton(initPosition: Vector2, size: Vector2, defaultLocation: DefaultLocation?,val doorButtonEvent: ButtonEvent, val direction:Direction = Direction.UP) :
    GameObject(initPosition, size, defaultLocation), SaveStateEntity by DefaultSaveStateHandler(), Button {
    override var activated = false
    override val texture = DefaultTextureHandler.getTexture("DoorButton.png")
    override val layer = Layer.AIR
    override val polygon = Polygon()
    override val collition by lazy { DoorButtonCollition(this) }

    init {
        if(direction == Direction.UP){
            polygon.vertices = floatArrayOf(x, y, x + size.x, y, x + size.x, y - 20f, x, y - 20f)
        }
        else if (direction == Direction.DOWN){
            polygon.vertices = floatArrayOf(x, y + size.y, x + size.x, y + size.y, x + size.x, y + size.y + 20f, x, y + size.y + 20f)
        }
        doorButtonEvent.addButton(this)
    }

    override fun render(batch: PolygonSpriteBatch) {
        sprite.color = if(activated) Color.GREEN else Color.RED
        super.render(batch)
    }
    fun acceptButton(){
        this.activated = true
        SignalManager.emitSignal(ButtonAcceptedSignal(this.entityId))
        doorButtonEvent.execute()
    }
}

open class DoorButtonCollition(val doorButton: DoorButton): MoveCollition {
    override fun collitionHappened(collidedObject: GameObject) {
        if(collidedObject is Icicle && !doorButton.activated){
            doorButton.acceptButton()
        }
    }

    override val canMoveAfterCollition = true
}