package com.mygdx.game.Areas.WastelandArea

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Areas.DefaultArea
import com.mygdx.game.Areas.DojoArea.getDojoObjects
import com.mygdx.game.Areas.DungeonArea.getLocationThreeCaveObjects
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.InsertDirection
import com.mygdx.game.Interfaces.Area
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Interfaces.AreaInitializer
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Locations.DefaultLocationData
import com.mygdx.game.addLocation
import com.mygdx.game.addLocationRelative
import getWastelandLocationOneObjects
import getWastelandLocationThreeObjects

class WastelandAreaInitializer: AreaInitializer {
    override fun initializeArea(): Area {
        val area = DefaultArea(AreaIdentifier.WASTELAND)
        val wastelandLocationData =  DefaultLocationData(DefaultTextureHandler.getTexture("wasteland.jpg"))
        val location1 = DefaultLocation(Vector2(1000f, 1000f), Vector2(0f, 0f),::getWastelandLocationOneObjects, wastelandLocationData)
        addLocation(location1, area)
        val location2 = addLocationRelative(location1,Vector2(1000f, 300f), InsertDirection.RIGHT,area,InsertDirection.MIDDLE,{ listOf()}, wastelandLocationData)
        val location3 = addLocationRelative(location2,Vector2(700f, 700f), InsertDirection.RIGHT,area,InsertDirection.MIDDLE,::getWastelandLocationThreeObjects, wastelandLocationData)
        return area
    }
}