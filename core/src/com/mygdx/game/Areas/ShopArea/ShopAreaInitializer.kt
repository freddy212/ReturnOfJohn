package com.mygdx.game.Areas.ShopArea

import com.mygdx.game.DefaultTextureHandler
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.DefaultArea
import com.mygdx.game.Interfaces.Area
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Interfaces.AreaInitializer
import com.mygdx.game.LocationImpl
import com.mygdx.game.addLocation

class ShopAreaInitializer():AreaInitializer {
    override fun initializeArea(): Area {
        val area = DefaultArea(AreaIdentifier.SHOP)
        val location1 = LocationImpl(Vector2(600f, 600f), Vector2(0f, 0f), ::getShopObjects, DefaultTextureHandler.getTexture("HouseFloor.jpg"))
        addLocation(location1, area)
        return area
    }
}