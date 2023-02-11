package com.mygdx.game.Interfaces

import com.mygdx.game.AbstractClasses.GameObject

interface AreaEntranceCollition: MoveCollition{
    var insideCollition: Boolean
    fun movedInside()
    fun movedOutside(objectLeaved: GameObject)
    fun movedInsideAction()
    fun movedOutsideAction(objectLeaved: GameObject)
}

abstract class DefaultAreaEntranceCollition(): AreaEntranceCollition{
    override val canMoveAfterCollition = true
    override var insideCollition = false

    override fun movedOutside(objectLeaved: GameObject){
        if(insideCollition){
            insideCollition = false
            movedOutsideAction(objectLeaved)
        }
    }
    override fun movedInside(){
        if(!insideCollition){
            insideCollition = true
            movedInsideAction()
        }
    }

    override fun collitionHappened(collidedObject: GameObject) {
            movedInside()
    }
}