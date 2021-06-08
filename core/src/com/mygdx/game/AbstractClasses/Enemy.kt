package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Collitions.PlayerHitCollition
import com.mygdx.game.Interfaces.EnemyStrategy
import com.mygdx.game.Interfaces.ModelInstanceHandler
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.AreaManager

abstract class Enemy(
    Position: Vector2,
    size: Vector2,
    var location: DefaultLocation?,
    modelHandler: ModelInstanceHandler,
    val aggroRadius: Float
) : DefaultCharacter(Position, size, location, modelHandler){

    private var aggroed = false
    override val collition = PlayerHitCollition()

    abstract val enemyStrategy:EnemyStrategy

    // ShootProjectile(::Fireball, Vector2(100f, 50f))
    init {
        this.onLocationExitActions.add(::changeLocation)
    }

    override fun frameTask() {
        val aggroCircle = Circle(this.sprite.x, this.sprite.y, aggroRadius)
        if (checkAggroed(InsideCircle(aggroCircle))) {
            enemyStrategy.getActions(this).forEach {it.executeEnemyAction(this)}
        }
        super.frameTask()
    }

    override fun isHit(launchUnitVector: Vector2) {
        checkAggroed(DefaultAggro())
        super.isHit(launchUnitVector)
    }

    fun checkAggroed(aggroStrategy: AggroStrategy): Boolean {
        aggroed = aggroed || aggroStrategy.isAggroed()
        return aggroed
    }

    fun changeLocation(newLocation: DefaultLocation) {
        val sameArea = this.defaultLocation!!.areaIdentifier == AreaManager.activeArea.identifier
        if (sameArea) {
            location?.removeGameObject(this)
            location = newLocation
            location?.addGameObject(this)
        }
    }

}