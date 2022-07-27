package com.mygdx.tests

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Enums.Direction
import com.mygdx.game.minus
import com.mygdx.game.player
import com.mygdx.game.plus
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MovementTest: TestGame() {
    val playerPos : Vector2
        get() = (Vector2(player.sprite.x,player.sprite.y))
    @Test
    fun testMovingInLocation(){
        var originalPosition = playerPos
        player.currentSpeed = 5f
        player.move(Direction.RIGHT)
        player.move(Direction.RIGHT)
        Assertions.assertEquals(originalPosition + Vector2(player.getCurrentSpeed() * 2,0f),playerPos)

        originalPosition = playerPos
        player.move(Direction.RIGHT)
        player.move(Direction.UP)
        player.move(Direction.LEFT)
        player.move(Direction.DOWN)
        Assertions.assertEquals(originalPosition,playerPos)

        originalPosition = playerPos
        player.move(Direction.LEFT)
        Assertions.assertEquals(originalPosition - Vector2(player.getCurrentSpeed(),0f),playerPos)

        player.move(Direction.RIGHT)
        Assertions.assertEquals(originalPosition,playerPos)
    }
}