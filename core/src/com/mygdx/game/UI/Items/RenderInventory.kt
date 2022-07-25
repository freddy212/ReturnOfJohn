package com.mygdx.game.UI.Items

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.Enums.Item
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.ShopItem.ShopItem
import com.mygdx.game.Interfaces.Renderable

class RenderInventory: Renderable {
    override val layer = Layer.FOREGROUND
    val box = DefaultTextureHandler.getTexture("ItemDisplay.png")
    val spriteBox = Sprite(box,200,400)
    //val sprites = items.map { Sprite(it.texture,64,32) }

    override fun render(batch: PolygonSpriteBatch) {
        spriteBox.setPosition(player.sprite.x+ 50f, player.sprite.y - 100f)
        spriteBox.draw(batch)
        //Could be optimized sometime
        val displayItems: List<Item> = player.inventory.inventoryList.entries.map {Item(it.key,it.value, getItemTexture(it.key))}
        drawItems(batch,displayItems)

    }
    fun drawItems(batch: PolygonSpriteBatch, displayItems: List<Item>){
        val startPos = Vector2(spriteBox.x, spriteBox.y + spriteBox.height - 64f)
        displayItems.forEachIndexed { index, item ->
            val column: Int = index / 3
            val row = index % 3
            val pos = startPos + Vector2(row * 64f, - column * 64f)
            val sprite = Sprite(item.texture,64,32)
            font.draw(batch,item.amount.toString(),pos.x + 16f, pos.y + 64f)
            sprite.setPosition(pos.x,pos.y)
            sprite.draw(batch)
        }
    }
}