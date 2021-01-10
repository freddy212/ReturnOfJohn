package com.mygdx.game.Events

import com.mygdx.game.Interfaces.Event
import com.mygdx.game.UI.Dialogue.TextChoice

class ChangeOptionEvent(val textChoice: TextChoice):Event {
    override fun execute() {
        textChoice.changeActive()
    }

}