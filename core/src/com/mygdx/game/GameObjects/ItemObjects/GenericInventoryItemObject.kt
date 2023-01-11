package com.mygdx.game.GameObjects.ItemObjects

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.ItemCollition
import com.mygdx.game.Enums.ItemType
import com.mygdx.game.Interfaces.ItemObject
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Saving.DefaultSaveStateHandler
import com.mygdx.game.Saving.SaveStateEntity
import com.mygdx.game.getItemTexture

class GenericInventoryItemObject(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation, itemType: ItemType) : GameObject(Position, size,defaultLocation),ItemObject,
                                                          SaveStateEntity by DefaultSaveStateHandler(){
    override val texture = getItemTexture(itemType)
    override val collition = ItemCollition(this)
    override val itemType = itemType
    override val layer = Layer.ONGROUND
}