package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.HitOppositeDirection
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.getOppositeUnitVector

abstract class ProjectileCollition: MoveCollition{

    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(entity is Projectile && (collidedObject is Player || collidedObject is Enemy)){
            val character = collidedObject as DefaultCharacter
            HitOppositeDirection(entity, character)
        }
    }

    override val canMoveAfterCollition = true
}