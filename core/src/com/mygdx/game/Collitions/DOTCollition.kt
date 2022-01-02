package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.Interfaces.EveryFrameCollition
import com.mygdx.game.player

object DOTCollition: EveryFrameCollition {
    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(entity is Player){
            player.health -= 0.5f
        }
    }

    override fun filterCollitions(gameObjects: List<GameObject>): List<GameObject> {
        val DotCollitionObjects = gameObjects.filter{it.collition is DOTCollition}
        return gameObjects.minus(DotCollitionObjects) + DotCollitionObjects.take(1)
    }
}
