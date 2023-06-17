package com.mygdx.game.UI.Map

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.Renderable
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.AreaManager
import com.mygdx.game.drawActiveShape
import com.mygdx.game.drawPolygonShape
import com.mygdx.game.player

object Map: Renderable {
    override val layer = Layer.FOREGROUND

    val shapeRenderer = ShapeRenderer()

    var currentMap: MutableList<Pair<Polygon,DefaultLocation>> = mutableListOf()

    var offset: Vector2 = Vector2()

    override fun render(batch: PolygonSpriteBatch) {

        val playerOffset = Vector2(player.currentPosition().x - offset.x, player.currentPosition().y - offset.y)

        currentMap.forEach {
            it.first.setPosition(playerOffset.x, playerOffset.y)
            if(it.second == player.defaultLocation!!){
                drawActiveShape(it.first, shapeRenderer)
            }else{
                drawPolygonShape(it.first, shapeRenderer)
            }
        }
    }


}