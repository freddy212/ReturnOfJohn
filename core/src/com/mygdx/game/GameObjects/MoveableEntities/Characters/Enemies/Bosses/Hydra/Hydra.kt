package com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.Hydra

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AI.EnemyActions.Dash
import com.mygdx.game.AI.EnemyActions.MoveBasedOnPlayer
import com.mygdx.game.AbstractClasses.DefaultEnemyStrategy
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.Boss
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.IceQueen.BossClone
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.SandGhost.DashCollition
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.boss.BossCloneAction
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.getUnitVectorTowardsPoint

class Hydra(Position: Vector2, size: Vector2, location: DefaultLocation?) : Boss(Position, size, location) {
    override var baseSpeed = 5f
    override var texture = DefaultTextureHandler.getTexture("hydra.png")
    override val layer = Layer.ONGROUND
    override var direction = Direction.RIGHT
            override var health = 150f
    override val maxHealth = 150f
    override val collition = DashCollition(this)

    var bossClone = BossClone(this)
    val movementOption = MoveBasedOnPlayer(250f, ::getUnitVectorTowardsPoint, bossClone)
    val bossCloneAction = BossCloneAction(this, bossClone, listOf(movementOption), true)

    override val enemyStrategy = DefaultEnemyStrategy(listOf(HydraEarthHeadAttack(this), Dash(this), bossCloneAction))
    override fun setAggroed() {
        val location9 = LocationManager.findLocation("location9",AreaIdentifier.FROSTFIRE)
        defaultLocation!!.adjacentDefaultLocations.forEach {
            if (it.locationName != "location9") {
                it.removeAdjacentLocation(location9)
            }
        }
        defaultLocation!!.removeAdjacentLocation(location9)
        LocationManager.changeLocation()
        aggroed = true
    }

    override fun resetArea(){
        val location9 = LocationManager.findLocation("location9", AreaIdentifier.FROSTFIRE)
        defaultLocation!!.adjacentDefaultLocations.forEach {
            if(location9 !in it.adjacentDefaultLocations){
                it.addAdjacentLocation(location9)
            }
        }
        if(location9 !in defaultLocation!!.adjacentDefaultLocations){
            defaultLocation!!.addAdjacentLocation(location9)
        }
        LocationManager.changeLocation()
    }
}