package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.ProjectileCollition
import com.mygdx.game.Events.DefaultEvent
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Boulder
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Fireball
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Icicle
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.WaterBall
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.ObjectProperties.Fire
import kotlin.reflect.typeOf

class FireballCollition(val fireball: Fireball): ProjectileCollition(fireball) {
    override fun collitionHappened(collidedObject: GameObject) {
        super.collitionHappened(collidedObject)
        if (collidedObject is WaterBall) {
            fireball.removeFromLocation()
            collidedObject.removeFromLocation()
        }
        if (collidedObject is Icicle) {
            collidedObject.removeFromLocation()
        }
    }
}
