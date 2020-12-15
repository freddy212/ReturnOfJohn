package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Events.DojoEvent
import com.mygdx.game.GameObjects.ItemAbilities.Shield
import com.mygdx.game.GameObjects.MoveableEntities.Player
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.player

class DojoMasterCollition(val dojoEvent: DojoEvent): MoveCollition by IllegalMoveCollition {

    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(collidedObject is Player){
            player.die()
        }
        if(collidedObject is Shield){
            dojoEvent.blockedWithShield()
        }
    }
}