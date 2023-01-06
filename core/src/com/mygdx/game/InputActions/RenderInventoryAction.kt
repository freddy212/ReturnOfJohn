package com.mygdx.game.InputActions

import com.badlogic.gdx.Input
import com.mygdx.game.Interfaces.Event
import com.mygdx.game.Interfaces.InputAction
import com.mygdx.game.Managers.EventManager
import com.mygdx.game.UI.Items.RenderInventory
import com.mygdx.game.Utils.RenderGraph

class RenderInventoryAction(val renderInventory: RenderInventory = RenderInventory()): InputAction {
    override val keycodes = listOf(Input.Keys.I)

    val renderEvent = object: Event {
        override fun execute() {
            RenderGraph.addToSceneGraph(renderInventory)
        }

    }

    override fun action(keycode: Int) {
        renderInventory.currentIndex = 0
        EventManager.eventManager.add(renderEvent)
    }

    override fun inactiveAction() {
        EventManager.eventManager.remove(renderEvent)
    }
}