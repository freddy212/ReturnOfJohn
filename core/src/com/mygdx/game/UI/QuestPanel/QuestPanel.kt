package com.mygdx.game.UI.QuestPanel

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Managers.QuestManager

class QuestPanel(val uiBatch: PolygonSpriteBatch) {

    fun renderQuestIcons(){
        var startPoint = Vector2(Gdx.graphics.width - 200f,Gdx.graphics.height - 100f)
        for(questcontainer in QuestManager.QuestManager.List.map {it.questContainer}){
            questcontainer.render(startPoint,uiBatch)
            startPoint = Vector2(startPoint.x, startPoint.y - 100f)
        }
    }

}