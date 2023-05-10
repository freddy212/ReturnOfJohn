package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.Projectile
import com.mygdx.game.AbstractClasses.ProjectileCollition
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Fireball
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.WaterBall
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.ObjectProperties.Fire
import com.mygdx.game.ObjectProperties.Ice

class WaterGunCollition(val waterBall: WaterBall) : ProjectileCollition(waterBall) {

    override fun collitionHappened(collidedObject: GameObject) {
        super.collitionHappened(collidedObject)
            val fire: Fire? = collidedObject.properties.List.find { it is Fire } as Fire?
            val ice: Ice? = collidedObject.properties.List.find { it is Ice } as Ice?
            if(fire != null) collidedObject.properties.remove(fire)
            fire?.extinguishFireEvent?.execute()
            ice?.changeWater(waterBall)
            if(collidedObject is Projectile){
                if(collidedObject is Fireball){
                    collidedObject.removeFromLocation()
                }
            }
    }

    override val canMoveAfterCollition = true
}
