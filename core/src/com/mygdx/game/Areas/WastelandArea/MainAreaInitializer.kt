package com.mygdx.game.Areas.WastelandArea

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Areas.DefaultArea
import com.mygdx.game.Areas.DojoArea.getDojoObjects
import com.mygdx.game.Areas.DungeonArea.getLocationThreeCaveObjects
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.InsertDirection
import com.mygdx.game.Interfaces.Area
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Interfaces.AreaInitializer
import com.mygdx.game.Locations.DamageLocationData
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Locations.DefaultLocationData
import com.mygdx.game.Locations.RepeatedTextureLocationData
import com.mygdx.game.addLocation
import com.mygdx.game.addLocationRelative
import getWastelandLocationFiveObjects
import getWastelandLocationOneObjects
import getWastelandLocationSevenObjects
import getWastelandLocationThreeObjects

class WastelandAreaInitializer: AreaInitializer {
    override fun initializeArea(): Area {
        val area = DefaultArea(AreaIdentifier.WASTELAND)
        val wasteLandTexture = DefaultTextureHandler.getTexture("wasteland.jpg")
        val spikeTexture = DefaultTextureHandler.getTexture("Spike.png")
        val wastelandLocationData =  DefaultLocationData(wasteLandTexture)
        val wastelandDamageLocation = RepeatedTextureLocationData(wasteLandTexture, spikeTexture)
        val location1 = DefaultLocation(Vector2(1024f, 1024f), Vector2(0f, 0f),::getWastelandLocationOneObjects,
            DefaultLocationData(DefaultTextureHandler.getTexture("WastelandLocation1.png")))
        addLocation(location1, area)
        val location2 = addLocationRelative(location1,Vector2(1000f, 300f), InsertDirection.RIGHT,area,InsertDirection.MIDDLE,{ listOf()}, wastelandLocationData)
        val location3 = addLocationRelative(location2,Vector2(700f, 700f), InsertDirection.RIGHT,area,InsertDirection.MIDDLE,::getWastelandLocationThreeObjects, wastelandLocationData)
        val location4 = addLocationRelative(location1,Vector2(1000f, 300f), InsertDirection.LEFT,area,InsertDirection.MIDDLE,{ listOf()}, wastelandLocationData)
        val location5 = addLocationRelative(location4,Vector2(3000f, 600f), InsertDirection.LEFT,area,InsertDirection.MIDDLE,::getWastelandLocationFiveObjects, wastelandDamageLocation)
        val location6 = addLocationRelative(location1,Vector2(300f, 500f), InsertDirection.DOWN,area,InsertDirection.MIDDLE,{ listOf()}, wastelandLocationData)
        val location7 = addLocationRelative(location6,Vector2(1500f, 1500f), InsertDirection.DOWN,area,InsertDirection.MIDDLE,::getWastelandLocationSevenObjects , wastelandDamageLocation)
        return area
    }
}