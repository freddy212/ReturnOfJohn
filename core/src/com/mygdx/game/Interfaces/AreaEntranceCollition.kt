package com.mygdx.game.Interfaces

import com.mygdx.game.AbstractClasses.GameObject

interface AreaEntranceCollition: MoveCollition{
    var insideCollition: Boolean
    fun movedOutsideAction()
    fun movedInsideAction()
}

open class DefaultAreaEntranceCollition(): AreaEntranceCollition{
    override val canMoveAfterCollition = true
    override var insideCollition = false
    override fun movedOutsideAction() {
        insideCollition = false
    }

    override fun movedInsideAction() {
        insideCollition = true
    }

    override fun collitionHappened(collidedObject: GameObject) {
        if(!insideCollition){
            movedInsideAction()
        }
    }

}