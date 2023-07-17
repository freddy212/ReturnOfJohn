package com.mygdx.game.Areas.FireLands

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.Areas.DefaultArea
import com.mygdx.game.Areas.IceLands.*
import com.mygdx.game.Interfaces.Area
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Interfaces.AreaInitializer
import com.mygdx.game.Locations.DamageLocationData
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Locations.DefaultLocationData

class IceLandsInitializer: AreaInitializer {
    override fun initializeArea(): Area {
        val iceTexture = "IceDamage.jpg"
        val area = DefaultArea(AreaIdentifier.ICELANDS)
        val location1 = DefaultLocation(
            Vector2(1500f, 1500f), Vector2(0f, 0f), ::getIceLandsLocationOneObjects, DamageLocationData(iceTexture)
        )
        addLocation(location1, area)
        val location2 = addLocationRelative(location1, Vector2(300f,800f),InsertDirection.UP,area,
                                            InsertDirection.MIDDLE, ::getIceLandsLocationTwoObjects,DefaultLocationData(), Vector2(16f,0f))
        val location3 = addLocationRelative(location1,Vector2(1200f,400f),InsertDirection.RIGHT,area,
            InsertDirection.MIDDLE, ::getIceLandsLocationThreeObjects,DefaultLocationData(), Vector2(0f,150f)
        )
        val location4 = addLocationRelative(location1, Vector2(800f, 300f), InsertDirection.LEFT, area, InsertDirection.MIDDLE, ::getIceLandsLocationEightObjects, DefaultLocationData(), Vector2(0f,150f))
        val location5 = addLocationRelative(location4, Vector2(400f, 1500f), InsertDirection.UP, area, InsertDirection.LEFT, ::getIceLandsLocationNineObjects)
        val location6 = addLocationRelative(location2, Vector2(800f, 300f), InsertDirection.LEFT, area,
            InsertDirection.UP)
        val location7 = addLocationRelative(location6, Vector2(300f, 500f), InsertDirection.UP, area,
            InsertDirection.LEFT, ::getIceLandsLocationElevenObjects)
        val location8 = addLocationRelative(location7, Vector2(1500f, 1500f), InsertDirection.UP, area,
            InsertDirection.MIDDLE, ::getIceLandsLocationTwelveObjects,DamageLocationData(iceTexture))
        location4.addAdjacentLocation(location1)
        location5.addAdjacentLocation(location1)
        location5.addAdjacentLocation(location3)
        location1.addAdjacentLocation(location5)
        return area
        //addLocationsToArea(area)

    }
}