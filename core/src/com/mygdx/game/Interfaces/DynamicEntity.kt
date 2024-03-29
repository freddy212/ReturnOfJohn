package com.mygdx.game.Interfaces

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.MoveableObject
import com.mygdx.game.Enums.Direction

interface DynamicEntity {
    fun setPosition(position: Vector2,gameObject: GameObject)
}