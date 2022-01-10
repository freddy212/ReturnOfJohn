package com.mygdx.game.UI.Dialogue.Conversations

import com.mygdx.game.Events.EndConversationEvent
import com.mygdx.game.Events.EngineerCityEvent
import com.mygdx.game.GameObjects.MoveableEntities.Characters.NPC
import com.mygdx.game.UI.Dialogue.*
import com.mygdx.game.player

fun engineerFirst(npc: NPC): Conversation {
    val dialogueBox = DialogueBox()

    val dialogueText = DialogueText("Do you wanna come to the city?",npc)
    val OptionText = TextChoice(DialogueChoice("yes",npc),DialogueChoice("no",npc, EndConversationEvent(npc)),npc)
    val dialogueText4 = DialogueText("great, i will see you there!",npc,EngineerCityEvent(npc))

    val sentence1 = Sentence(dialogueBox, dialogueText, npc)
    val sentence2 = OptionSentence(dialogueBox,OptionText, player)
    val sentence3 = Sentence(dialogueBox,dialogueText4,npc)

    val conversation = Conversation(npc)
    conversation.sentenceList.addAll(listOf(sentence1,sentence2,sentence3))
    return conversation
}
fun engineerSecond(npc: NPC): Conversation {
    val dialogueBox = DialogueBox()

    val dialogueText = DialogueText("So, how long are we walking?",npc)
    val dialogueText2 = DialogueText("Until it gets dark",npc)
    val dialogueText3 = DialogueText("i see",npc)

    val sentence1 = Sentence(dialogueBox, dialogueText, player)
    val sentence2 = Sentence(dialogueBox, dialogueText2, npc)
    val sentence3 = Sentence(dialogueBox, dialogueText3, player)

    val conversation = Conversation(npc)
    conversation.sentenceList.addAll(listOf(sentence1,sentence2,sentence3))
    return conversation
}