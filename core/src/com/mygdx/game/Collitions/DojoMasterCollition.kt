package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Events.DojoEvent
import com.mygdx.game.ItemAbilities.Shield
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.Interfaces.MoveCollition

class DojoMasterCollition(val dojoEvent: DojoEvent): MoveCollition by CanMoveCollition {

    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(collidedObject is Player){
            dojoEvent.resetCounter()
        }
        if(collidedObject is Shield){
            entity.removeFromLocation()
            dojoEvent.blockedWithShield()
        }
    }
}