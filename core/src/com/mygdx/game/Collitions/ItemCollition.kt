package com.mygdx.game.Collitions

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Interfaces.Collition
import com.mygdx.game.Interfaces.ItemObject
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.GameObjects.MoveableEntities.Player
import com.mygdx.game.Interfaces.MoveableEntity

object ItemCollition: Collition by CanMoveCollition {
    override fun collitionHappened(entity: MoveableEntity, collitionPosition: Vector2, collidedObject: GameObject) {
        if(entity is Player && collidedObject is ItemObject){
            val item = collidedObject.item
            entity.addToInventory(item)
            LocationManager.currentLocation.removeGameObject(collidedObject)
       }
    }
}