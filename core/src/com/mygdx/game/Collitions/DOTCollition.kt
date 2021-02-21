package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.LocationImpl
import com.mygdx.game.Locations.DamageLocation
import com.mygdx.game.AbstractClasses.DefaultCharacter
import com.mygdx.game.counter

object DOTCollition: MoveCollition by CanMoveCollition {
    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(entity is DefaultCharacter){
            counter += 1
            println("playerTakingDamage!" + counter)
        }
    }
}
