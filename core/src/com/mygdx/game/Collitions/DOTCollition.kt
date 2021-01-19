package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.LocationImpl
import com.mygdx.game.Locations.DamageLocation
import com.mygdx.game.UI.Dialogue.DefaultCharacter

object DOTCollition: MoveCollition by CanMoveCollition {
    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(entity is DefaultCharacter && collidedObject is LocationImpl){
            if(collidedObject.locationStrategy is DamageLocation){
                println("playerTakingDamage!")
            }
        }
    }
}
