package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.ProjectileCollition
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Fireball
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Icicle
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.WaterBall
import com.mygdx.game.Interfaces.MoveCollition
import kotlin.reflect.typeOf

class FireballCollition : ProjectileCollition() {
    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        super.collitionHappened(entity, collidedObject)
        if (entity is Fireball || collidedObject is Fireball) {
            if (collidedObject is WaterBall || entity is WaterBall) {
                entity.defaultLocation!!.removeGameObject(entity)
                collidedObject.defaultLocation!!.removeGameObject(collidedObject)
            }
            if (collidedObject is Icicle) {
                collidedObject.defaultLocation!!.removeGameObject(collidedObject)
            } else if (entity is Icicle) {
                entity.defaultLocation!!.removeGameObject(entity)
            }
        }
    }
}
