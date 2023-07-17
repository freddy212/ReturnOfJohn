package com.mygdx.game.Collitions

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.Projectile
import com.mygdx.game.ItemAbilities.Shield
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.*
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.ObjectProperties.Fire
import com.mygdx.game.ObjectProperties.Ice
import com.mygdx.game.Trimer.DelayTimer

open class ShieldCollition(val shield: Shield) : MoveCollition {
    val delayMap = mutableMapOf<GameObject, DelayTimer>()

    override var canMoveAfterCollition = true
    val sound = Gdx.audio.newSound(Gdx.files.local("Sound/SoundEffect/woodhit.mp3"));
    override fun collitionHappened(collidedObject: GameObject) {
        canMoveAfterCollition = collidedObject is Boulder || collidedObject is SmallBoulder
        if ((collidedObject is Boulder || collidedObject is SmallBoulder)) {
            if (collidedObject in delayMap.keys) {
                val delayTimer = delayMap[collidedObject]!!
                if (delayTimer.getTimeHasPassed()) {
                    delayMap.remove(collidedObject)
                }
            } else {
                val fire: Fire? = collidedObject.properties.List.find { it is Fire } as Fire?
                if (fire == null) {
                    val delayTimer = DelayTimer(1f)
                    delayMap.put(collidedObject, delayTimer)
                    val centerBoulder = Vector2(
                        collidedObject.sprite.x + collidedObject.sprite.width / 2,
                        collidedObject.sprite.y + collidedObject.sprite.height
                    )
                    val centerShield =
                        Vector2(shield.sprite.x + shield.sprite.width / 2, shield.sprite.y + shield.sprite.height)
                    (collidedObject as Projectile).shooter = player
                    collidedObject.unitVectorDirection = getOppositeUnitVector(centerBoulder, centerShield)
                    sound.play(0.25f)
                } else {
                    BoulderCollition(collidedObject as Projectile).collitionHappened(player)
                }
            }
        }

        if(collidedObject is Fireball){
            BoulderCollition(collidedObject as Projectile).collitionHappened(player)
        }
    }

    override fun filterCollitions(gameObjects: List<GameObject>): List<GameObject> {
        return gameObjects.filter { it !is Player }
    }
}

class ShieldUpgradedCollition(shield: Shield) : ShieldCollition(shield) {
    override fun collitionHappened(collidedObject: GameObject) {
        canMoveAfterCollition = collidedObject is Boulder || collidedObject is SmallBoulder
        if ((collidedObject is Boulder || collidedObject is SmallBoulder)) {
            if (collidedObject in delayMap.keys) {
                val delayTimer = delayMap[collidedObject]!!
                if (delayTimer.getTimeHasPassed()) {
                    delayMap.remove(collidedObject)
                }
            } else {
                val delayTimer = DelayTimer(1f)
                delayMap.put(collidedObject, delayTimer)
                val centerBoulder = Vector2(
                    collidedObject.sprite.x + collidedObject.sprite.width / 2,
                    collidedObject.sprite.y + collidedObject.sprite.height
                )
                val centerShield =
                    Vector2(shield.sprite.x + shield.sprite.width / 2, shield.sprite.y + shield.sprite.height)
                (collidedObject as Projectile).shooter = player
                collidedObject.unitVectorDirection = getOppositeUnitVector(centerBoulder, centerShield)
                sound.play(0.25f)
            }
        }
    }
}