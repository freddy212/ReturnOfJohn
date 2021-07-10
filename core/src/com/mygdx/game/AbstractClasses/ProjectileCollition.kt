package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.HitOppositeDirection
import com.mygdx.game.Interfaces.FightableEntity
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.getOppositeUnitVector

abstract class ProjectileCollition: MoveCollition{

    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(entity is Projectile && collidedObject is FightableEntity){
            HitOppositeDirection(entity, collidedObject)
        }
    }

    override val canMoveAfterCollition = true
}