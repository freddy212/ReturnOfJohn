package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Interfaces.ItemObject
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.itemObjectAddToInventory

object ItemCollition: MoveCollition by CanMoveCollition {
    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(entity is Player && collidedObject is ItemObject){
            val item = collidedObject.itemType
            itemObjectAddToInventory(item, collidedObject)
        }
    }
}