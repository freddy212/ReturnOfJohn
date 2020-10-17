package com.mygdx.game.Managers

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.MoveableObject
import com.mygdx.game.LocationImpl

class MovableObjectManager {
    companion object{
        private val MoveableObjects: MutableList<MoveableObject> = mutableListOf()
        val moveableObjects : List<MoveableObject>
            get() = MoveableObjects.toList()
        fun addMoveableObject(moveableObject: MoveableObject){
            MoveableObjects.add(moveableObject)
        }
        fun removeMoveableObject(moveableObject: MoveableObject){
            MoveableObjects.remove(moveableObject)
        }
    }
}