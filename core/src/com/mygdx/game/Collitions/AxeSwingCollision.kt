package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.GameObjects.Axe
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.SmallBoulder
import com.mygdx.game.HitOppositeDirection
import com.mygdx.game.Interfaces.EveryFrameCollition
import com.mygdx.game.Interfaces.FightableEntity

class AxeSwingCollision: EveryFrameCollition {
    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(entity is Axe && collidedObject is FightableEntity){
            HitOppositeDirection(entity, collidedObject)
        }
        if(entity is Axe && collidedObject is SmallBoulder){
            collidedObject.defaultLocation!!.removeGameObject(collidedObject)
        }
    }
}