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
            Vector2(500f, 500f), Vector2(0f, 0f), ::getFireLandsLocationOneObjects
        )
        addLocation(location1, area)
        val location2 = addLocationRelative(location1, Vector2(1200f, 1000f), InsertDirection.UP, area,
            InsertDirection.MIDDLE, ::getFireLandsLocationTwoObjects,DamageLocationData())
        val location3 = addLocationRelative(location2, Vector2(200f, 1000f), InsertDirection.UP, area,
            InsertDirection.MIDDLE, ::getFireLandsLocationThreeObjects,DefaultLocationData())
        val location4 = addLocationRelative(location2, Vector2(800f, 400f), InsertDirection.LEFT, area,
            InsertDirection.MIDDLE, ::getFireLandsLocationFourObjects,DefaultLocationData(), Vector2(0f,100f))
        val location5 = addLocationRelative(location3, Vector2(1500f, 1500f), InsertDirection.UP, area,
            InsertDirection.MIDDLE, ::getFireLandsLocationFiveObjects,DamageLocationData())
        val location6 = addLocationRelative(location4, Vector2(300f, 500f), InsertDirection.DOWN, area,
            InsertDirection.MIDDLE, { listOf()},DefaultLocationData())
        val location7 = addLocationRelative(location6, Vector2(1000f, 1000f), InsertDirection.DOWN, area,
            InsertDirection.MIDDLE, ::getFireLandsLocationSevenObjects,DamageLocationData())

        val location8 = addLocationRelative(location4, Vector2(800f,200f),InsertDirection.LEFT,area,InsertDirection.LEFT,{ listOf()})

        val location9 = addLocationRelative(location8, Vector2(600f,600f),InsertDirection.LEFT,area,InsertDirection.LEFT,::getFireLandsLocationNineObjects)
        val location10 = addLocationRelative(location2, Vector2(400f, 200f), InsertDirection.RIGHT, area,
            InsertDirection.MIDDLE, { listOf() }, DefaultLocationData(), Vector2(0f,100f)
        )

        val location11 = addLocationRelative(location10, Vector2(2000f, 500f), InsertDirection.RIGHT, area,
            InsertDirection.MIDDLE, ::getFireLandsLocationElevenObjects, DamageLocationData())

        location11.addAdjacentLocation(location2)


        return area
        //addLocationsToArea(area)

    }
}