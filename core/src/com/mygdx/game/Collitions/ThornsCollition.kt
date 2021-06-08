package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Icicle
import com.mygdx.game.GameObjects.Thorns
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.SaveHandling.FileHandler

class ThornsCollition: MoveCollition{
    override var canMoveAfterCollition = false

    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(entity is Icicle && collidedObject is Thorns){
            canMoveAfterCollition = true
            collidedObject.removeFromLocation()
        }
        if(entity is Player && collidedObject is Thorns){
            PlayerHitCollition().collitionHappened(entity,collidedObject)
        }
    }
}