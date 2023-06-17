package com.mygdx.game.InputActions

import com.mygdx.game.Interfaces.Event
import com.mygdx.game.Interfaces.InputAction
import com.mygdx.game.Interfaces.Renderable
import com.mygdx.game.Managers.EventManager
import com.mygdx.game.Utils.RenderGraph

class RenderEventAction(val renderable: Renderable, keycode: Int): InputAction {
    override val keycodes = listOf(keycode)

    val renderEvent = object: Event {
        override fun execute() {
            RenderGraph.addToSceneGraph(renderable)
        }

    }

    override fun action(keycode: Int) {
        EventManager.eventManager.add(renderEvent)
    }

    override fun inactiveAction() {
        EventManager.eventManager.remove(renderEvent)
    }

}