package com.mygdx.game.UI.Items

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Item
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.ShopItem.ShopItem
import com.mygdx.game.Interfaces.Renderable
import com.mygdx.game.Inventory
import com.mygdx.game.font

class RenderShopItem(val shopItem: ShopItem,val inventory: Inventory): Renderable {
    override val layer = Layer.AIR
    val box = DefaultTextureHandler.getTexture("Box.png")
    val spriteBox = Sprite(box,75,100)
    val items = shopItem.requiredItems
    val sprites = items.map { Sprite(it.texture,64,32) }

    override fun render(batch: PolygonSpriteBatch) {
        spriteBox.setPosition(shopItem.sprite.x - 75, shopItem.sprite.y)
        spriteBox.draw(batch)
        drawRequiredItems(batch)
    }
    fun drawRequiredItems(batch: PolygonSpriteBatch){
        var offset = 10f
        for(itemIndex in items.indices){
            val currentSprite = sprites[itemIndex]
            setFont(items[itemIndex])
            font.draw(batch,shopItem.requiredItems[itemIndex].amount.toString(), spriteBox.x + 10f, spriteBox.y + spriteBox.height - offset)
            currentSprite.setPosition(spriteBox.x + 20f, spriteBox.y + spriteBox.height - currentSprite.height - offset)
            currentSprite.draw(batch)
            //cleanup of current item
            font.color = Color.WHITE
            offset += 40f
        }
    }
    fun setFont(item: Item){
        if(inventory.getItemCount(item.itemType) >= item.amount){
            font.color = Color.GREEN
        } else{
            font.color = Color.RED
        }
    }
}