package com.mygdx.game.Interfaces

import com.mygdx.game.AbstractClasses.GameObject

interface AreaEntranceCollition: MoveCollition{
    var insideCollition: MutableMap<GameObject, Boolean>
    fun movedInside(objectEntered:GameObject)
    fun movedOutside(objectLeaved: GameObject)
    fun movedInsideAction(objectEntered: GameObject)
    fun movedOutsideAction(objectLeaved: GameObject)
}

abstract class DefaultAreaEntranceCollition(): AreaEntranceCollition{
    override val canMoveAfterCollition = true
    override var insideCollition: MutableMap<GameObject, Boolean> = mutableMapOf()

    override fun movedOutside(objectLeaved: GameObject){
        if(insideCollition.getOrDefault(objectLeaved,true)){
            insideCollition[objectLeaved] = false
            movedOutsideAction(objectLeaved)
        }
    }
    override fun movedInside(objectEntered: GameObject){
        if(! (insideCollition.getOrDefault(objectEntered,false))){
            insideCollition[objectEntered] = true
            movedInsideAction(objectEntered)
        }
    }

    override fun collitionHappened(collidedObject: GameObject) {
            movedInside(collidedObject)
    }
}