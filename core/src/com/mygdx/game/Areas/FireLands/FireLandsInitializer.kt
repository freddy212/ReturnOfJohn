package com.mygdx.game.Areas.FireLands

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.Areas.DojoArea.getDojoObjects
import com.mygdx.game.Areas.MainArea.getLocationFourObjects
import com.mygdx.game.Interfaces.Area
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Interfaces.AreaInitializer
import com.mygdx.game.Locations.DamageLocation
import com.mygdx.game.Locations.DefaultLocation

class FireLandsInitializer: AreaInitializer {
    override fun initializeArea(): Area {
        val area = DefaultArea(AreaIdentifier.FIRELANDS)
        val location1 = LocationImpl(
            Vector2(1500f, 1000f), Vector2(0f, 0f), ::getFireLandsLocationOneObjects, DamageLocation()
        )
        addLocation(location1, area)
        val location2 = addLocationRelative(location1, Vector2(2800f, 1000f), InsertDirection.UP, area,
            InsertDirection.MIDDLE, ::getFireLandsLocationTwoObjects,DamageLocation())
        val location3 = addLocationRelative(location2, Vector2(200f, 1000f), InsertDirection.UP, area,
            InsertDirection.MIDDLE, ::getFireLandsLocationThreeObjects,DefaultLocation())
        val location4 = addLocationRelative(location2, Vector2(800f, 400f), InsertDirection.LEFT, area,
            InsertDirection.MIDDLE, ::getFireLandsLocationFourObjects,DefaultLocation(), Vector2(0f,100f))
        return area
        //addLocationsToArea(area)

    }
}