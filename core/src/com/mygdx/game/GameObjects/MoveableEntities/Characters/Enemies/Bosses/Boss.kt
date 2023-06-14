package com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.Enemy
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.HealthStrategy.BossHealthStrategy
import com.mygdx.game.Interfaces.CollitionMask
import com.mygdx.game.Interfaces.HealthStrategy
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Managers.SignalManager
import com.mygdx.game.Saving.DefaultSaveStateHandler
import com.mygdx.game.Saving.SaveStateEntity
import com.mygdx.game.Signal.Signal
import com.mygdx.game.Signal.Signals.RemoveObjectSignal

open class BossCollitionMask(boss: Boss): CollitionMask {
    override val canCollideWith = { _: GameObject -> boss.isAggroed()}

}
abstract class Boss(
    Position: Vector2,
    size: Vector2,
    location: DefaultLocation?,
    val onDeathSignal: Signal? = null
) : Enemy(Position, size, location, 300f), SaveStateEntity by DefaultSaveStateHandler() {

    val adjacentLocations = location?.adjacentDefaultLocations!!
    override val healthStrategy: HealthStrategy by lazy {BossHealthStrategy(this)}
    override val collitionMask by lazy { BossCollitionMask(this) }
    var isRolling = false
    init {
        onLocationEnterActions.add(::resetArea)
    }

    override fun setAggroed(){
        defaultLocation!!.adjacentDefaultLocations.forEach {it.removeAdjacentLocation(defaultLocation!!)}
        println(defaultLocation!!.adjacentDefaultLocations)
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
        SignalManager.emitSignal(RemoveObjectSignal(entityId))
        if(onDeathSignal != null){
            SignalManager.emitSignal(onDeathSignal)
        }
    }
}