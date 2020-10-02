package com.mygdx.game.GameObjects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.GenericGameObject
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.InitSprite
import com.mygdx.game.Interfaces.Collition
import com.mygdx.game.RectanglePolygon
import com.mygdx.game.LocationManager

class Tomb(Position: Vector2, size: Vector2) : GameObject(Position, size) {
    val texture = Texture("Tomb.png")
    val graveyardGrass = GenericGameObject(Vector2(Position.x, Position.y - 96f), Vector2(128f, 128f), "grass.png")
    override val sprite = InitSprite(texture)
    override val collition = IllegalMoveCollition
    init {
        ChildrenGameObjects.add(graveyardGrass)
    }
}