package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Enums.Item
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Interfaces.ItemObject
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.GameObjects.MoveableEntities.Player
import com.mygdx.game.itemObjectAddToInventory
import com.mygdx.game.player
import java.io.FileWriter

object ItemCollition: MoveCollition by CanMoveCollition {
    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(entity is Player && collidedObject is ItemObject){
            val item = collidedObject.item
            itemObjectAddToInventory(item, collidedObject)
        }
    }
}