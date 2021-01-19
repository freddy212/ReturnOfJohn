package com.mygdx.game.UI.Dialogue.Conversations

import com.mygdx.game.Events.*
import com.mygdx.game.GameObjects.MoveableEntities.NPC
import com.mygdx.game.UI.Dialogue.*
import com.mygdx.game.player

fun GetFireConversation(npc: NPC): Conversation {

    val dialogueBox = DialogueBox()

    val dialogueText = DialogueText("Hey, can you help me train?",npc)
    val dialogueText2 = DialogueText("Train? What do you want me to do?",npc)
    val dialogueText3 = DialogueText("I need to practice for the show at the festival",npc)
    val dialogueText4 = DialogueText("Just block 10 of my attacks",npc)
    val OptionText = TextChoice(DialogueChoice("I took care of it",npc, CheckFireExtinguishedEvent(npc)), DialogueChoice("Still on it",npc),npc)
    val dialogueText5 = DialogueText("Okay, please hurry up",npc)

    val sentence1 = Sentence(dialogueBox, dialogueText, npc)
    val sentence2 = Sentence(dialogueBox, dialogueText2, player)
    val sentence3 = Sentence(dialogueBox, dialogueText3, npc)
    val sentence4 = Sentence(dialogueBox,dialogueText4,npc)
    val sentence5 = OptionSentence(dialogueBox,OptionText, player)
    val sentence6 = Sentence(dialogueBox,dialogueText5,npc)

    val conversation = Conversation(npc)
    conversation.conversation.addAll(listOf(sentence1,sentence2,sentence3,sentence4,sentence5,sentence6))
    return conversation
}

fun GetFireNotFixedConversation(npc: NPC):Conversation{
    val dialogueBox = DialogueBox()

    val dialogueText = DialogueText("Don't lie to me boy",npc)

    val sentence1 = Sentence(dialogueBox, dialogueText, npc)

    val conversation = Conversation(npc)
    conversation.conversation.addAll(listOf(sentence1))
    return conversation

}
fun GetFireFixedConversation(npc: NPC):Conversation{
    val dialogueBox = DialogueBox()

    val dialogueText = DialogueText("Thanks for helping me!",npc)
    val dialogueText2 = DialogueText("I live to serve",npc)

    val sentence1 = Sentence(dialogueBox, dialogueText, npc)
    val sentence2 = Sentence(dialogueBox, dialogueText2, player)
    sentence2.dialogueText.event = CompositeEvent(listOf(RemoveNpcEvent(npc),ContinueConversationEvent(npc)))

    val conversation = Conversation(npc)
    conversation.conversation.addAll(listOf(sentence1,sentence2))
    return conversation
}