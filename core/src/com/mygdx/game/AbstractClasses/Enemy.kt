package com.mygdx.game.AbstractClasses

import EnemyCollition
import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.HealthStrategy.EnemyHealthStrategy
import com.mygdx.game.Interfaces.EnemyStrategy
import com.mygdx.game.Interfaces.HealthStrategy
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.AreaManager
import com.mygdx.game.setAggroIfShouldBeAggroed

abstract class Enemy(
    Position: Vector2,
    size: Vector2,
    var location: DefaultLocation?,
    val aggroRadius: Float,
) : DefaultCharacter(Position, size, location),
    AggroableEntity by DefaultAggroableEntity(){

    override val collition by lazy { EnemyCollition(this) }
    override val healthStrategy: HealthStrategy = EnemyHealthStrategy()
    abstract val enemyStrategy:EnemyStrategy


    init {
        this.onLocationEnterActions.add(::resetAggro)
        this.onLocationEnterActions.add(::resetHealth)
        this.onLocationEnterActions.add(::cleanUpAbilties)
    }

    override fun frameTask() {
        if (isAggroed()) {
            enemyStrategy.getActions(this).forEach {it.executeEnemyAction()}
        } else {
            val aggroCircle = Circle(this.sprite.x, this.sprite.y, aggroRadius)
            setAggroIfShouldBeAggroed(InsideCircle(aggroCircle), this)
        }
        super.frameTask()
    }

    override fun isHit(other: GameObject) {
        if(!isAggroed()) {
            setAggroed()
        }
        super.isHit(other)
    }
    fun cleanUpAbilties(){
        enemyStrategy.actionList.forEach { it.cleanUp() }
    }
    fun resetHealth() {
        health = maxHealth
    }

    override fun death() {
        this.removeFromLocation()
    }

}