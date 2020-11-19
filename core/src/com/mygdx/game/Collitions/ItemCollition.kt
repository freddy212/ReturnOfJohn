package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Interfaces.ItemObject
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.GameObjects.MoveableEntities.Player
import com.mygdx.game.Interfaces.DynamicEntity

object ItemCollition: MoveCollition by CanMoveCollition {
    override fun collitionHappened(entity: DynamicEntity, collidedObject: GameObject) {
        if(entity is Player && collidedObject is ItemObject){
            val item = collidedObject.item
            entity.addToInventory(item)
            LocationManager.oldLocation.removeGameObject(collidedObject)
       }
    }
}