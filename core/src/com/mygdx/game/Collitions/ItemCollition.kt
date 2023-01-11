package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Interfaces.ItemObject
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.itemObjectAddToInventory

open class ItemCollition(val gameObject: GameObject): MoveCollition by CanMoveCollition{
    override fun collitionHappened(collidedObject: GameObject) {
        if(collidedObject is Player){
            val item = (gameObject as ItemObject).itemType
            itemObjectAddToInventory(item, gameObject)
        }
    }
}