package com.mygdx.game

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.game.Interfaces.Renderable

class RenderGraph {
    companion object{
        private val RenderList = mutableListOf<Renderable>()
        fun addToSceneGraph(renderable: Renderable){
            RenderList.add(renderable)
        }
        fun render(batch: PolygonSpriteBatch){
            RenderList.sortBy {it.layer}
            RenderList.forEach{it.render(batch)}
            RenderList.clear()
        }
    }
}