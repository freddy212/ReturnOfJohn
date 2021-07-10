package com.mygdx.game.GameObjects

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GetCollidingObjects
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.handleCollitions
import com.mygdx.game.player

class IceButton(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation?, val objectToToggle:GameObject) :
    GameObject(Position, size, defaultLocation) {
    override val texture = DefaultTextureHandler.getTexture("GateButton.png")
    override val layer = Layer.ONGROUND

    override fun frameTask() {
        super.frameTask()
        val collitions = GetCollidingObjects(this.polygon, LocationManager.MoveCollitionGameObjects - this)
        if(player in collitions && objectToToggle in LocationManager.MoveCollitionGameObjects){
            objectToToggle.removeFromLocation()
        }
        else if(player !in collitions && objectToToggle !in LocationManager.MoveCollitionGameObjects){
            objectToToggle.defaultLocation?.addGameObject(objectToToggle)
        }
    }
}