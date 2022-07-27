package com.mygdx.tests

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Enums.CharacterState
import com.mygdx.game.player
import com.mygdx.game.plus
import com.mygdx.game.times
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class HitTest: TestGame() {
    @Test
    fun testHitBehaviour(){
     /*   assertEquals(CharacterState.FREE,player.characterState)

        var playerPosition = Vector2(player.sprite.x,player.sprite.y)
        val Right = Vector2(1f,0f)
        player.move(Right)
        assertEquals(playerPosition + (Right * player.currentSpeed), Vector2(player.sprite.x,player.sprite.y))

        playerPosition = Vector2(player.sprite.x,player.sprite.y)
        val speedBeforeHit = player.currentSpeed

        player.isHit(Vector2(1f,0f))

        assertTrue(player.currentSpeed > speedBeforeHit)
        assertEquals(playerPosition,Vector2(player.sprite.x, player.sprite.y))
        player.move(Right)
        assertEquals(playerPosition,Vector2(player.sprite.x, player.sprite.y))
       // assertEquals(playerPosition + (Right * player.currentSpeed), Vector2(player.sprite.x,player.sprite.y))
        assertTrue(player.characterState == CharacterState.STUNNED)

        while (player.currentSpeed != speedBeforeHit){
            assertTrue(player.currentSpeed > speedBeforeHit)
            playerPosition = Vector2(player.sprite.x,player.sprite.y)
            player.frameTask()
            assertEquals(playerPosition + (Right * player.currentSpeed), Vector2(player.sprite.x,player.sprite.y))
        }
        assertTrue(player.characterState == CharacterState.FREE)

        playerPosition = Vector2(player.sprite.x,player.sprite.y)

        player.move(Right)
        assertEquals(playerPosition + (Right * player.currentSpeed), Vector2(player.sprite.x,player.sprite.y))
        */
    }
}