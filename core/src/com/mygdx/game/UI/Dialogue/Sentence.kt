package com.mygdx.game.UI.Dialogue

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.mygdx.game.Interfaces.Character

open class Sentence(val dialogueBox: DialogueBox, val dialogueText: DialogueText, val character: Character) {

    open fun draw(dialogueBatch: PolygonSpriteBatch, font: BitmapFont,x: Float, y: Float, text: String = dialogueText.text) {
        font.draw(dialogueBatch,text,x,y)
    }
}