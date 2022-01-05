package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Icicle
import com.mygdx.game.GameObjects.Thorns
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Managers.SignalManager
import com.mygdx.game.SaveHandling.FileHandler
import com.mygdx.game.Signal.SIGNALTYPE
import com.mygdx.game.Signal.Signal

class ThornsCollition: MoveCollition{
    override var canMoveAfterCollition = false

    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(entity is Icicle && collidedObject is Thorns){
            canMoveAfterCollition = true
            SignalManager.emitSignal(Signal( SIGNALTYPE.REMOVE_OBJECT,collidedObject.entityId))
        }
        if(entity is Player && collidedObject is Thorns){
            PlayerHitCollition().collitionHappened(entity,collidedObject)
        }
    }
}