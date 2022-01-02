package com.mygdx.game.Collitions

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.Projectile
import com.mygdx.game.GameObjects.ItemAbilities.ShieldAbility
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Boulder
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.SmallBoulder
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.ObjectProperties.Fire
import com.mygdx.game.Trimer.DelayTimer

class ShieldCollition: MoveCollition{
    val delayMap = mutableMapOf<GameObject,DelayTimer>()

    override var canMoveAfterCollition = true
    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        canMoveAfterCollition = entity is Boulder || entity is SmallBoulder
        if((entity is Boulder || entity is SmallBoulder) && collidedObject is ShieldAbility){
            if(entity in delayMap.keys){
                val delayTimer = delayMap[entity]!!
                if(delayTimer.getTimeHasPassed()){
                    delayMap.remove(entity)
                }
            }else{
                val fire: Fire? = entity.properties.List.find {it is Fire} as Fire?
                if(fire == null) {
                    val delayTimer = DelayTimer(1f)
                    delayMap.put(entity, delayTimer)
                    val centerBoulder = Vector2(entity.sprite.x + entity.sprite.width / 2, entity.sprite.y + entity.sprite.height)
                    val centerShield = Vector2(collidedObject.sprite.x + collidedObject.sprite.width / 2, collidedObject.sprite.y + collidedObject.sprite.height)
                    (entity as Projectile).unitVectorDirection = getOppositeUnitVector(centerBoulder,centerShield)
                }else{
                    BoulderCollition().collitionHappened(entity, player)
                }
            }
        }
    }

    override fun filterCollitions(gameObjects: List<GameObject>): List<GameObject> {
        return gameObjects.filter {it !is Player }
    }
}
