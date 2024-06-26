package com.mygdx.game.GameObjects.MoveableEntities.Characters

import ToolTip
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.mygdx.game.DefaultTextureHandler
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.CharacterAbility
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Enums.getDirectionUnitVector
import com.mygdx.game.Interfaces.ModelInstanceHandler
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Managers.TooltipManager
import com.mygdx.game.SaveState.DefaultSaveStateHandler
import com.mygdx.game.SaveState.SaveStateEntity
import com.mygdx.game.AbstractClasses.DefaultCharacter
import com.mygdx.game.FightableEnitityData.PlayerFightableEntity
import com.mygdx.game.Interfaces.FightableEntity
import com.mygdx.game.Utils.ResourceList

class Player(Position: Vector2, size: Vector2, modelHandler: ModelInstanceHandler = DefaultModelInstanceHandler("ManBlender.g3db",Position,size))
             : DefaultCharacter(Position, size,null,modelHandler),SaveStateEntity by DefaultSaveStateHandler(),
                                                                         FightableEntity by PlayerFightableEntity(){
    override val texture = DefaultTextureHandler.getTexture("man.png")
    override var currentSpeed = 7f
    override val layer = Layer.PERSON
    val inventory = Inventory()
    override var direction = Direction.UP
    override val collition = IllegalMoveCollition
    val itemAbilities = ResourceList<CharacterAbility>()
    fun die(){
        val playerLocation = LocationManager.activeDefaultLocations.find{ x -> x.sprite.boundingRectangle.contains(Vector2(camera.position.x, camera.position.y))}!!
        player.setPosition(playerLocation.Position, player)
    }
    fun addAbility(characterAbility: CharacterAbility) {
        val toolTip = ToolTip(Sprite(characterAbility.toolTipTexture), Input.Keys.toString(characterAbility.triggerKey)[0])
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

    override fun render(batch: PolygonSpriteBatch) {
        var oldCameraPos = camera.position
        camera.position.set(Vector3(player.sprite.x,player.sprite.y, 4f))
        camera.update()
        super.render(batch)
        camera.position.set(oldCameraPos)
        camera.update()
    }

    override fun death() {
        ResetPlayer(playerSaveState)
    }
}