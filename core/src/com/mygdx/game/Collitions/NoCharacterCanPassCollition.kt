package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.DefaultCharacter
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.Projectile
import com.mygdx.game.Interfaces.Character
import com.mygdx.game.Interfaces.MoveCollition

class NoCharacterCanPassCollition(val gameObject: GameObject) : MoveCollition {
    override var canMoveAfterCollition = false
    override fun collitionHappened(collidedObject: GameObject) {
        canMoveAfterCollition = !(gameObject is Character || collidedObject is Character)
    }
}

class ProjectileCanPassCollition(val gameObject: GameObject): MoveCollition{
    override var canMoveAfterCollition = true
    override fun collitionHappened(collidedObject: GameObject) {
        canMoveAfterCollition = (gameObject is Projectile && collidedObject is DefaultCharacter)
                                ||(gameObject is DefaultCharacter && collidedObject is Projectile)
    }
}


