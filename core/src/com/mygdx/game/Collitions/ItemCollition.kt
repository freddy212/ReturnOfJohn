package com.mygdx.game.Collitions

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.MoveableEntity
import com.mygdx.game.Interfaces.Collition
import com.mygdx.game.Interfaces.ItemObject
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Player

object ItemCollition: Collition {
    override fun collitionHappened(entity: MoveableEntity, collitionPosition: Vector2,collidedObject: GameObject) {
        if(entity is Player && collidedObject is ItemObject){
            val item = collidedObject.item
            entity.addToInventory(item)
            LocationManager.currentLocation.removeGameObject(collidedObject)
       }
    }
}