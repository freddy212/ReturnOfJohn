package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Interfaces.AreaEntranceCollition
import com.mygdx.game.Interfaces.Collition
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Locations.DefaultLocation

class RemoveDotDamageCollition(val collition: Collition): MoveCollition{
    override val canMoveAfterCollition = true

    override fun collitionHappened(collidedObject: GameObject) {
        if(collition is DOTCollition){
            if(collition.insideCollition){
                collition.movedOutsideAction()
            }
        }
    }

    override fun filterCollitions(gameObjects: List<GameObject>): List<GameObject> {
        return gameObjects.filter { it !is DefaultLocation }
    }
}