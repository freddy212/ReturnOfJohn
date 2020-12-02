package com.mygdx.game.Areas.ShopArea

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.DefaultArea
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.LocationImpl
import com.mygdx.game.addLocation
import com.mygdx.game.addLocationsToArea

fun initializeShop(){
    val area = DefaultArea(AreaIdentifier.SHOP)
    val location1 = LocationImpl(Vector2(600f, 600f), Vector2(0f, 0f),::getShopObjects, Texture("HouseFloor.jpg"))
    addLocation(location1,area)
    addLocationsToArea(area)
}