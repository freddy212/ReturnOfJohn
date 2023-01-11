package com.mygdx.game.Interfaces

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.UI.Actions.RenderKeyAction
import com.mygdx.game.Utils.RenderGraph

abstract class KeyPressedCollition: BaseCollition {
    abstract val specificButton: Int

    open fun renderKeyToUI(entity: GameObject, collidedObject: GameObject) {
        val uiRenderKey = RenderKeyAction()
        uiRenderKey.renderKeyAction(specificButton)
    }
}