package com.mygdx.game.GameObjects

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.AbyssCollition
import com.mygdx.game.InitSprite
import com.mygdx.game.RectanglePolygon
import com.mygdx.game.TerrainManager.Companion.collitionPolygons
import com.mygdx.game.abyssCollition

class Abyss(Position: Vector2, size: Vector2): GameObject(Position, size){
        val abyssTexture = Texture("MainB.jpg")

        override val spriteToRender = InitSprite(abyssTexture)
        init{
        spriteToRender.color = Color.BLACK

        collitionPolygons.add(Pair(RectanglePolygon(spriteToRender.boundingRectangle), abyssCollition))
        }
}