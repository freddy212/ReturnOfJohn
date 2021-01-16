package com.mygdx.game.Areas.DungeonArea

import com.mygdx.game.DefaultTextureHandler
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.Interfaces.Area
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Interfaces.AreaInitializer

class DungeonAreaInitializer():AreaInitializer{
    override fun initializeArea(): Area {
        val dungeonArea = DefaultArea(AreaIdentifier.DUNGEONAREA)
        val location1 = LocationImpl(Vector2(400f, 800f), Vector2(0f, 0f), ::getLocationOneCaveObjects, DefaultTextureHandler.getTexture("dungeonFloor.jpg"))
        addLocation(location1, dungeonArea)
        val horizontalHallway = Vector2(1500f, 300f)
        val verticalHallway = Vector2(300f, 800f)
        val location2 = addLocationRelative(location1, Vector2(1500f, 128f), InsertDirection.LEFT, dungeonArea, InsertDirection.MIDDLE,
                { listOf() }, DefaultTextureHandler.getTexture("dungeonFloor.jpg"))
        val location3 = addLocationRelative(location2, horizontalHallway + Vector2(0f, 300f), InsertDirection.LEFT, dungeonArea, InsertDirection.MIDDLE,
                ::getLocationThreeCaveObjects, DefaultTextureHandler.getTexture("dungeonFloor.jpg"))
        val location4 = addLocationRelative(location1, verticalHallway, InsertDirection.UP, dungeonArea, InsertDirection.MIDDLE,
                ::getLocationFourCaveObjects, DefaultTextureHandler.getTexture("dungeonFloor.jpg"))
        val location5 = addLocationRelative(location1, Vector2(1500f, 128f), InsertDirection.RIGHT, dungeonArea, InsertDirection.MIDDLE,
                { listOf() }, DefaultTextureHandler.getTexture("dungeonFloor.jpg"))
        val location6 = addLocationRelative(location3, verticalHallway + Vector2(100f, 0f), InsertDirection.UP, dungeonArea, InsertDirection.LEFT,
                ::getLocationSixCaveObjects, DefaultTextureHandler.getTexture("dungeonFloor.jpg"))
        val location7 = addLocationRelative(location6, horizontalHallway - Vector2(500f, 100f), InsertDirection.LEFT, dungeonArea, InsertDirection.UP,
                { listOf() }, DefaultTextureHandler.getTexture("dungeonFloor.jpg"))
        val location8 = addLocationRelative(location7, Vector2(600f, 600f), InsertDirection.LEFT, dungeonArea, InsertDirection.MIDDLE,
                ::getLocationEightCaveObjects, DefaultTextureHandler.getTexture("dungeonFloor.jpg"))
        val location9 = addLocationRelative(location5, Vector2(128f, 1500f), InsertDirection.RIGHT, dungeonArea, InsertDirection.DOWN, ::getLocationNineCaveObjects, DefaultTextureHandler.getTexture("dungeonFloor.jpg"))
        val location10 = addLocationRelative(location9, Vector2(1500f, 128f), InsertDirection.RIGHT, dungeonArea, InsertDirection.UP,
                ::getLocationTenCaveObjects, DefaultTextureHandler.getTexture("dungeonFloor.jpg"))
        val location11 = addLocationRelative(location10, Vector2(600f, 600f), InsertDirection.RIGHT, dungeonArea, InsertDirection.MIDDLE,
                ::getLocationElevenCaveObjects, DefaultTextureHandler.getTexture("dungeonFloor.jpg"))
        return dungeonArea
        //addLocationsToArea(dungeonArea)
    }
}

