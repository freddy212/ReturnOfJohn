package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Enums.CharacterState
import com.mygdx.game.GameObjects.Other.Abyss
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.player

object AbyssCollition: MoveCollition by CanMoveCollition {
    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(entity is Player && collidedObject is Abyss){
            if(player.characterState != CharacterState.DASHING){
                player.death()
            }
        }
    }
}