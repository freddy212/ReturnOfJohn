package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.GameObjects.Other.Axe
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Icicle
import com.mygdx.game.GameObjects.Other.Thorns
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Managers.SignalManager
import com.mygdx.game.Signal.Signals.RemoveObjectSignal

class ThornsCollition(val thorns: Thorns) : MoveCollition {
    override var canMoveAfterCollition = false

    override fun collitionHappened(collidedObject: GameObject) {
        if (collidedObject is Icicle || collidedObject is Axe) {
            canMoveAfterCollition = true
            SignalManager.emitSignal(RemoveObjectSignal(thorns.entityId))
        }
        if (collidedObject is Player) {
            collidedObject.isHit(thorns)
        }
    }
}