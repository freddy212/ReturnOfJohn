package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Interfaces.EnemyStrategy
import com.mygdx.game.Interfaces.ModelInstanceHandler
import com.mygdx.game.Locations.DefaultLocation

abstract class Enemy(Position: Vector2, size: Vector2, location: DefaultLocation?, modelHandler: ModelInstanceHandler, val aggroRadius: Float)
    : DefaultCharacter(Position, size, location,modelHandler),
    EnemyStrategy by DefaultEnemyStrategy(){

    override fun frameTask() {
        val aggroCircle = Circle(this.sprite.x,this.sprite.y,aggroRadius)
        if(aggroed(InsideCircle(aggroCircle))){
            getNextAction().executeEnemyAction(this)
        }
        super.frameTask()
    }

    override fun isHit(launchUnitVector: Vector2) {
        aggroed(DefaultAggro())
        super.isHit(launchUnitVector)
    }

}