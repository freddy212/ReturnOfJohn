package com.mygdx.game.UI.Map

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.Renderable
import com.mygdx.game.Interfaces.getAreaColor
import com.mygdx.game.Interfaces.getAreaTitleText
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.AreaManager

object Map: Renderable {

    init {
        mapTitleFont.data.setScale(2f)
    }

    override val layer = Layer.FOREGROUND

    val shapeRenderer = ShapeRenderer()

    var currentMap: MutableList<Pair<Polygon,DefaultLocation>> = mutableListOf()

    var offset: Vector2 = Vector2()

    override fun render(batch: PolygonSpriteBatch) {

        val currentIdentifier = AreaManager.activeArea.identifier
        val areaName = getAreaTitleText(currentIdentifier)
        mapTitleFont.color = getAreaColor(currentIdentifier)

        val playerOffset = Vector2(player.currentPosition().x - offset.x, player.currentPosition().y - offset.y)
        mapTitleFont.draw(batch,areaName, player.currentPosition().x - 150f, player.currentPosition().y + 150f)

        batch.end()
        currentMap.forEach {
            it.first.setPosition(playerOffset.x, playerOffset.y)
            if(it.second == player.defaultLocation!!){
                drawActiveShape(it.first, shapeRenderer)
            }else{
                drawPolygonShape(it.first, shapeRenderer)
            }
        }
        batch.begin()
    }


}