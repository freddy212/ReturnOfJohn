package com.mygdx.game.Areas.DungeonArea

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Enums.Direction
import com.mygdx.game.GameObjects.BoulderGenerator
import com.mygdx.game.GameObjects.Door
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Utils.door1PlayerPos
import com.mygdx.game.Utils.door2PlayerPos
import com.mygdx.game.doorMainAreaAndDungeonConnection
import com.mygdx.game.playerSize

fun getLocationOneCaveObjects(): List<GameObject>{
    val location1 = LocationManager.findLocation("location1",AreaIdentifier.DUNGEONAREA)
    println(location1.bottomleft)
    val door = Door(Vector2(location1.middle.x -  (playerSize.x / 2),location1.bottomleft.y), Vector2(32f * 2, 64f * 2), Texture("CaveDoor.png"), AreaIdentifier.MAINAREA,
            doorMainAreaAndDungeonConnection,Direction.DOWN,location1)
    doorMainAreaAndDungeonConnection.secondEntrance = Vector2(door.middle.x,door.bottomleft.y)

    return listOf(door)
}
fun getLocationThreeCaveObjects(): List<GameObject>{
    val location2 = LocationManager.findLocation("location3",AreaIdentifier.DUNGEONAREA)
    val boulderGenerator1 = BoulderGenerator(Vector2(location2.middle.x,location2.topleft.y), Vector2(64f * 2,64f * 2),Direction.DOWN,location2)
    val boulderGenerator2 = BoulderGenerator(Vector2(location2.bottomleft.x,location2.middle.y - 64f), Vector2(64f * 2,64f * 2),Direction.RIGHT,location2)
    val boulderGenerator3 = BoulderGenerator(Vector2(location2.middle.x - 300f,location2.topleft.y), Vector2(64f * 2,64f * 2),Direction.DOWN,location2)
    val boulderGenerator4 = BoulderGenerator(Vector2(location2.middle.x +400f,location2.topleft.y), Vector2(64f * 2,64f * 2),Direction.DOWN,location2)
    return listOf(boulderGenerator1,boulderGenerator2,boulderGenerator3,boulderGenerator4)
}
fun getLocationSixCaveObjects(): List<GameObject>{
    val location = LocationManager.findLocation("location6",AreaIdentifier.DUNGEONAREA)
    val boulderGenerator1 = BoulderGenerator(Vector2(location.bottomleft.x,location.topright.y), Vector2(64f * 2,64f * 2),Direction.DOWN,location,1f)
    val boulderGenerator2  = BoulderGenerator(Vector2(location.bottomright.x - 128f,location.topright.y), Vector2(64f * 2,64f * 2),Direction.DOWN,location,2f)
    val boulderGenerator3 = BoulderGenerator(Vector2(location.middle.x - 64f,location.topright.y), Vector2(64f * 2,64f * 2),Direction.DOWN,location,3f)
    return listOf(boulderGenerator1,boulderGenerator2,boulderGenerator3)
}