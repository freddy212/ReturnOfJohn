package com.mygdx.game.Collitions

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.GameObjects.BoulderGenerator
import com.mygdx.game.GameObjects.MoveableEntities.Boulder
import com.mygdx.game.GameObjects.MoveableEntities.Player
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Interfaces.DynamicEntity
import com.mygdx.game.getOppositeUnitVector

class BoulderCollition: MoveCollition {
    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(entity is Boulder && collidedObject is Player){
            entity.location!!.removeGameObject(entity)
            val centerPointBoulder = Vector2(entity.sprite.x + entity.sprite.width / 2, entity.sprite.y + entity.sprite.height / 2)
            val centerPointPlayer = Vector2(collidedObject.sprite.x + collidedObject.sprite.width / 2, collidedObject.sprite.y + collidedObject.sprite.height / 2)
            val oppositeDirection = getOppositeUnitVector(centerPointPlayer,centerPointBoulder)
            collidedObject.isHit(oppositeDirection)
        }
        if(entity is Boulder && (collidedObject is BoulderGenerator || collidedObject is Boulder)){
            collidedObject.location!!.removeGameObject(collidedObject)
            entity.location!!.removeGameObject(entity)
        }
    }

    override var canMoveAfterCollition = true
}