package com.mygdx.game.GameObjects.MoveableEntities

import ToolTip
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.ItemAbility
import com.mygdx.game.AbstractClasses.DefaultMovement
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.MoveableObject
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.EdgeOfLocationStrategies.NoAction
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Item
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.Character
import com.mygdx.game.Interfaces.DirectionalObject
import com.mygdx.game.Inventory
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Managers.TooltipManager
import com.mygdx.game.ResourceList
import com.mygdx.game.UI.Dialogue.DefaultCharacter
import com.mygdx.game.camera
import com.mygdx.game.player

class Player(Position: Vector2, size: Vector2) :DefaultCharacter(Position, size,null){
    private var death = false
    override val texture = Texture("man.png")
    override val movementStrategy = DefaultMovement(NoAction())
    override var speed = 10f
    override val layer = Layer.PERSON
    val inventory = Inventory()
    override var direction = Direction.UP
    val itemAbilities = ResourceList<ItemAbility>()
    override val collition = IllegalMoveCollition
    init {
        polygon.setOrigin(sprite.originX,sprite.originY)
    }
    /*override fun setPosition(position:Vector2, gameObject: GameObject){
        super.setPosition(position,gameObject)
    }*/

    override fun setRotation(direction: Direction, directionalObject: DirectionalObject) {
        super.setRotation(direction,directionalObject)
        polygon.rotation = when(direction){
            Direction.UP -> 0f
            Direction.RIGHT -> 90f
            Direction.DOWN -> 180f
            Direction.LEFT -> 270f
        }
    }
    fun die(){
        val playerLocation = LocationManager.locations.find{ x -> x.sprite.boundingRectangle.contains(Vector2(camera.position.x, camera.position.y))}!!
        player.setPosition(playerLocation.Position, player)
    }
    fun addItemAbility(itemAbility: ItemAbility) {
        val toolTip = ToolTip(Sprite(itemAbility.texture), Input.Keys.toString(itemAbility.triggerKey)[0])
        TooltipManager.tooltipManager.add(toolTip)
        itemAbilities.add(itemAbility)
    }
}