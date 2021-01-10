package com.mygdx.game.UI.Dialogue.Conversations

import com.mygdx.game.Events.EndConversationEvent
import com.mygdx.game.GameObjects.MoveableEntities.NPC
import com.mygdx.game.UI.Dialogue.*
import com.mygdx.game.player

fun GetFirstConversation(npc: NPC): Conversation {
    val dialogueBox = DialogueBox()

    val dialogueText = DialogueText("hello, what are you doing here?",npc)
    val dialogueText2 = DialogueText("I'm going for a walk",npc)
    val dialogueText3 = DialogueText("I see. Let me join you",npc)
    val OptionText = TextChoice(DialogueChoice("sphagetti",npc),DialogueChoice("Rice",npc,EndConversationEvent(npc)),npc)
    val dialogueText4 = DialogueText("shows up when choosing sphagetti",npc)

    val sentence1 = Sentence(dialogueBox, dialogueText, player)
    val sentence2 = Sentence(dialogueBox, dialogueText2, npc)
    val sentence3 = Sentence(dialogueBox, dialogueText3, player)
    val sentence4 = OptionSentence(dialogueBox,OptionText, player)
    val sentence5 = Sentence(dialogueBox,dialogueText4,npc)

    val conversation = Conversation(npc)
    conversation.conversation.addAll(listOf(sentence1,sentence2,sentence3,sentence4,sentence5))
    return conversation
}
fun GetSecondConversation(npc: NPC): Conversation {
    val dialogueBox = DialogueBox()

    val dialogueText = DialogueText("So, how long are we walking?",npc)
    val dialogueText2 = DialogueText("Until it gets dark",npc)
    val dialogueText3 = DialogueText("i see",npc)

    val sentence1 = Sentence(dialogueBox, dialogueText, player)
    val sentence2 = Sentence(dialogueBox, dialogueText2, npc)
    val sentence3 = Sentence(dialogueBox, dialogueText3, player)

    val conversation = Conversation(npc)
    conversation.conversation.addAll(listOf(sentence1,sentence2,sentence3))
    return conversation
}