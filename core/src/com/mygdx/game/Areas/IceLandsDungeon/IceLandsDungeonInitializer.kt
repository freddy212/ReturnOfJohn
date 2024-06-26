package com.mygdx.game.Areas.FireLandsDungeon

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.Areas.DefaultArea
import com.mygdx.game.Areas.IceLandsDungeon.getIceLandsDungeonLocationOneObjects
import com.mygdx.game.InsertDirection.*
import com.mygdx.game.Interfaces.Area
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Interfaces.AreaInitializer
import com.mygdx.game.Locations.DamageLocationData
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Locations.DefaultLocationData

class IceLandsDungeonInitializer: AreaInitializer {
    override fun initializeArea(): Area {
        val area = DefaultArea(AreaIdentifier.ICELANDSDUNGEON)
        val location1 = DefaultLocation(
            Vector2(2000f, 400f), Vector2(0f, 0f), ::getIceLandsDungeonLocationOneObjects, DefaultLocationData()
        )
        addLocation(location1,area)
        val location2 = addLocationRelative(location1, Vector2(150f,1700f),UP,area,MIDDLE, ::listOf)
        val location3 = addLocationRelative(location1, Vector2(400f,800f),UP,area,LEFT, ::listOf)
        val location4 = addLocationRelative(location3, Vector2(2000f,400f),RIGHT,area,UP, ::listOf)
        val location5 = addLocationRelative(location4, Vector2(400f,800f),UP,area,RIGHT, ::listOf)
        val location6 = addLocationRelative(location5, Vector2(1600f,400f),LEFT,area,UP, ::listOf)
        location4.addAdjacentLocation(location2)
        location6.addAdjacentLocation(location2)
        return area
    }
}