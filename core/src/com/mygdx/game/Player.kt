package com.mygdx.game

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.MoveableEntity
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Item
import com.mygdx.game.Enums.Layer

class Player : MoveableEntity(){
    override val sprite =  Sprite(Texture("man.png"))
    override var speed = 10f
    override val layer = Layer.PERSON
    private val inventory = Inventory()
    override var direction = Direction.UP
    init {
        val scale = 0.3f
        sprite.setSize(sprite.width * scale, sprite.height * scale)
        sprite.setPosition(0f,0f)
        sprite.setOriginCenter()
    }
    fun addToInventory(item: Item){
        inventory.addItem(item)
    }
}