package com.mygdx.tests

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Enums.Direction
import com.mygdx.game.GameObjects.MoveableEntities.Player
import com.mygdx.game.minus
import com.mygdx.game.plus
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MovementTest: TestGame() {
    val playerPos : Vector2
        get() = (Vector2(player.polygon.x,player.polygon.y))
    @Test
    fun testMovingInLocation(){
        var originalPosition = playerPos
        Assertions.assertEquals(Vector2(player.sprite.x,player.sprite.y),playerPos)

        player.move(Direction.RIGHT)
        player.move(Direction.RIGHT)
        Assertions.assertEquals(originalPosition + Vector2(player.speed * 2,0f),playerPos)

        originalPosition = playerPos
        player.move(Direction.RIGHT)
        player.move(Direction.UP)
        player.move(Direction.LEFT)
        player.move(Direction.DOWN)
        Assertions.assertEquals(originalPosition,playerPos)

        player.setPosition(Vector2(location.bottomright.x - player.size.x,location.bottomright.y))
        originalPosition = playerPos
        player.move(Direction.LEFT)
        Assertions.assertEquals(originalPosition - Vector2(player.speed,0f),playerPos)

        player.move(Direction.RIGHT)
        Assertions.assertEquals(originalPosition,playerPos)

        player.move(Direction.RIGHT)
        Assertions.assertEquals(originalPosition,playerPos)
    }
}