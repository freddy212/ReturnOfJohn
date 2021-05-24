package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.AbstractClasses.DefaultCharacter
import com.mygdx.game.Interfaces.EveryFrameCollition

object DOTCollition: EveryFrameCollition {
    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(entity is DefaultCharacter){
            println("playerTakingDamage!")
        }
    }
}
