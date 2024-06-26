package com.mygdx.game.UI.Items

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.Enums.Item
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.Renderable

class RenderInventory: Renderable {
    override val layer = Layer.FOREGROUND
    val box = DefaultTextureHandler.getTexture("ItemDisplay.png")
    val spriteBox = Sprite(box,200,130)
    val uiCircle = DefaultTextureHandler.getTexture("UICircle.png")
    val uiCircleSprite = Sprite(uiCircle)
    var currentIndex = 0
    //val sprites = items.map { Sprite(it.texture,64,32) }

    override fun render(batch: PolygonSpriteBatch) {
        spriteBox.setPosition(player.sprite.x -  200f, player.sprite.y - 50f)
        spriteBox.draw(batch)
        //Could be optimized sometime
        val displayItems: List<Item> = player.inventory.inventoryList.entries.map {Item(it.key,it.value, getItemTexture(it.key))}
        if(displayItems.isNotEmpty()){
            drawItems(batch,displayItems)
        }

    }
    fun drawItems(batch: PolygonSpriteBatch, displayItems: List<Item>){
        val currentItem = displayItems[currentIndex]
        val description = getItemDescription(currentItem.itemType)


        val startPos = Vector2(spriteBox.x, spriteBox.y + spriteBox.height - 64f)
        val uiCircleOffsetX = currentIndex.mod(3)
        val uiCircleOffsetY = currentIndex / 3
        uiCircleSprite.setPosition(startPos.x + (64f * uiCircleOffsetX),startPos.y - (64f * uiCircleOffsetY))
        uiCircleSprite.draw(batch)

        displayItems.forEachIndexed { index, item ->
            val width = item.texture.width.toFloat()
            val height = item.texture.height.toFloat()
            val column: Int = index / 3
            val row = index % 3
            val pos = startPos + Vector2(row * width, - column * width)
            val sprite = Sprite(item.texture,width.toInt(),height.toInt())
 //           font.draw(batch,item.amount.toString(),pos.x + height / 2, pos.y + width)
            sprite.setPosition(pos.x,pos.y)
            sprite.draw(batch)
        }
        font.draw(batch,description,spriteBox.x - 200f, spriteBox.y + 180f)
    }

}