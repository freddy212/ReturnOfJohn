package com.mygdx.game.Collitions

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.MoveableEntity
import com.mygdx.game.Interfaces.Collition

object NoCollition: Collition {
    override fun collitionHappened(entity: MoveableEntity, collitionPosition: Vector2, collidedObject:GameObject) {
        entity.sprite.setPosition(collitionPosition.x,collitionPosition.y)
    }
}