package com.mygdx.game.GameObjects.Sensors

import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.ItemType
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.Gates.LockedDoor
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.Interfaces.*
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.EventManager
import com.mygdx.game.Managers.SignalManager
import com.mygdx.game.Managers.UIRendererManager
import com.mygdx.game.Signal.Signals.RemoveObjectSignal
import com.mygdx.game.Signal.Signals.UseItemsSignal
import com.mygdx.game.UI.Sign.SignText
import com.mygdx.game.Utils.RenderGraph
import com.mygdx.game.player

class SignSensor(initPosition: Vector2, size: Vector2, defaultLocation: DefaultLocation?, text: String, text2: String? = null) :
    Sensor(initPosition, size, defaultLocation) {

    val signText = SignText(text, text2)

    val renderSignTextEvent = object: Event {
        override fun execute() {
            UIRendererManager.addToUIGraph(signText)
        }
    }

    override val collition = SignSensorCollition(text, renderSignTextEvent)
    override val collitionMask = OnlyPlayerCollitionMask
}

class SignSensorCollition(val text: String,val renderSignTextEvent: Event) : DefaultAreaEntranceCollition() {

    override fun movedInsideAction(objectEntered: GameObject) {
        EventManager.eventManager.add(renderSignTextEvent)
    }

    override fun movedOutsideAction(objectLeaved: GameObject) {
        EventManager.eventManager.remove(renderSignTextEvent)
    }

}