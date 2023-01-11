package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Boulder
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Rocket
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Managers.SignalManager
import com.mygdx.game.Saving.SaveStateEntity
import com.mygdx.game.Signal.Signals.RemoveObjectSignal

class BreakableCollition(val saveStateObject: SaveStateEntity): MoveCollition by IllegalMoveCollition {

    override fun collitionHappened(collidedObject: GameObject) {
        if(collidedObject is Boulder || collidedObject is Rocket){
            SignalManager.emitSignal(RemoveObjectSignal(saveStateObject.entityId))
        }
    }
}