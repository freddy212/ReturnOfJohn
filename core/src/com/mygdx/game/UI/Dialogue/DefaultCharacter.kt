package com.mygdx.game.UI.Dialogue

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.mygdx.game.Interfaces.Character

object DefaultCharacter: Character {
    override val font = BitmapFont()

    init {
        font.data.setScale(2f)
        font.color = Color.WHITE
    }
}