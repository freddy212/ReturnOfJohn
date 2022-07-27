package com.mygdx.game.GameObjects.MoveableEntities.Characters

import ToolTip
import com.badlogic.gdx.Input
import com.mygdx.game.DefaultTextureHandler
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.CharacterAbility
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Enums.getDirectionUnitVector
import com.mygdx.game.Interfaces.ModelInstanceHandler
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Managers.TooltipManager
import com.mygdx.game.SaveState.DefaultSaveStateHandler
import com.mygdx.game.SaveState.SaveStateEntity
import com.mygdx.game.AbstractClasses.DefaultCharacter
import com.mygdx.game.Collitions.ProjectileCanPassCollition
import com.mygdx.game.HealthStrategy.PlayerHealthStrategy
import com.mygdx.game.Interfaces.FightableEntity
import com.mygdx.game.Utils.ResourceList

class Player(Position: Vector2, size: Vector2, modelHandler: ModelInstanceHandler = DefaultModelInstanceHandler("ManBlender.g3db",Position,size))
             : DefaultCharacter(Position, size,null),SaveStateEntity by DefaultSaveStateHandler(),
                                                                         FightableEntity{
    override val texture = DefaultTextureHandler.getTexture("man.png")
    override var baseSpeed: Float = 7f
    override val layer = Layer.PERSON
    val inventory = Inventory()
    override var direction = Direction.UP
    override val collition = ProjectileCanPassCollition()
    override var health = 100f
    override val maxHealth = 100f
    override val healthStrategy = PlayerHealthStrategy()
    val itemAbilities = ResourceList<CharacterAbility>()
    fun die(){
        val playerLocation = LocationManager.activeDefaultLocations.find{ x -> x.sprite.boundingRectangle.contains(Vector2(camera.position.x, camera.position.y))}!!
        player.setPosition(playerLocation.initPosition)
    }
    fun addAbility(characterAbility: CharacterAbility) {
        val toolTip = ToolTip(Sprite(characterAbility.toolTipTexture), Input.Keys.toString(characterAbility.triggerKey)[0],characterAbility.cooldownTimer)
        TooltipManager.tooltipManager.add(toolTip)
        itemAbilities.add(characterAbility)
    }
    //For test
    fun move(direction: Direction){
        player.move(getDirectionUnitVector(direction))
    }
    override fun isHit(launchUnitVector:Vector2){
        itemAbilities.List.forEach { x -> x.InactiveAction() }
        super.isHit(launchUnitVector)
    }

    override fun death() {
        ResetPlayer(playerSaveState)
    }
}