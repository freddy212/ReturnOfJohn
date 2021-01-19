package com.mygdx.game.Collitions

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.GameObjects.ItemAbilities.Shield
import com.mygdx.game.GameObjects.MoveableEntities.Boulder
import com.mygdx.game.GameObjects.MoveableEntities.Player
import com.mygdx.game.GameObjects.MoveableEntities.WaterGunSpray
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.ObjectProperties.Fire
import com.mygdx.game.Trimer.DelayTimer
import java.util.*

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
                    val centerBoulder = Vector2(entity.sprite.x + entity.sprite.width / 2, entity.sprite.y + entity.sprite.height)
                    val centerShield = Vector2(collidedObject.sprite.x + collidedObject.sprite.width / 2, collidedObject.sprite.y + collidedObject.sprite.height)
                    entity.directionGiven = getOppositeUnitVector(centerBoulder,centerShield)
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
