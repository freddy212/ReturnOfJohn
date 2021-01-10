package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.GameObjects.ItemAbilities.Shield
import com.mygdx.game.GameObjects.MoveableEntities.Boulder
import com.mygdx.game.GameObjects.MoveableEntities.Player
import com.mygdx.game.GameObjects.MoveableEntities.WaterGunSpray
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.ObjectProperties.Fire
import com.mygdx.game.Trimer.DelayTimer
import com.mygdx.game.checkOpposingDirections
import com.mygdx.game.getOpposingDirection
import com.mygdx.game.player

class ShieldCollition: MoveCollition by CanMoveCollition{
    val boulderDelay = mutableMapOf<Boulder,DelayTimer>()
    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(entity is Boulder && collidedObject is Shield){
            if(entity in boulderDelay.keys){
                val delayTimer = boulderDelay[entity]!!
                if(delayTimer.getTimeHasPassed()){
                    boulderDelay.remove(entity)
                }
            }else{
                val fire: Fire? = entity.properties.List.find {it is Fire} as Fire?
                if(fire == null) {
                    val delayTimer = DelayTimer(1f)
                    boulderDelay.put(entity, delayTimer)
                    entity.direction = getOpposingDirection(entity.direction)
                }else{
                    player.die()
                }
            }
        }
    }

    override fun filterCollitions(gameObjects: List<GameObject>): List<GameObject> {
        return gameObjects.filter {it !is Player }
    }
}