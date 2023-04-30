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
        val iceTexture = "IceGround.png"
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
        val location4 = addLocationRelative(location3,Vector2(200f,500f),InsertDirection.UP,area,
            InsertDirection.LEFT, { listOf()},DefaultLocationData(), Vector2(200f,0f)
        )
        val location5 = addLocationRelative(location4,Vector2(1000f,1000f),InsertDirection.UP,area,
            InsertDirection.MIDDLE, ::getIceLandsLocationFiveObjects,DamageLocationData(iceTexture)
        )
        val location6 = addLocationRelative(location5,Vector2(200f,500f),InsertDirection.UP,area,
            InsertDirection.MIDDLE
        )
        val location7 = addLocationRelative(location6,Vector2(1000f,1000f),InsertDirection.UP,area,
            InsertDirection.MIDDLE, ::getIceLandsLocationSevenObjects,DamageLocationData(iceTexture)
        )
        location7.addAdjacentLocation(location5)
        val location8 = addLocationRelative(location1, Vector2(800f, 300f), InsertDirection.LEFT, area, InsertDirection.MIDDLE, { listOf()}, DefaultLocationData(), Vector2(0f,150f))
        val location9 = addLocationRelative(location8, Vector2(300f, 1200f), InsertDirection.UP, area, InsertDirection.LEFT, ::getIceLandsLocationNineObjects)
        val location10 = addLocationRelative(location2, Vector2(800f, 300f), InsertDirection.LEFT, area,
            InsertDirection.UP)
        val location11 = addLocationRelative(location10, Vector2(300f, 500f), InsertDirection.UP, area,
            InsertDirection.LEFT)
        val location12 = addLocationRelative(location11, Vector2(1500f, 1500f), InsertDirection.UP, area,
            InsertDirection.MIDDLE, ::getIceLandsLocationTwelveObjects,DamageLocationData(iceTexture))
        return area
        //addLocationsToArea(area)

    }
}