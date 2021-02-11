package com.mygdx.game.Collitions

import com.badlogic.gdx.Input
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Item
import com.mygdx.game.GameObjects.LockedDoor
import com.mygdx.game.GameObjects.MoveableEntities.Player
import com.mygdx.game.GameObjects.Sensors.KeySensor
import com.mygdx.game.Interfaces.KeyPressedCollition
import com.mygdx.game.player

class CheckKeyCollition(val lockedDoor: LockedDoor): KeyPressedCollition {
    override val specificButton = Input.Keys.SPACE

    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(entity is Player && collidedObject is KeySensor){
            if(player.inventory.getItemCount(Item.KEY) > 0 && player.direction == Direction.UP){
                player.inventory.useItems(Item.KEY,1)
                lockedDoor.removeFromLocation()
                collidedObject.removeFromLocation()
            }
        }
    }

}
