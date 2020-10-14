package com.mygdx.game.EdgeOfLocationStrategies

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Interfaces.EdgeOfLocationStrategy

class RemoveObject: EdgeOfLocationStrategy {
    override fun handleEdgeOfLocation(gameObject: GameObject) {
            gameObject.location!!.removeGameObject(gameObject)
    }
}