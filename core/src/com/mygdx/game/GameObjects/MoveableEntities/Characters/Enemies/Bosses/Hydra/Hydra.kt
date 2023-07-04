package com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.Hydra

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AI.EnemyActions.*
import com.mygdx.game.AbstractClasses.DefaultEnemyStrategy
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.Boss
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.IceQueen.BossClone
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.SandGhost.DashCollition
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.boss.BossCloneAction
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Fireball
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Icicle
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.SmallBoulder
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Timer.DefaultTimer
import com.mygdx.game.getOppositeUnitVector
import com.mygdx.game.getUnitVectorTowardsPoint
import com.mygdx.game.minus

class Hydra(Position: Vector2, size: Vector2, location: DefaultLocation?) : Boss(Position, size, location) {
    override var baseSpeed = 5f
    override var texture = DefaultTextureHandler.getTexture("hydra.png")
    override val layer = Layer.ONGROUND
    override var direction = Direction.RIGHT
    override var health = 150f
    override val maxHealth = 150f
    override val collition = DashCollition(this)

    var bossClone = BossClone(this)

    private val randomAction = RandomAction(
        listOf(
            MoveBasedOnPlayer(250f, ::getUnitVectorTowardsPoint, this),
            MoveBasedOnPlayer(250f, ::getOppositeUnitVector, this),
            MoveTowardsPoint(this, this.defaultLocation!!.currentMiddle - Vector2(size.x / 2, 0f))
        ), DefaultTimer(1f), this
    )
    private val randomActionClone = RandomAction(
        listOf(
            MoveBasedOnPlayer(250f, ::getUnitVectorTowardsPoint, bossClone),
            MoveBasedOnPlayer(250f, ::getOppositeUnitVector, bossClone),
            MoveTowardsPoint(bossClone, this.defaultLocation!!.currentMiddle - Vector2(size.x / 2, 0f))
        ), DefaultTimer(1f), bossClone
    )
    val bossCloneAction = BossCloneAction(this, bossClone, listOf(randomActionClone), true)


    private val randomShootAction = RandomAction(
        listOf(
            ShootProjectile(::Icicle, Vector2(100f, 34f), this),
            ShootProjectile(::Fireball, Vector2(100f, 50f), this),
            ShootProjectile(::SmallBoulder, Vector2(50f, 50f), this)
        ), DefaultTimer(2f), this, true, true
    )
    override val enemyStrategy =
        DefaultEnemyStrategy(listOf(HydraEarthHeadAttack(this), Dash(this), randomAction,randomShootAction, bossCloneAction))

    override fun setAggroed() {
        val location9 = LocationManager.findLocation("location9", AreaIdentifier.FROSTFIRE)
        defaultLocation!!.adjacentDefaultLocations.forEach {
            if (it.locationName != "location9") {
                it.removeAdjacentLocation(location9)
            }
        }
        defaultLocation!!.removeAdjacentLocation(location9)
        LocationManager.changeLocation()
        aggroed = true
    }

    override fun resetArea() {
        val location9 = LocationManager.findLocation("location9", AreaIdentifier.FROSTFIRE)
        defaultLocation!!.adjacentDefaultLocations.forEach {
            if (location9 !in it.adjacentDefaultLocations) {
                it.addAdjacentLocation(location9)
            }
        }
        if (location9 !in defaultLocation!!.adjacentDefaultLocations) {
            defaultLocation!!.addAdjacentLocation(location9)
        }
        LocationManager.changeLocation()
    }
}