package com.mygdx.game.Areas.FireLandsDungeon

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.Areas.DojoArea.getDojoObjects
import com.mygdx.game.Areas.MainArea.getLocationFourObjects
import com.mygdx.game.InsertDirection.*
import com.mygdx.game.Interfaces.Area
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Interfaces.AreaInitializer
import com.mygdx.game.Locations.DamageLocation
import com.mygdx.game.Locations.DefaultLocation

class FireLandsDungeonInitializer: AreaInitializer {
    override fun initializeArea(): Area {
        val area = DefaultArea(AreaIdentifier.FIRELANDSDUNGEON)
        val location1 = LocationImpl(
            Vector2(1585f, 400f), Vector2(0f, 0f), ::getFireLandsDungeonLocationOneObjects, DefaultLocation()
        )
        addLocation(location1,area)
        val location2 = addLocationRelative(location1,Vector2(300f,2000f),UP,area,LEFT, ::getFireLandsDungeonLocationTwoObjects)
        val location3 = addLocationRelative(location2, Vector2(1200f, 800f),RIGHT, area,UP, { listOf()},
                                            DamageLocation()
        )
        val location4 = addLocationRelative(location3, Vector2(400f,location3.bottomright.y - location1.topright.y),DOWN,area,RIGHT)
        location4.addAdjacentLocation(location1)
        location1.addAdjacentLocation(location4)
        return area
        //addLocationsToArea(area)

    }
}