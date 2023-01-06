package com.mygdx.game.GameObjects.Other

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Events.ButtonEvent
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.MoveableEntities.IceClone
import com.mygdx.game.GetCollidingObjects
import com.mygdx.game.Interfaces.Button
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Saving.DefaultSaveStateHandler
import com.mygdx.game.Saving.SaveStateEntity
import com.mygdx.game.player



class IceButton(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation?, val iceGate:IceGate, val iceButtonEvent: ButtonEvent) :
    GameObject(Position, size, defaultLocation), Button , SaveStateEntity by DefaultSaveStateHandler(){
    override val texture = DefaultTextureHandler.getTexture("GateButton.png")
    override val layer = Layer.ONGROUND
    override var activated = false

    init {
        iceButtonEvent.addButton(this)
    }

    override fun render(batch: PolygonSpriteBatch) {
        sprite.color = if(activated) Color.GREEN else Color.RED
        super.render(batch)
    }

    //PERFORMANCE!! THIS CAN BE OPTIMISED
    override fun frameTask() {
        super.frameTask()
        if(!iceButtonEvent.isAllButtonsActivated()){
            val collitions = GetCollidingObjects(this.polygon, LocationManager.MoveCollitionGameObjects - this)
            val iceClone = collitions.find { it is IceClone }
            if((player in collitions || iceClone in collitions)){
                activated = true
                iceGate.openHalfWay()
                iceButtonEvent.execute()
            }
            else if((player !in collitions && iceClone !in collitions)){
                iceGate.reset()
                activated = false
            }
        }
    }
}