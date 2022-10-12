package com.mygdx.game.GameObjects.ShopItem

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.CharacterAbility
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Item
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.Sensors.BuySensor
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Saving.DefaultSaveStateHandler
import com.mygdx.game.Saving.SaveStateEntity

class ShopItem(textureName: String,val requiredItems: List<Item>, Position: Vector2, size: Vector2, defaultLocation: DefaultLocation?, val ability:CharacterAbility):
    GameObject(Position, size, defaultLocation)
    , SaveStateEntity by DefaultSaveStateHandler(){
    override val texture: Texture = DefaultTextureHandler.getTexture(textureName)
    override val layer = Layer.AIR
    val buySensor = BuySensor(this,Position,size,defaultLocation)
    init {
        this.defaultLocation!!.addGameObject(buySensor)
    }

    override fun removeFromLocation() {
        super.removeFromLocation()
        buySensor.removeFromLocation()
    }
}