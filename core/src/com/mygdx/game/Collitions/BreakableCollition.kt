package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Boulder
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Rocket
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Managers.SignalManager
import com.mygdx.game.Saving.SaveStateEntity
import com.mygdx.game.Signal.Signals.RemoveObjectSignal

class BreakableCollition() : MoveCollition by IllegalMoveCollition {

    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(entity is Boulder || entity is Rocket){
            SignalManager.emitSignal(RemoveObjectSignal((collidedObject as SaveStateEntity).entityId))
        }
    }
}