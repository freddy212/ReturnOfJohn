package com.mygdx.game.Collitions

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Enums.ItemType
import com.mygdx.game.GameObjects.Other.Axe
import com.mygdx.game.GameObjects.ItemObjects.GenericInventoryItemObject
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.SmallBoulder
import com.mygdx.game.Interfaces.EveryFrameCollition

class AxeSwingCollision(val axe: Axe): EveryFrameCollition {
    override fun collitionHappened(collidedObject: GameObject) {
        if(collidedObject is SmallBoulder){
            collidedObject.defaultLocation!!.removeGameObject(collidedObject)
            val genericItemObject = GenericInventoryItemObject(collidedObject.currentMiddle, Vector2(64f,32f),
                                    collidedObject.defaultLocation!!, ItemType.FLINT)
            collidedObject.defaultLocation!!.addGameObject(genericItemObject)
        }
    }
}