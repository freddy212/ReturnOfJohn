package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.GameObjects.MoveableEntities.IceClone
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Fireball
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Icicle
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Rocket
import com.mygdx.game.Interfaces.MoveCollition

class IceCloneCollition(val iceClone: IceClone): MoveCollition by CanMoveCollition {

    override fun collitionHappened(collidedObject: GameObject) {
        if(collidedObject is Fireball){
            iceClone.removeFromLocation()
            collidedObject.removeFromLocation()
        }
        if(collidedObject is Rocket || collidedObject is Icicle){
            collidedObject.removeFromLocation()
        }
    }
}