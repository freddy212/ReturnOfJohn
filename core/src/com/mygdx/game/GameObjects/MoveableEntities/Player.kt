package com.mygdx.game.GameObjects.MoveableEntities

import ToolTip
import com.badlogic.gdx.Input
import com.mygdx.game.DefaultTextureHandler
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g3d.Model
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.ItemAbility
import com.mygdx.game.AbstractClasses.DefaultMovement
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.EdgeOfLocationStrategies.NoAction
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Enums.getDirectionUnitVector
import com.mygdx.game.Interfaces.ModelInstanceHandler
import com.mygdx.game.Managers.DefaultAssetHandler.assets
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Managers.TooltipManager
import com.mygdx.game.UI.Dialogue.DefaultCharacter

class Player(Position: Vector2, size: Vector2, modelHandler: ModelInstanceHandler = DefaultModelInstanceHandler(
        assets.get("ManBlender.g3db", Model::class.java),
        Position,size))
             :DefaultCharacter(Position, size,null,modelHandler){
    private var death = false
    override val texture = DefaultTextureHandler.getTexture("man.png")
    override val movementStrategy = DefaultMovement(NoAction())
    override var currentSpeed = 7f
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
    fun die(){
        val playerLocation = LocationManager.locations.find{ x -> x.sprite.boundingRectangle.contains(Vector2(camera.position.x, camera.position.y))}!!
        player.setPosition(playerLocation.Position, player)
    }
    fun addItemAbility(itemAbility: ItemAbility) {
        val toolTip = ToolTip(Sprite(itemAbility.texture), Input.Keys.toString(itemAbility.triggerKey)[0])
        TooltipManager.tooltipManager.add(toolTip)
        itemAbilities.add(itemAbility)
    }
    //For test
    fun move(direction: Direction){
        player.move(getDirectionUnitVector(direction))
    }
}