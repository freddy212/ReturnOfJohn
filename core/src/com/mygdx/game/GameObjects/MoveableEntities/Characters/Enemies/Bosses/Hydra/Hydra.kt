package com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.Hydra

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.DefaultEnemyStrategy
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.Boss
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.LocationManager

class Hydra(Position: Vector2, size: Vector2, location: DefaultLocation?) : Boss(Position, size, location) {
    override val enemyStrategy = DefaultEnemyStrategy(listOf())
    override var baseSpeed = 5f
    override val texture = DefaultTextureHandler.getTexture("hydra.png")
    override val layer = Layer.ONGROUND
    override var direction = Direction.RIGHT
    override var health = 150f
    override val maxHealth = 150f

    override fun setAggroed() {
        adjacentLocations.forEach {
            if (it.locationName == "location9") {
                it.removeAdjacentLocation(defaultLocation!!)
            }
        }
        LocationManager.changeLocation()
        aggroed = true
    }
}