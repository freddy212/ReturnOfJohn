package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Icicle
import com.mygdx.game.GameObjects.Thorns
import com.mygdx.game.Interfaces.MoveCollition

class IcicleCollition: MoveCollition by IllegalMoveCollition{
    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
    }
}
