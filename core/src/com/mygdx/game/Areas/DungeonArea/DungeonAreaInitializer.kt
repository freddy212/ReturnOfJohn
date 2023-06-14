package com.mygdx.game.Areas.DungeonArea

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.Areas.DefaultArea
import com.mygdx.game.Interfaces.Area
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Interfaces.AreaInitializer
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Locations.DefaultLocationData
import com.mygdx.game.Locations.RepeatedTextureLocationData

class DungeonAreaInitializer():AreaInitializer{
    override fun initializeArea(): Area {
        val dungeonArea = DefaultArea(AreaIdentifier.DUNGEONAREA)
        val location1 = DefaultLocation(Vector2(800f, 800f), Vector2(0f, 0f), ::getLocationOneCaveObjects, DefaultLocationData(
            "dungeonFloor.jpg"))
        addLocation(location1, dungeonArea)
        val horizontalHallway = Vector2(1500f, 300f)
        val verticalHallway = Vector2(500f, 800f)
        val spikeTexture = DefaultTextureHandler.getTexture("Spike.png")
        val wastelandDamageLocation = RepeatedTextureLocationData("dungeonFloor.jpg", spikeTexture)
        val location2 = addLocationRelative(location1, Vector2(800f, 128f), InsertDirection.LEFT, dungeonArea, InsertDirection.MIDDLE,
                { listOf() }, DefaultLocationData("dungeonFloor.jpg"))
        val location3 = addLocationRelative(location2, horizontalHallway + Vector2(0f, 300f), InsertDirection.LEFT, dungeonArea, InsertDirection.MIDDLE,
                ::getLocationThreeCaveObjects, wastelandDamageLocation)
        val location4 = addLocationRelative(location1, verticalHallway, InsertDirection.UP, dungeonArea, InsertDirection.MIDDLE,
                ::getLocationFourCaveObjects, DefaultLocationData("dungeonFloor.jpg"))
        val location5 = addLocationRelative(location3, verticalHallway + Vector2(100f, 0f), InsertDirection.UP, dungeonArea, InsertDirection.LEFT,
                ::getLocationFiveCaveObjects, DefaultLocationData("dungeonFloor.jpg"))
        val location6 = addLocationRelative(location5, horizontalHallway - Vector2(500f, 100f), InsertDirection.LEFT, dungeonArea, InsertDirection.UP,
                { listOf() }, DefaultLocationData("dungeonFloor.jpg"))
        val location7 = addLocationRelative(location6, Vector2(600f, 600f), InsertDirection.LEFT, dungeonArea, InsertDirection.MIDDLE,
                ::getLocationSevenCaveObjects, DefaultLocationData("dungeonFloor.jpg"))

        location4.addAdjacentLocation(location2)
        return dungeonArea
        //addLocationsToArea(dungeonArea)
    }
}

