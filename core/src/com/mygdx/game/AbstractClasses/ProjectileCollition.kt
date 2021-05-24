package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.getOppositeUnitVector

abstract class ProjectileCollition: MoveCollition {

    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(entity is Projectile && (collidedObject is Player || collidedObject is Enemy)){
            val character = collidedObject as DefaultCharacter
            val centerPointBoulder = Vector2(entity.sprite.x + entity.sprite.width / 2, entity.sprite.y + entity.sprite.height / 2)
            val centerPointPlayer = Vector2(character.sprite.x + character.sprite.width / 2, character.sprite.y + character.sprite.height / 2)
            val oppositeDirection = getOppositeUnitVector(centerPointPlayer,centerPointBoulder)
            character.isHit(oppositeDirection)
        }
    }
}