package com.mygdx.game.UI.Dialogue

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.MoveableObject
import com.mygdx.game.GameObjects.MoveableEntities.NPC
import com.mygdx.game.GameObjects.MoveableEntities.Player
import com.mygdx.game.Interfaces.Character
import com.mygdx.game.camera
import com.mygdx.game.drawPolygonShape
import com.mygdx.game.player

class UIController {
    companion object {
        val shapeRenderer = ShapeRenderer()
        val dialogueBatch = PolygonSpriteBatch()
        fun drawSentence(sentence: Sentence, character: Character) {
            dialogueBatch.projectionMatrix = camera.combined

            val dialogueText = sentence.dialogueText
            val dialogueBox = sentence.dialogueBox

            val font = character.font
            val text = dialogueText.text
            val box = dialogueBox.polygon

            val talkingCharacter:MoveableObject = if(character is NPC) character else player
            val position = Vector2(talkingCharacter.sprite.x - 400f,talkingCharacter.sprite.y + talkingCharacter.size.y + 50f)

            box.setPosition(position.x, position.y)
            drawPolygonShape(box, shapeRenderer)
            dialogueBatch.begin()
            sentence.draw(dialogueBatch,font,box.x + box.vertices[0] +25f,box.y + box.vertices[1] +  50f)
            dialogueBatch.end()
        }
    }
}
