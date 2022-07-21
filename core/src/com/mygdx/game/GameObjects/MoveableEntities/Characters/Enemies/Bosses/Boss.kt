package com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.Enemy
import com.mygdx.game.HealthStrategy.BossHealthStrategy
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.LocationManager

data class BoolHolder(var value: Boolean) {
}

abstract class Boss(
    Position: Vector2,
    size: Vector2,
    location: DefaultLocation?,
) : Enemy(Position, size, location, 200f) {

    val boolHolder = BoolHolder(false)
    val adjacentLocations = location?.adjacentDefaultLocations!!

    override val healthStrategy = BossHealthStrategy(boolHolder)
    init {
        onLocationEnterActions.add(::resetArea)
    }
    override fun frameTask() {
        boolHolder.value = isAggroed()
        super.frameTask()
    }

    override fun setAggroed(){
        adjacentLocations.forEach {it.removeAdjacentLocation(defaultLocation!!)}
        LocationManager.changeLocation()
        super.setAggroed()
    }

    fun resetArea() {
        if(!LocationManager.activeDefaultLocations.containsAll(adjacentLocations)){
            adjacentLocations.forEach {it.addAdjacentLocation(defaultLocation!!)}
            LocationManager.changeLocation()
        }
    }

    override fun death() {
        resetArea()
        super.death()
    }
}