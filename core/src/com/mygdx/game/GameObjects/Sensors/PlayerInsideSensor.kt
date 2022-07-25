package com.mygdx.game.GameObjects.Sensors

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.PlayerInsideCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.ShopItem.ShopItem
import com.mygdx.game.GetCollidingObjects
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.UI.Items.RenderShopItem
import com.mygdx.game.Utils.RenderGraph
import com.mygdx.game.player

class PlayerInsideSensor(val shopItem: ShopItem, Position: Vector2, size: Vector2, defaultLocation: DefaultLocation?) : GameObject(Position, size,defaultLocation){
    override val texture = DefaultTextureHandler.getTexture("sensor.png")
    override val layer = Layer.ONGROUND
    var playerInside = false
    override fun render(batch: PolygonSpriteBatch){
        sprite.draw(batch)
    }
    val buySensor = BuySensor(polygon,shopItem,Position,size,defaultLocation)
    init {
        polygon.setPosition(Position.x - polygon.vertices[0],sprite.y - polygon.vertices[1] - sprite.height)
        buySensor.defaultLocation?.addGameObject(buySensor)
    }

    override val collition = PlayerInsideCollition()

    override fun frameTask() {
        if(playerInside){
            if(this !in GetCollidingObjects(player.polygon,LocationManager.ActiveGameObjects)){
                playerInside = false
            }
            RenderGraph.addToSceneGraph(RenderShopItem(shopItem, player.inventory))
        }
    }

    override fun removeFromLocation() {
        super.removeFromLocation()
        buySensor.removeFromLocation()
    }

}