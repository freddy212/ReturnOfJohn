package com.mygdx.game.Utils

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.mygdx.game.Interfaces.Renderable

class RenderGraph {
    companion object{
        private val RenderList = mutableListOf<Renderable>()
        fun addToSceneGraph(renderable: Renderable){
            RenderList.add(renderable)
        }
        fun render(batch: PolygonSpriteBatch){
            batch.begin()
            RenderList.sortBy {it.layer}
            RenderList.forEach{it.render(batch)}
            RenderList.clear()
            batch.end()
        }
    }
}