package com.mygdx.tests

import com.mygdx.game.ItemAbilities.WaterBallAbility
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
        itemAbility.tryUseAction()
        Assertions.assertEquals(2, location.gameObjects.size)
        Assertions.assertTrue(location.gameObjects[1] is WaterBall)
        itemAbility.tryUseAction()
        Assertions.assertEquals(2, location.gameObjects.size)
        Assertions.assertTrue(location.gameObjects[1] is WaterBall)
    }
}