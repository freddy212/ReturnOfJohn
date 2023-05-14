package com.mygdx.game.Areas.ShopArea

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Areas.DefaultArea
import com.mygdx.game.Areas.FrostfireArea.getFrostFireLocationFourObjects
import com.mygdx.game.Areas.FrostfireArea.getFrostFireLocationOneObjects
import com.mygdx.game.Areas.FrostfireArea.getFrostFireLocationSevenObjects
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.InsertDirection
import com.mygdx.game.Interfaces.Area
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Interfaces.AreaInitializer
import com.mygdx.game.Locations.DamageLocationData
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Locations.DefaultLocationData
import com.mygdx.game.addLocation
import com.mygdx.game.addLocationRelative

class FrostFireInitializer():AreaInitializer {
    override fun initializeArea(): Area {
        val area = DefaultArea(AreaIdentifier.FROSTFIRE)
        val location1 =
            DefaultLocation(Vector2(300f, 300f), Vector2(0f, 0f), ::getFrostFireLocationOneObjects, DefaultLocationData())
        addLocation(location1, area)
        val location2 =
            addLocationRelative(location1, Vector2(1500f, 300f), InsertDirection.LEFT, area, InsertDirection.UP)
        val location3 =
            addLocationRelative(location2, Vector2(300f, 1500f), InsertDirection.UP, area, InsertDirection.MIDDLE)
        val location4 =
            addLocationRelative(location3, Vector2(600f, 1500f), InsertDirection.LEFT, area, InsertDirection.MIDDLE, ::getFrostFireLocationFourObjects, DamageLocationData())
        val location5 =
            addLocationRelative(location3, Vector2(600f, 1500f), InsertDirection.RIGHT, area, InsertDirection.MIDDLE, { listOf()}, DamageLocationData("IceGround.png"))
        val location6 =
            addLocationRelative(location4, Vector2(1500f, 200f), InsertDirection.LEFT, area, InsertDirection.MIDDLE, { listOf()})
        val location7 =
            addLocationRelative(location6, Vector2(600f, 1300f), InsertDirection.UP, area, InsertDirection.LEFT, ::getFrostFireLocationSevenObjects)
        val location8 =
            addLocationRelative(location7, Vector2(200f, 400f), InsertDirection.UP, area, InsertDirection.MIDDLE)

        location2.addAdjacentLocation(location4)
        location2.addAdjacentLocation(location5)
        location4.addAdjacentLocation(location5)

        return area
    }
}