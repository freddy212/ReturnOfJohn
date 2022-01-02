package com.mygdx.game.GameObjects.ShopItem

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.CharacterAbility
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Item
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.Sensors.PlayerInsideSensor
import com.mygdx.game.Inventory
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.SaveHandling.DefaultAbilityGainedSaveState
import com.mygdx.game.SaveHandling.DefaultRemoveObjectSaveState
import com.mygdx.game.SaveState.SaveStateEntity
import com.mygdx.game.player

class ShopItem(textureName: String,val requiredItems: List<Item>, Position: Vector2, size: Vector2, defaultLocation: DefaultLocation?, val ability:CharacterAbility,
private val saveStateHandler: SaveStateEntity = DefaultAbilityGainedSaveState(ability)):
    GameObject(Position, size, defaultLocation)
    ,SaveStateEntity by saveStateHandler{
    override val texture: Texture = DefaultTextureHandler.getTexture(textureName)
    override val layer = Layer.AIR
    val playerInsideSensor = PlayerInsideSensor(this,Position,size,defaultLocation)
    init {
        this.defaultLocation!!.addGameObject(playerInsideSensor)
    }

    override fun removeFromLocation() {
        super.removeFromLocation()
        playerInsideSensor.removeFromLocation()
    }

    override fun onLoadAction() {
        saveStateHandler.onLoadAction()
        requiredItems.forEach { player.inventory.useItems(it.itemType,it.amount) }
    }
}