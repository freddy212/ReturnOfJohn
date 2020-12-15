package com.mygdx.game.Events

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.Location
import com.mygdx.game.Enums.Direction
import com.mygdx.game.GameObjects.MoveableEntities.DojoAttackObject
import com.mygdx.game.Interfaces.Event
import com.mygdx.game.Interfaces.Timer
import com.mygdx.game.LocationImpl
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Trimer.DelayTimer
import com.mygdx.game.player
import com.mygdx.game.plus
import kotlin.random.Random

class DojoEvent(val location: LocationImpl,val timer: Timer):Event{
    var counter = 0
    override fun execute() {
        val offset = 300f
            if (timer.tryUseCooldown()) {
                location.gameObjects.filter { it is DojoAttackObject }.forEach{location.removeGameObject(it)}
                val attackAngle = Random.nextInt(4)
                val StartInfo: Pair<Direction, Vector2> = when (attackAngle) {
                    0 -> Pair(Direction.RIGHT, Vector2(-offset, player.size.y / 2))
                    1 -> Pair(Direction.DOWN, Vector2(player.size.x / 2, offset))
                    2 -> Pair(Direction.LEFT, Vector2(offset, player.size.y / 2f))
                    else -> Pair(Direction.UP, Vector2(player.size.x / 2, -offset))
                }
                val dojoAttack = DojoAttackObject(StartInfo.first, StartInfo.second + (Vector2(player.sprite.x, player.sprite.y)), location)
                location.addGameObject(dojoAttack)
            }
    }

    fun blockedWithShield(){
        counter++
    }

}
