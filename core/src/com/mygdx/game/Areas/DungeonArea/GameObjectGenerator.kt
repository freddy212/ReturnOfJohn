package com.mygdx.game.Areas.DungeonArea

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.DoorCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.ItemType
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Enums.getDirectionUnitVector
import com.mygdx.game.GameObjects.Generators.BoulderGenerator
import com.mygdx.game.GameObjects.Door
import com.mygdx.game.GameObjects.ItemObjects.GenericInventoryItemObject
import com.mygdx.game.GameObjects.ItemObjects.ShieldItemObject
import com.mygdx.game.GameObjects.ItemObjects.WaterGunItemObject
import com.mygdx.game.GameObjects.LockedDoor
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.doorMainAreaAndDungeonConnection
import com.mygdx.game.middleOfObject
import com.mygdx.game.playerSize

fun getLocationOneCaveObjects(): List<GameObject>{
    val location1 = LocationManager.findLocation("location1",AreaIdentifier.DUNGEONAREA)
    val doorPosition = Vector2(location1.originalMiddle.x -  (playerSize.x / 2),location1.bottomleft.y)
    val doorCollition = DoorCollition(doorPosition,AreaIdentifier.MAINAREA, doorMainAreaAndDungeonConnection,Direction.DOWN)
    val door = Door(doorPosition, Vector2(32f * 2, 36f * 2), DefaultTextureHandler.getTexture("CaveDoor.png"),location1,Direction.DOWN,doorCollition)
    return listOf(door)
}
fun getLocationThreeCaveObjects(): List<GameObject>{
    val location2 = LocationManager.findLocation("location3",AreaIdentifier.DUNGEONAREA)
    val boulderGenerator1 = BoulderGenerator(Vector2(location2.originalMiddle.x,location2.topleft.y), Vector2(64f * 2,64f * 2), getDirectionUnitVector(Direction.DOWN),location2)
    val boulderGenerator2 = BoulderGenerator(Vector2(location2.bottomleft.x,location2.originalMiddle.y - 64f), Vector2(64f * 2,64f * 2), getDirectionUnitVector(Direction.RIGHT),location2)
    val boulderGenerator3 = BoulderGenerator(Vector2(location2.originalMiddle.x - 300f,location2.topleft.y), Vector2(64f * 2,64f * 2), getDirectionUnitVector(Direction.DOWN),location2)
    val boulderGenerator4 = BoulderGenerator(Vector2(location2.originalMiddle.x +400f,location2.topleft.y), Vector2(64f * 2,64f * 2), getDirectionUnitVector(Direction.DOWN),location2)
    return listOf(boulderGenerator1,boulderGenerator2,boulderGenerator3,boulderGenerator4)
}
fun getLocationFourCaveObjects():List<GameObject>{
    val location = LocationManager.findLocation("location4",AreaIdentifier.DUNGEONAREA)
    val position = location.Position
    val door = LockedDoor(position, Vector2(location.size.x,100f),location)
    val waterGunSize = Vector2(60f,40f)
    val waterGun = WaterGunItemObject(middleOfObject(location.originalMiddle,waterGunSize),waterGunSize,Layer.AIR,location)
    return listOf(door,waterGun)

}
fun getLocationNineCaveObjects():List<GameObject>{
    val location = LocationManager.findLocation("location9",AreaIdentifier.DUNGEONAREA)
    val boulderGenerator1 = BoulderGenerator(Vector2(location.bottomleft.x + 35f,location.topleft.y - 230f), Vector2(230f,230f), getDirectionUnitVector(Direction.DOWN),location,1f,3f)
    //val boulderGenerator2 = BoulderGenerator(Vector2(location.bottomleft.x + 128f + 20f,location.originalMiddle.y), Vector2(64f * 2,64f * 2), getDirectionUnitVector(Direction.DOWN),location,1f,6f)
    return listOf(boulderGenerator1)
}
fun getLocationSixCaveObjects(): List<GameObject>{
    val location = LocationManager.findLocation("location6",AreaIdentifier.DUNGEONAREA)
    val boulderGenerator1 = BoulderGenerator(Vector2(location.bottomleft.x,location.topright.y), Vector2(64f * 2,64f * 2), getDirectionUnitVector(Direction.DOWN),location,1f)
    val boulderGenerator2  = BoulderGenerator(Vector2(location.bottomright.x - 128f,location.topright.y), Vector2(64f * 2,64f * 2), getDirectionUnitVector(Direction.DOWN),location,2f)
    val boulderGenerator3 = BoulderGenerator(Vector2(location.originalMiddle.x - 64f,location.topright.y), Vector2(64f * 2,64f * 2), getDirectionUnitVector(Direction.DOWN),location,3f)
    return listOf(boulderGenerator1,boulderGenerator2,boulderGenerator3)
}
fun getLocationTenCaveObjects():List<GameObject>{
    val location = LocationManager.findLocation("location10",AreaIdentifier.DUNGEONAREA)
    val boulderGenerator1 = BoulderGenerator(Vector2(location.originalMiddle.x - 64f,location.originalMiddle.y - 64), Vector2(64f * 2,64f * 2), getDirectionUnitVector(Direction.LEFT),location,1f)
    return listOf(boulderGenerator1)
}
fun getLocationEightCaveObjects(): List<GameObject> {
    val location = LocationManager.findLocation("location8",AreaIdentifier.DUNGEONAREA)
    val size = Vector2(80f,80f)
    val shieldItem = ShieldItemObject(middleOfObject(location.originalMiddle,size),size,location)
    return listOf(shieldItem)
}
fun getLocationElevenCaveObjects(): List<GameObject> {
    val location = LocationManager.findLocation("location11",AreaIdentifier.DUNGEONAREA)
    val size = Vector2(80f,40f)
    val key = GenericInventoryItemObject(middleOfObject(location.originalMiddle,size),size,location,ItemType.KEY, DefaultTextureHandler.getTexture("Key.png"))
    return listOf(key)
}