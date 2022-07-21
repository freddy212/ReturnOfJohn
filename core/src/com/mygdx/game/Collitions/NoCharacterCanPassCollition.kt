package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.DefaultCharacter
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.Projectile
import com.mygdx.game.Interfaces.Character
import com.mygdx.game.Interfaces.MoveCollition

class NoCharacterCanPassCollition:MoveCollition {
    override var canMoveAfterCollition = false
    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        canMoveAfterCollition = !(entity is Character || collidedObject is Character)
    }
}

class ProjectileCanPassCollition: MoveCollition {
    override var canMoveAfterCollition = true
    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        canMoveAfterCollition = (entity is Projectile && collidedObject is DefaultCharacter)
                                ||(entity is DefaultCharacter && collidedObject is Projectile)
    }
}


