package com.mygdx.game.Areas.DungeonArea

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.Areas.MainArea.getLocationFourObjects
import com.mygdx.game.Interfaces.AreaIdentifier

fun initializeDungeon() {
    val dungeonArea = DefaultArea(AreaIdentifier.DUNGEONAREA)
    val location1 = LocationImpl(Vector2(400f, 800f), Vector2(0f, 0f),{listOf()},Texture("dungeonFloor.jpg"))
    addLocation(location1, dungeonArea)
    val horizontalHallway = Vector2(1500f, 300f)
    val verticalHallway = Vector2(300f, 800f)
    val location2 = addLocationRelative(location1, horizontalHallway, InsertDirection.LEFT, dungeonArea, InsertDirection.MIDDLE,
            {listOf()},Texture("dungeonFloor.jpg"))
    val location3 = addLocationRelative(location1, verticalHallway, InsertDirection.UP, dungeonArea, InsertDirection.MIDDLE,
            {listOf()},Texture("dungeonFloor.jpg"))
    val location4 = addLocationRelative(location1, horizontalHallway, InsertDirection.RIGHT, dungeonArea, InsertDirection.MIDDLE,
            {listOf()},Texture("dungeonFloor.jpg"))
}
