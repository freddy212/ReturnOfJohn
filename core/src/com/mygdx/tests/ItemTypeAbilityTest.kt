package com.mygdx.tests

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.GameObjects.ItemAbilities.WaterBallAbility
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.WaterBall
import com.mygdx.game.Managers.LocationManager
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ItemTypeAbilityTest : TestGame() {
    private val itemAbility = WaterBallAbility()

    @Test
    fun TestWaterBall() {
        val location = LocationManager.newDefaultLocation
        Assertions.assertEquals(1, location.gameObjects.size)
        itemAbility.activeAction()
        Assertions.assertEquals(2, location.gameObjects.size)
        Assertions.assertTrue(location.gameObjects[1] is WaterBall)
        itemAbility.activeAction()
        Assertions.assertEquals(2, location.gameObjects.size)
        Assertions.assertTrue(location.gameObjects[1] is WaterBall)
    }
}