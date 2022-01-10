package com.mygdx.game.UI.Dialogue

import com.mygdx.game.GameObjects.MoveableEntities.Characters.NPC

class Conversation(val npc: NPC) {
    val sentenceList = mutableListOf<Sentence>()
}