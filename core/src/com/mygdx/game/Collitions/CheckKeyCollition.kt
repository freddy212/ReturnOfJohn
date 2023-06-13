package com.mygdx.game.Collitions

import com.badlogic.gdx.Input
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.ItemType
import com.mygdx.game.GameObjects.Gates.HydraGate
import com.mygdx.game.GameObjects.Gates.LockedDoor
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.Interfaces.KeyPressedCollition
import com.mygdx.game.Managers.SignalManager
import com.mygdx.game.Signal.Signals.RemoveObjectSignal
import com.mygdx.game.Signal.Signals.UseItemsSignal
import com.mygdx.game.player

class CheckKeyCollition(val lockedDoor: HydraGate) : KeyPressedCollition() {
    override val specificButton = Input.Keys.SPACE

    override fun collitionHappened(collidedObject: GameObject) {
        if (collidedObject is Player) {
            if (player.inventory.getItemCount(ItemType.ICELANDSKEY) > 0 && player.inventory.getItemCount(ItemType.FIRELANDSKEY) > 0 && player.inventory.getItemCount(
                    ItemType.WASTELANDSKEY
                ) > 0
            ) {
                SignalManager.emitSignal(UseItemsSignal(ItemType.ICELANDSKEY, 1))
                SignalManager.emitSignal(UseItemsSignal(ItemType.FIRELANDSKEY, 1))
                SignalManager.emitSignal(UseItemsSignal(ItemType.WASTELANDSKEY, 1))
                SignalManager.emitSignal(RemoveObjectSignal(lockedDoor.entityId))
            }
        }
    }

    override fun renderKeyToUI(entity: GameObject, collidedObject: GameObject) {
        if (player.inventory.getItemCount(ItemType.ICELANDSKEY) > 0 && player.inventory.getItemCount(ItemType.FIRELANDSKEY) > 0 && player.inventory.getItemCount(
                ItemType.WASTELANDSKEY
            ) > 0
        ) {
            super.renderKeyToUI(entity, collidedObject)
        }
    }

}
