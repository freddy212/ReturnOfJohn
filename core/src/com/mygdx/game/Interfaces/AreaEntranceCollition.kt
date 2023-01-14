package com.mygdx.game.Interfaces

import com.mygdx.game.AbstractClasses.GameObject

interface AreaEntranceCollition: MoveCollition{
    var insideCollition: Boolean
    fun movedInside()
    fun movedOutside()
    fun movedInsideAction()
    fun movedOutsideAction()
}

abstract class DefaultAreaEntranceCollition(): AreaEntranceCollition{
    override val canMoveAfterCollition = true
    override var insideCollition = false

    override fun movedOutside(){
        if(insideCollition){
            insideCollition = false
            movedOutsideAction()
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