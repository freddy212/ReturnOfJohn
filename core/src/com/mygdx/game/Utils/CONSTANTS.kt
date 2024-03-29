package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player

val Center = Vector2(Gdx.graphics.width.toFloat() / 2, Gdx.graphics.height.toFloat() / 2)

val cameraPos = {player: Player -> Vector2((Center.x - player.sprite.x), (Center.y - player.sprite.y))}

fun drawPolygonShape(polygon: Polygon, shapeRenderer: ShapeRenderer){
    val Position = cameraPos(player)
    shapeRenderer.begin(ShapeRenderer.ShapeType.Line)
    shapeRenderer.polygon(polygon.transformedVertices + Position)
    shapeRenderer.end()
}

fun drawActiveShape(polygon: Polygon, shapeRenderer: ShapeRenderer){
    shapeRenderer.color = Color.GREEN

    drawPolygonShape(polygon, shapeRenderer)

    shapeRenderer.color = Color.WHITE
}

const val NOTSAVEDID = -1
