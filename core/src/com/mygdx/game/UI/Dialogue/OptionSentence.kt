package com.mygdx.game.UI.Dialogue

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.mygdx.game.Interfaces.Character

class OptionSentence(dialogueBox: DialogueBox,val textChoice: TextChoice, character: Character) : Sentence(dialogueBox, textChoice, character) {
    val offsetBetweenOptions = 350f
    override fun draw(dialogueBatch: PolygonSpriteBatch, font: BitmapFont, x: Float, y: Float, text: String) {
        setActiveFont(font,textChoice,textChoice.Option1.Option)
        super.draw(dialogueBatch, font, x, y,textChoice.Option1.Option)
        setActiveFont(font,textChoice,textChoice.Option2.Option)
        super.draw(dialogueBatch, font, x + offsetBetweenOptions, y,textChoice.Option2.Option)
        font.color = Color.WHITE
    }

    private fun setActiveFont(font:BitmapFont, textChoice: TextChoice, optionText:String){
        if(textChoice.activeOption.Option.equals(optionText)){
            font.color = Color.GREEN
        }else{
            font.color = Color.WHITE
        }
    }
}