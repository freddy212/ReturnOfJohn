package com.mygdx.game.Areas.DungeonArea

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.Interfaces.AreaIdentifier

fun initializeDungeon() {
    val dungeonArea = DefaultArea(AreaIdentifier.DUNGEONAREA)
    val location1 = LocationImpl(Vector2(400f, 800f), Vector2(0f, 0f),::getLocationOneCaveObjects,Texture("dungeonFloor.jpg"))
    addLocation(location1, dungeonArea)
    val horizontalHallway = Vector2(1500f, 300f)
    val verticalHallway = Vector2(300f, 800f)
    val location2 = addLocationRelative(location1, horizontalHallway + Vector2(0f,300f), InsertDirection.LEFT, dungeonArea, InsertDirection.MIDDLE,
            ::getLocationTwoCaveObjects,Texture("dungeonFloor.jpg"))
    val location3 = addLocationRelative(location1, verticalHallway, InsertDirection.UP, dungeonArea, InsertDirection.MIDDLE,
            {listOf()},Texture("dungeonFloor.jpg"))
    val location4 = addLocationRelative(location1, horizontalHallway, InsertDirection.RIGHT, dungeonArea, InsertDirection.MIDDLE,
            {listOf()},Texture("dungeonFloor.jpg"))
    val location5 = addLocationRelative(location2, verticalHallway, InsertDirection.LEFT, dungeonArea, InsertDirection.MIDDLE,
            {listOf()},Texture("dungeonFloor.jpg"))
    addLocationsToArea(dungeonArea)
}
