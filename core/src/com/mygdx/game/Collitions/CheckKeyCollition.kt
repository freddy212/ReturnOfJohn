package com.mygdx.game.Collitions

import com.badlogic.gdx.Input
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.ItemType
import com.mygdx.game.GameObjects.LockedDoor
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.GameObjects.Sensors.KeySensor
import com.mygdx.game.Interfaces.KeyPressedCollition
import com.mygdx.game.Managers.SignalManager
import com.mygdx.game.Signal.Signal
import com.mygdx.game.Signal.Signals.RemoveObjectSignal
import com.mygdx.game.Signal.Signals.UseItemsSignal
import com.mygdx.game.player

class CheckKeyCollition(val lockedDoor: LockedDoor): KeyPressedCollition() {
    override val specificButton = Input.Keys.SPACE

    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(entity is Player && collidedObject is KeySensor){
            if(player.inventory.getItemCount(ItemType.KEY) > 0 && player.direction == Direction.UP){

                SignalManager.emitSignal(UseItemsSignal(ItemType.KEY,1))
                SignalManager.emitSignal(RemoveObjectSignal(lockedDoor.entityId))
            }
        }
    }

}
