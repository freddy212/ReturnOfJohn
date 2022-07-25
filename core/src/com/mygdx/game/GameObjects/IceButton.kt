package com.mygdx.game.GameObjects

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.GetCollidingObjects
import com.mygdx.game.Interfaces.Collition
import com.mygdx.game.Interfaces.EveryFrameCollition
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.handleCollitions
import com.mygdx.game.player

class IceButton(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation?, val objectToToggle:GameObject) :
    GameObject(Position, size, defaultLocation) {
    override val texture = DefaultTextureHandler.getTexture("GateButton.png")
    override val layer = Layer.ONGROUND

    /*override val collition = object: EveryFrameCollition {

        override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
            if((entity is Player || entity is IceClone) && objectToToggle in LocationManager.MoveCollitionGameObjects){
                objectToToggle.removeFromLocation()
            }
            else if((entity !is Player && entity !is IceClone) && objectToToggle !in LocationManager.MoveCollitionGameObjects){
                objectToToggle.defaultLocation?.addGameObject(objectToToggle)
            }
        }

    }*/

    override fun frameTask() {
        super.frameTask()
        val collitions = GetCollidingObjects(this.polygon, LocationManager.MoveCollitionGameObjects - this)
        val iceClone = collitions.find { it is IceClone }
        if((player in collitions || iceClone in collitions) && objectToToggle in LocationManager.MoveCollitionGameObjects){
            objectToToggle.removeFromLocation()
        }
        else if((player !in collitions && iceClone !in collitions) && objectToToggle !in LocationManager.MoveCollitionGameObjects){
            objectToToggle.addToLocation(defaultLocation!!)
        }
    }
}