package com.mygdx.game.AI.EnemyActions

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.mygdx.game.AbstractClasses.Enemy
import com.mygdx.game.Enums.CharacterState
import com.mygdx.game.Events.ActionAfterFramesEvent
import com.mygdx.game.Events.ActionBeforeFramesEvent
import com.mygdx.game.Interfaces.EnemyAction
import com.mygdx.game.Managers.EventManager
import com.mygdx.game.ObjectProperties.FireDashEffect
import com.mygdx.game.camera
import com.mygdx.game.getUnitVectorTowardsPoint

class Dash(val enemy: Enemy, val dashCondition: () -> Boolean = { true }) : EnemyAction() {

    val fireDashEffect = FireDashEffect(enemy)
    val dashFrames = 40
    val sound = Gdx.audio.newSound(Gdx.files.internal("Sound/SoundEffect/fireworks.mp3"));

    private var counter = 0
    override fun executeEnemyAction() {
        if (counter == 0) {
            enemy.setCurrentSpeed(enemy.getCurrentSpeed() * 3.0f)
            enemy.characterState = CharacterState.DASHING
            enemy.properties.add(fireDashEffect)
            fireDashEffect.start()
            sound.play(0.25f)
        }
        else{
            inDashAction()
        }
        counter++
    }

    override val shouldBlock = true
    override val framesToBlock = dashFrames

    fun inDashAction() {
        if (enemy.characterState == CharacterState.DASHING) {
            enemy.move((enemy.unitVectorDirection))
            enemy.setCurrentSpeed(enemy.getCurrentSpeed() - 0.2f)
        }
    }

    override fun condition(): Boolean {
        return dashCondition()
    }

    override val probability: Double
        get() = if(active) 1.0 else 0.01

    override fun cleanUp() {
        counter = 0
        if (enemy.characterState == CharacterState.DASHING) {
            enemy.setCurrentSpeed(enemy.baseSpeed)
            enemy.characterState = CharacterState.FREE
        }
        enemy.properties.remove(fireDashEffect)
        super.cleanUp()
    }
}