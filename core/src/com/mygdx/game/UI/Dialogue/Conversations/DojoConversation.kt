package com.mygdx.game.UI.Dialogue.Conversations

import com.mygdx.game.DefaultQuest
import com.mygdx.game.Enums.QuestIdentifier
import com.mygdx.game.Events.*
import com.mygdx.game.GameObjects.MoveableEntities.NPC
import com.mygdx.game.LocationImpl
import com.mygdx.game.UI.Dialogue.*
import com.mygdx.game.player

fun GetDojoConversation(npc: NPC): Conversation {

    val dialogueBox = DialogueBox()

    val dialogueText = DialogueText("Hey, can you help me train?",npc)
    val dialogueText2 = DialogueText("Train? What do you want me to do?",npc)
    val dialogueText3 = DialogueText("I need to practice for the show at the festival",npc)
    val dialogueText4 = DialogueText("Just block 10 of my attacks",npc)
    val OptionText = TextChoice(DialogueChoice("I'll help", npc,CompositeEvent(listOf(AddEventEvent(DojoEvent(npc.location!!,ClearQuestEvent(QuestIdentifier.DOJO))), RemoveNpcEvent(npc),ContinueConversationEvent(npc)))), DialogueChoice("I'll do it later",npc),npc)

    val sentence1 = Sentence(dialogueBox, dialogueText, npc)
    val sentence2 = Sentence(dialogueBox, dialogueText2, player)
    val sentence3 = Sentence(dialogueBox, dialogueText3, npc)
    val sentence4 = Sentence(dialogueBox,dialogueText4,npc)
    val sentence5 = OptionSentence(dialogueBox,OptionText, player)

    val conversation = Conversation(npc)
    conversation.conversation.addAll(listOf(sentence1,sentence2,sentence3,sentence4,sentence5))
    return conversation
}