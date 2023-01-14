package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.Interfaces.AreaEntranceCollition
import com.mygdx.game.Interfaces.Collition
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Locations.DefaultLocation

class RemoveDotDamageCollition(val collition: Collition) : MoveCollition {
    override val canMoveAfterCollition = true

    override fun collitionHappened(collidedObject: GameObject) {
        if (collition is DOTCollition && collidedObject is Player) {
            collition.movedOutside()
        }
    }

    override fun filterCollitions(gameObjects: List<GameObject>): List<GameObject> {
        return gameObjects.filter { it !is DefaultLocation }
    }
}