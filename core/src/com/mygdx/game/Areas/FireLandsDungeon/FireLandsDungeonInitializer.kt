package com.mygdx.game.Areas.FireLandsDungeon

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.Areas.DefaultArea
import com.mygdx.game.Areas.FireLands.getFireLandsLocationFourObjects
import com.mygdx.game.InsertDirection.*
import com.mygdx.game.Interfaces.Area
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Interfaces.AreaInitializer
import com.mygdx.game.Locations.DamageLocationData
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Locations.DefaultLocationData

class FireLandsDungeonInitializer: AreaInitializer {
    override fun initializeArea(): Area {
        val area = DefaultArea(AreaIdentifier.FIRELANDSDUNGEON)
        val location1 = DefaultLocation(
            Vector2(1200f, 1200f), Vector2(0f, 0f), ::getFireLandsDungeonLocationOneObjects, DamageLocationData()
        )
        addLocation(location1,area)
        val location2  =  addLocationRelative(location1, Vector2(1000f,1200f),RIGHT,area,MIDDLE, ::getFireLandsDungeonLocationTwoObjects, DamageLocationData())
        val location3  =  addLocationRelative(location2, Vector2(1500f,230f),RIGHT,area,UP)
        val location4  =  addLocationRelative(location3, Vector2(200f,500f),UP,area,RIGHT)
        val location5  =  addLocationRelative(location4, Vector2(500f,500f),UP,area,MIDDLE)
        val location6  =  addLocationRelative(location5, Vector2(600f,200f),LEFT,area,MIDDLE)
        val location7  =  addLocationRelative(location6, Vector2(400f,600f),LEFT,area,MIDDLE, ::getFirelandsDungeonLocationSevenObjects)
        val location8  =  addLocationRelative(location7, Vector2(600f,200f),LEFT,area,MIDDLE)
        val location9 = addLocationRelative(location8, Vector2(950f,800f),LEFT,area,MIDDLE, ::getFireLandsDungeonLocationNineObjects, DamageLocationData())
        val location10 = addLocationRelative(location9, Vector2(400f,400f),DOWN,area,LEFT)

        location10.addAdjacentLocation(location1)
        location8.addAdjacentLocation(location6)
        return area
        //addLocationsToArea(area)

    }
}