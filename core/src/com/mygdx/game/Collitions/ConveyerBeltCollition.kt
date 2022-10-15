package com.mygdx.game.Collitions

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.GameObjects.Hazards.ConveyerBelt.ConveyerBelt
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.Interfaces.Event
import com.mygdx.game.Interfaces.EveryFrameCollition
import com.mygdx.game.Managers.EventManager
import com.mygdx.game.player
import com.mygdx.game.plus

class ConveyerBeltCollition: EveryFrameCollition {
    object ResetModifier : Event {
        override fun execute() {
            player.moveModifier = Vector2(0f,0f)
        }

        override fun runOnce(): Boolean {
            return true
        }
    }
    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(entity is Player && collidedObject is ConveyerBelt){
            entity.moveModifier = Vector2(0f, - player.baseSpeed)
            if(!player.hasMovedThisFrame){
                entity.move(Vector2(0f,0f))
                entity.setCharacterRotation(Vector2(0f,-1f))
            }
            EventManager.eventManager.add(ResetModifier)
        }
    }
}