package com.mygdx.game.GameObjects

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.DoorButtonEvent
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Icicle
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.SignalManager
import com.mygdx.game.SaveState.DefaultSaveStateHandler
import com.mygdx.game.SaveState.SaveStateEntity
import com.mygdx.game.Signal.SIGNALTYPE
import com.mygdx.game.Signal.Signal

class DoorButton(initPosition: Vector2, size: Vector2, defaultLocation: DefaultLocation?, doorButtonEvent: DoorButtonEvent) :
    GameObject(initPosition, size, defaultLocation), SaveStateEntity by DefaultSaveStateHandler() {
    var activated = false
    override val texture = DefaultTextureHandler.getTexture("DoorButton.png")
    override val layer = Layer.AIR
    override val polygon = Polygon()
    override val collition = DoorButtonCollition(doorButtonEvent)

    init {
        polygon.vertices = floatArrayOf(x, y, x + size.x, y, x + size.x, y - 20f, x, y - 20f)
        doorButtonEvent.addButton(this)
    }

    override fun render(batch: PolygonSpriteBatch) {
        sprite.color = if(activated) Color.GREEN else Color.RED
        super.render(batch)
    }
}

class DoorButtonCollition(val doorButtonEvent: DoorButtonEvent): MoveCollition {
    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(entity is Icicle && collidedObject is DoorButton && !collidedObject.activated){
            collidedObject.activated = true
            SignalManager.emitSignal(Signal(SIGNALTYPE.BUTTON_ACCEPTED, collidedObject.entityId))
            doorButtonEvent.execute()
            entity.removeFromLocation()
        }
    }

    override val canMoveAfterCollition = true
}