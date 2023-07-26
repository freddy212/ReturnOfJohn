package com.mygdx.game.Areas.WastelandArea

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Areas.DefaultArea
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.InsertDirection
import com.mygdx.game.Interfaces.Area
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Interfaces.AreaInitializer
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Locations.DefaultLocationData
import com.mygdx.game.Locations.RepeatedTextureLocationData
import com.mygdx.game.addLocation
import com.mygdx.game.addLocationRelative
import getWastelandLocationEightObjects
import getWastelandLocationFourObjects
import getWastelandLocationOneObjects
import getWastelandLocationSevenObjects
import getWastelandLocationSixObjects
import getWastelandLocationThreeObjects
import getWastelandLocationTwoObjects

class WastelandAreaInitializer: AreaInitializer {
    override fun initializeArea(): Area {
        val area = DefaultArea(AreaIdentifier.WASTELAND)
        val spikeTexture = DefaultTextureHandler.getTexture("Spike.png")
        val wastelandLocationData =  DefaultLocationData("wasteland.jpg")
        val wastelandDamageLocation = RepeatedTextureLocationData("wasteland.jpg", spikeTexture)
        val location1 = DefaultLocation(Vector2(200f, 300f), Vector2(0f, 0f),::getWastelandLocationOneObjects,
            wastelandLocationData)
        addLocation(location1, area)
        val location2 = addLocationRelative(location1,Vector2(1000f, 300f), InsertDirection.LEFT,area,InsertDirection.MIDDLE,  ::getWastelandLocationTwoObjects, wastelandLocationData)
        val location3 = addLocationRelative(location2,Vector2(1000f, 1000f), InsertDirection.LEFT,area,InsertDirection.MIDDLE,::getWastelandLocationThreeObjects, wastelandDamageLocation)
        //val location7 = addLocationRelative(location6,Vector2(1500f, 1500f), InsertDirection.DOWN,area,InsertDirection.MIDDLE,::getWastelandLocationSevenObjects , wastelandDamageLocation)
        val location4 = addLocationRelative(location3, Vector2(400f, 200f), InsertDirection.LEFT,area,InsertDirection.MIDDLE, ::getWastelandLocationFourObjects , wastelandLocationData)
        val location5 = addLocationRelative(location4, Vector2(200f, 500f), InsertDirection.UP,area,InsertDirection.LEFT, { listOf()} , wastelandLocationData)
        val location6 = addLocationRelative(location5, Vector2(1000f, 1200f), InsertDirection.UP,area,InsertDirection.MIDDLE,  ::getWastelandLocationSixObjects , wastelandDamageLocation)
        val location7 = addLocationRelative(location6, Vector2(600f,200f), InsertDirection.LEFT, area, InsertDirection.UP, ::getWastelandLocationSevenObjects, wastelandLocationData)
        val location8 = addLocationRelative(location7,Vector2(1500f, 1500f), InsertDirection.LEFT,area,InsertDirection.MIDDLE,::getWastelandLocationEightObjects, wastelandDamageLocation)

        location5.addAdjacentLocation(location3)
        location6.addAdjacentLocation(location3)
        location6.addAdjacentLocation(location8)

       // location9.addAdjacentLocation(location5)
        return area
    }
}