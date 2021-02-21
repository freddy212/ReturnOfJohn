package com.mygdx.game.GameObjects.ItemObjects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.ItemCollition
import com.mygdx.game.Enums.Item
import com.mygdx.game.Interfaces.ItemObject
import com.mygdx.game.Enums.Layer
import com.mygdx.game.LocationImpl
import com.mygdx.game.SaveHandling.DefaultItemObjectSaveState
import com.mygdx.game.SaveState.SaveStateEntity

class GenericItemObject(Position: Vector2, size: Vector2, location: LocationImpl, item: Item, texture: Texture) : GameObject(Position, size,location),ItemObject,
                                                          SaveStateEntity by DefaultItemObjectSaveState(item){
    override val texture = texture
    override val collition = ItemCollition
    override val item = item
    override val layer = Layer.ONGROUND
}