package com.mygdx.game.Areas.FireLands

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.Areas.DefaultArea
import com.mygdx.game.Interfaces.Area
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Interfaces.AreaInitializer
import com.mygdx.game.Locations.DamageLocationData
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Locations.DefaultLocationData

class FireLandsInitializer: AreaInitializer {
    override fun initializeArea(): Area {
        val area = DefaultArea(AreaIdentifier.FIRELANDS)
        val location1 = DefaultLocation(
            Vector2(1500f, 1000f), Vector2(0f, 0f), ::getFireLandsLocationOneObjects, DamageLocationData()
        )
        addLocation(location1, area)
        val location2 = addLocationRelative(location1, Vector2(2800f, 1000f), InsertDirection.UP, area,
            InsertDirection.MIDDLE, ::getFireLandsLocationTwoObjects,DamageLocationData())
        val location3 = addLocationRelative(location2, Vector2(200f, 1000f), InsertDirection.UP, area,
            InsertDirection.MIDDLE, ::getFireLandsLocationThreeObjects,DefaultLocationData())
        val location4 = addLocationRelative(location2, Vector2(800f, 400f), InsertDirection.LEFT, area,
            InsertDirection.MIDDLE, ::getFireLandsLocationFourObjects,DefaultLocationData(), Vector2(0f,100f))
        return area
        //addLocationsToArea(area)

    }
}