package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Collitions.PlayerHitCollition
import com.mygdx.game.HealthStrategy.EnemyHealthStrategy
import com.mygdx.game.Interfaces.EnemyStrategy
import com.mygdx.game.Interfaces.HealthStrategy
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.AreaManager

abstract class Enemy(
    Position: Vector2,
    size: Vector2,
    var location: DefaultLocation?,
    val aggroRadius: Float,
    collition:MoveCollition = PlayerHitCollition()
) : DefaultCharacter(Position, size, location){

    private var aggroed = false
    override val collition = collition
    override val healthStrategy: HealthStrategy = EnemyHealthStrategy()
    abstract val enemyStrategy:EnemyStrategy

    init {
        this.onLocationExitActions.add(::changeLocation)
        this.onLocationEnterActions.add(::resetAggro)
        this.onLocationEnterActions.add(::resetHealth)
        this.onLocationEnterActions.add(::cleanUpAbilties)
    }

    override fun frameTask() {
        if (aggroed) {
            enemyStrategy.getActions(this).forEach {it.executeEnemyAction(this)}
        } else {
            val aggroCircle = Circle(this.sprite.x, this.sprite.y, aggroRadius)
            checkAggroed(InsideCircle(aggroCircle))
        }
        super.frameTask()
    }

    override fun isHit(launchUnitVector: Vector2) {
        setAggroed()
        super.isHit(launchUnitVector)
    }

    fun isAggroed(): Boolean {
        return aggroed
    }

    fun checkAggroed(aggroStrategy: AggroStrategy) {
        if(aggroStrategy.isAggroed()){
            setAggroed()
        }
    }

    fun changeLocation(newLocation: DefaultLocation) {
        val sameArea = this.defaultLocation!!.areaIdentifier == AreaManager.activeArea.identifier
        if (sameArea) {
            location?.removeGameObject(this)
            location = newLocation
            location?.addGameObject(this)
        }
    }
    fun resetAggro() {
        aggroed = false
    }
    fun cleanUpAbilties(){
        enemyStrategy.actionList.forEach { it.cleanUp() }
    }
    open fun setAggroed(){
        aggroed = true
    }
    fun resetHealth() {
        health = maxHealth
    }

    override fun death() {
        this.removeFromLocation()
    }

}