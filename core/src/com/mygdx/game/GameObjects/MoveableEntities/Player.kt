package com.mygdx.game.GameObjects.MoveableEntities

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.DefaultMovement
import com.mygdx.game.AbstractClasses.MoveableObject
import com.mygdx.game.Collitions.BoulderPlayerCollition
import com.mygdx.game.EdgeOfLocationStrategies.NoAction
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Item
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Inventory
import com.mygdx.game.LocationImpl

class Player(Position: Vector2, size: Vector2) : MoveableObject(Position, size,null){
    override val texture = Texture("man.png")
    override val movementStrategy = DefaultMovement(NoAction())
    override var speed = 15f
    override val layer = Layer.PERSON
    private val inventory = Inventory()
    override var direction = Direction.UP
    fun addToInventory(item: Item){
        inventory.addItem(item)
    }
    override val collition = BoulderPlayerCollition()
}