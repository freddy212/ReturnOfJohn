package com.mygdx.game.Areas.WastelandArea

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Areas.DefaultArea
import com.mygdx.game.Areas.DojoArea.getDojoObjects
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Interfaces.Area
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Interfaces.AreaInitializer
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Locations.DefaultLocationData
import com.mygdx.game.addLocation
import getWastelandLocationOneObjects

class WastelandAreaInitializer: AreaInitializer {
    override fun initializeArea(): Area {
        val area = DefaultArea(AreaIdentifier.WASTELAND)
        val wastelandLocationData =  DefaultLocationData(DefaultTextureHandler.getTexture("wasteland.jpg"))
        val location1 = DefaultLocation(Vector2(1000f, 1000f), Vector2(0f, 0f),::getWastelandLocationOneObjects, wastelandLocationData)
        addLocation(location1, area)
        return area
    }
}