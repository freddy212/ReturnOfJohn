package com.mygdx.game.GameObjects.Sensors

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.BuyItemCollition
import com.mygdx.game.Collitions.CheckKeyCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.ShopItem.ShopItem
import com.mygdx.game.Locations.DefaultLocation

class BuySensor(shopItem: ShopItem, Position: Vector2, size: Vector2, defaultLocation: DefaultLocation?) :
    GameObject(Position, size, defaultLocation) {
    override val texture = DefaultTextureHandler.getTexture("sensor.png")
    override val layer = Layer.ONGROUND
    override fun render(batch: PolygonSpriteBatch) {
        sprite.draw(batch)
    }
    init {
        polygon.setPosition(Position.x - polygon.vertices[0],sprite.y - polygon.vertices[1] - sprite.height)
    }

    override val collition = BuyItemCollition(shopItem)
}