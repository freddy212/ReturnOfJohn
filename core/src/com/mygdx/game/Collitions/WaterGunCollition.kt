package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.WaterBall
import com.mygdx.game.GameObjects.MoveableEntities.WaterGunSpray
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.ObjectProperties.Fire

class WaterGunCollition: MoveCollition by CanMoveCollition {

    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        val fire: Fire? = collidedObject.properties.List.find {it is Fire} as Fire?
        if(entity is WaterBall && fire != null){
            fire.fireExtinguised()
        }
    }

}
