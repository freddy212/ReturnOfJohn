package com.mygdx.game.UI.QuestPanel

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2

class QuestPanel(private val listOfQuestContainers: List<QuestContainer>) {

    fun renderQuestIcons(){
        var startPoint = Vector2(Gdx.graphics.width - 200f,Gdx.graphics.height - 100f)
        for(questcontainer in listOfQuestContainers){
            questcontainer.render(startPoint)
            startPoint = Vector2(startPoint.x, startPoint.y - 100f)
        }
    }

}