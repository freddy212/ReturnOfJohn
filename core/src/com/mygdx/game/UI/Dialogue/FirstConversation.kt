package com.mygdx.game.UI.Dialogue

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.GameObjects.MoveableEntities.NPC
import com.mygdx.game.player

fun GetFirstConversation(npc: NPC): Conversation{
    val dialogueBox = DialogueBox()

    val dialogueText = DialogueText("hello, what are you doing here?")
    val dialogueText2 = DialogueText("I'm going for a walk")
    val dialogueText3 = DialogueText("I see. Let me join you")

    val sentence1 = Sentence(dialogueBox,dialogueText,player)
    val sentence2 = Sentence(dialogueBox,dialogueText2,npc)
    val sentence3 = Sentence(dialogueBox,dialogueText3, player)

    val conversation = Conversation(npc)
    conversation.conversation.addAll(listOf(sentence1,sentence2,sentence3))
    return conversation
}
fun GetSecondConversation(npc: NPC): Conversation{
    val dialogueBox = DialogueBox()

    val dialogueText = DialogueText("So, how long are we walking?")
    val dialogueText2 = DialogueText("Until it gets dark")
    val dialogueText3 = DialogueText("i see")

    val sentence1 = Sentence(dialogueBox,dialogueText,player)
    val sentence2 = Sentence(dialogueBox,dialogueText2,npc)
    val sentence3 = Sentence(dialogueBox,dialogueText3, player)

    val conversation = Conversation(npc)
    conversation.conversation.addAll(listOf(sentence1,sentence2,sentence3))
    return conversation
}