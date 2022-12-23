package com.mygdx.tests

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.DefaultEnemyStrategy
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Mobs.SmallDevil
import com.mygdx.game.Interfaces.EnemyAction
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class ChooseEnemyActionTest: TestGame() {

    private val enemy = SmallDevil(Vector2(0f, 0f), Vector2(0f, 0f), location)
    private val enemyAction = mock<EnemyAction>()
    private val defaultEnemyStrategy = DefaultEnemyStrategy(listOf(enemyAction))

    @Test
    fun TestOnlyValidActionsCanBeChosen() {
        //Given
        Mockito.`when`(enemyAction.condition()).thenReturn(true)
        assertEquals(1, defaultEnemyStrategy.actionList.size)
        //When
        var action = defaultEnemyStrategy.getActions(enemy)
        //Then
        assertEquals(enemyAction,action)

        //Given
        Mockito.`when`(enemyAction.condition()).thenReturn(false)
        //When
        action = defaultEnemyStrategy.getActions(enemy)
        //Then
        assertEquals(null,action)


    }
}