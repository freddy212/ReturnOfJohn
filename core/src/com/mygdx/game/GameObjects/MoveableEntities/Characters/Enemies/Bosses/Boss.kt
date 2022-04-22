package com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.Enemy
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.HealthStrategy.BossHealthStrategy
import com.mygdx.game.HealthStrategy.EnemyHealthStrategy
import com.mygdx.game.Interfaces.EnemyStrategy
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Locations.DefaultLocation

data class BoolHolder(var value: Boolean) {
}

abstract class Boss(
    Position: Vector2,
    size: Vector2,
    location: DefaultLocation?,
) : Enemy(Position, size, location, 200f) {

    val boolHolder = BoolHolder(false)

    override val healthStrategy = BossHealthStrategy(boolHolder)
    override fun frameTask() {
        boolHolder.value = isAggroed()
        super.frameTask()
    }
}