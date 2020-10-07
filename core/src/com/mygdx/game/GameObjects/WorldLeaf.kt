package com.mygdx.game.GameObjects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.ItemCollition
import com.mygdx.game.Enums.Item
import com.mygdx.game.Interfaces.ItemObject
import com.mygdx.game.Enums.Layer

class WorldLeaf(Position: Vector2, size: Vector2) : GameObject(Position, size),ItemObject {
    override val texture = Texture("WorldLeaf.png")
    override val collition = ItemCollition
    override val item = Item.WORLDLEAF
    override val layer = Layer.ONGROUND
}