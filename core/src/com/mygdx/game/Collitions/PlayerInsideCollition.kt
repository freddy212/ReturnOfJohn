package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.GameObjects.Sensors.PlayerInsideSensor
import com.mygdx.game.Interfaces.MoveCollition

class PlayerInsideCollition(): MoveCollition by CanMoveCollition {
    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(entity is Player && collidedObject is PlayerInsideSensor){
            collidedObject.playerInside = true
        }
    }
}