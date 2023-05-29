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
import com.mygdx.game.Saving.DefaultSaveStateHandler
import com.mygdx.game.Saving.SaveStateEntity
import com.mygdx.game.AbstractClasses.DefaultCharacter
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.ProjectileCanPassCollition
import com.mygdx.game.HealthStrategy.PlayerHealthStrategy
import com.mygdx.game.Interfaces.FightableEntity
import com.mygdx.game.ItemAbilities.ProjectileAbilityToggle
import com.mygdx.game.Utils.ResourceList

class Player(Position: Vector2, size: Vector2, modelHandler: ModelInstanceHandler = DefaultModelInstanceHandler("ManBlender.g3db",Position,size))
             : DefaultCharacter(Position, size,null),SaveStateEntity by DefaultSaveStateHandler(),
                                                                         FightableEntity{
    override val texture = DefaultTextureHandler.getTexture("man.png")
    override var baseSpeed: Float = 7f
    override val layer = Layer.PERSON
    val inventory = Inventory()
    override var direction = Direction.UP
    override val collition = ProjectileCanPassCollition(this)
    override var health = 100f
    override var maxHealth = 100f
    override val healthStrategy = PlayerHealthStrategy()
    var hasMovedThisFrame = false
    val itemAbilities = ResourceList<CharacterAbility>()
    init {
        addAbility(ProjectileAbilityToggle)
    }
    fun addAbility(characterAbility: CharacterAbility) {
        val toolTip = ToolTip(Input.Keys.toString(characterAbility.triggerKey)[0],characterAbility)
        TooltipManager.tooltipManager.add(toolTip)
        itemAbilities.add(characterAbility)
    }
    fun removeAbility(characterAbility: CharacterAbility?){
        if(characterAbility != null){
            itemAbilities.remove(characterAbility)
            val tooltipToRemove = TooltipManager.tooltipManager.List.filter { it.key == Input.Keys.toString(characterAbility.triggerKey)[0]}
            TooltipManager.tooltipManager.remove(tooltipToRemove[0])
        }
    }
    //For test
    fun move(direction: Direction){
        player.move(getDirectionUnitVector(direction))
    }
    override fun isHit(other:GameObject){
        itemAbilities.List.forEach { x -> if(x.active) x.inactiveAction() }
        super.isHit(other)
    }

    override fun death() {
        ResetPlayer(playerSaveState)
    }
}