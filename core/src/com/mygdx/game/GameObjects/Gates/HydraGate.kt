package com.mygdx.game.GameObjects.Gates

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.ItemType
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.Sensors.KeySensor
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Saving.DefaultSaveStateHandler
import com.mygdx.game.Saving.SaveStateEntity
import com.mygdx.game.player

class HydraGate(initPosition: Vector2, size: Vector2, defaultLocation: DefaultLocation?) :
    GameObject(initPosition, size, defaultLocation), SaveStateEntity by DefaultSaveStateHandler() {
    override val texture = DefaultTextureHandler.getTexture("LockedDoor.png")
    override val layer = Layer.ONGROUND
    override val collition = IllegalMoveCollition
    private var sensor: KeySensor
    val offset = size.x / 3

    val wastelandKey = Sprite(DefaultTextureHandler.getTexture("WastelandKey.png"))
    val icelandsKey = Sprite(DefaultTextureHandler.getTexture("IcelandsKey.png"))
    val firelandsKey = Sprite(DefaultTextureHandler.getTexture("FirelandsKey.png"))

    init {
        wastelandKey.setPosition( this.x + 15f + (0 * offset),this.y + this.height / 2)
        icelandsKey.setPosition( this.x + 15f + (1 * offset),this.y + this.height / 2)
        firelandsKey.setPosition( this.x + 15f + (2 * offset),this.y + this.height / 2)
        sensor = KeySensor(initPosition,Vector2(300f,100f),defaultLocation!!,this)
        defaultLocation.addGameObject(sensor)
    }


    override fun render(batch: PolygonSpriteBatch) {
        super.render(batch)
        val containsWastelandKey = player.inventory.inventoryList.containsKey(ItemType.WASTELANDSKEY)
        val containsFirelandsKey = player.inventory.inventoryList.containsKey(ItemType.FIRELANDSKEY)
        val containsIcelandsKey = player.inventory.inventoryList.containsKey(ItemType.ICELANDSKEY)
        wastelandKey.setAlpha(if(containsWastelandKey) 1f else 0.3f)
        wastelandKey.draw(batch)
        icelandsKey.setAlpha(if(containsIcelandsKey) 1f else 0.3f)
        icelandsKey.draw(batch)
        firelandsKey.setAlpha(if(containsFirelandsKey) 1f else 0.3f)
        firelandsKey.draw(batch)
    }

    override fun removeFromLocation() {
        sensor.removeFromLocation()
        super.removeFromLocation()
    }
}