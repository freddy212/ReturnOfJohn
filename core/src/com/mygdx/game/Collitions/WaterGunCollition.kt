package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.Projectile
import com.mygdx.game.AbstractClasses.ProjectileCollition
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.WaterBall
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.ObjectProperties.Fire
import com.mygdx.game.ObjectProperties.Ice

class WaterGunCollition : ProjectileCollition() {

    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        super.collitionHappened(entity, collidedObject)
        if (entity is WaterBall) {
            val fire: Fire? = collidedObject.properties.List.find { it is Fire } as Fire?
            val ice: Ice? = collidedObject.properties.List.find { it is Ice } as Ice?
            fire?.fireExtinguised()
            ice?.changeWater(entity)
            if(collidedObject is Projectile){
                entity.removeFromLocation()
            }
        }
    }

    override val canMoveAfterCollition = true
}
