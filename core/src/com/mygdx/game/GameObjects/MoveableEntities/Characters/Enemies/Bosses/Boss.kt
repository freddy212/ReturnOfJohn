package com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.Enemy
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.HealthStrategy.BossHealthStrategy
import com.mygdx.game.Interfaces.CollitionMask
import com.mygdx.game.Interfaces.HealthStrategy
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.LocationManager

open class BossCollitionMask(boss: Boss): CollitionMask {
    override val canCollideWith = { _: GameObject -> boss.isAggroed()}

}
abstract class Boss(
    Position: Vector2,
    size: Vector2,
    location: DefaultLocation?,
) : Enemy(Position, size, location, 300f) {

    val adjacentLocations = location?.adjacentDefaultLocations!!
    override val healthStrategy: HealthStrategy by lazy {BossHealthStrategy(this)}
    override val collitionMask by lazy { BossCollitionMask(this) }
    init {
        onLocationEnterActions.add(::resetArea)
    }
    override fun frameTask() {
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