package com.mygdx.game.Interfaces

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.UI.Actions.RenderKeyAction

abstract class KeyPressedCollition: Collition {
    abstract val specificButton: Int

    open fun renderKeyToUI(entity: GameObject, collidedObject: GameObject) {
        val uiRenderKey = RenderKeyAction()
        uiRenderKey.renderKeyAction(specificButton)
    }
}