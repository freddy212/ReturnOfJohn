package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Collitions.AbyssCollition
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.Collitions.NoCollition

val Center = Vector2(Gdx.graphics.width.toFloat() / 2, Gdx.graphics.height.toFloat() / 2)

val cameraPos = {player: Player -> Vector2((Center.x - player.sprite.x), (Center.y - player.sprite.y))}

fun drawPolygonShape(polygon: Polygon, player: Player, shapeRenderer: ShapeRenderer){
    val Position = cameraPos(player)
    shapeRenderer.begin(ShapeRenderer.ShapeType.Line)
    polygon.setPosition(Position.x,Position.y)
    shapeRenderer.polygon(polygon.transformedVertices)
    shapeRenderer.end()

}
