package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.Enemy
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.ProjectileCollition
import com.mygdx.game.GameObjects.Generators.BoulderGenerator
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.SandGhost.SandHand
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Boulder
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.SmallBoulder


class BoulderCollition: ProjectileCollition() {
    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(!(entity is SandHand || collidedObject is SandHand)){
            super.collitionHappened(entity, collidedObject)
        }
        if(entity is Boulder && (collidedObject is BoulderGenerator || collidedObject is Boulder)){
            collidedObject.removeFromLocation()
            entity.removeFromLocation()
        }
    }

    override var canMoveAfterCollition = true
}