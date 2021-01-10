package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Interfaces.DirectionalObject
import com.mygdx.game.Interfaces.DynamicEntity
import com.mygdx.game.UI.Dialogue.DefaultCharacter

object DefaultPositionChange: DynamicEntity {
    override fun setPosition(position: Vector2, gameObject: GameObject) {
        val sprite = gameObject.sprite
        val polygon = gameObject.polygon
        sprite.setPosition(position.x,position.y)
        polygon.setPosition(position.x - polygon.vertices[0],position.y - polygon.vertices[1])


    }

    override fun setRotation(direction: Direction, directionalObject: DirectionalObject) {
        directionalObject.direction = direction
    }

}