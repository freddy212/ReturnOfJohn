package com.mygdx.game.Areas.DungeonArea

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.DoorCollition
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.ItemType
import com.mygdx.game.Enums.getDirectionUnitVector
import com.mygdx.game.GameObjects.Hazards.Generators.BoulderGenerator
import com.mygdx.game.GameObjects.ItemObjects.AbilityItemObject
import com.mygdx.game.GameObjects.Other.Door
import com.mygdx.game.GameObjects.ItemObjects.GenericInventoryItemObject
import com.mygdx.game.GameObjects.Gates.LockedDoor
import com.mygdx.game.GameObjects.Other.DefaultBreakableObject
import com.mygdx.game.GameObjects.Other.Sign
import com.mygdx.game.GameObjects.Terrain.WalkableTerrain
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.ItemAbilities.ShieldAbility
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Signal.signalConvert

fun getLocationOneCaveObjects(): List<GameObject>{
    val location1 = LocationManager.findLocation("location1",AreaIdentifier.DUNGEONAREA)
    val location2 = LocationManager.findLocation("location2",AreaIdentifier.DUNGEONAREA)
    val doorPosition = Vector2(location1.originalMiddle.x -  (playerSize.x / 2),location1.bottomleft.y)
    val doorCollition = DoorCollition(doorPosition,AreaIdentifier.WASTELAND, doorWastelandAndDungeonConnection,Direction.DOWN)
    val door = Door(doorPosition, Vector2(32f * 2, 36f * 2), DefaultTextureHandler.getTexture("CaveDoor.png"),location1,Direction.DOWN,doorCollition)
    val boulderGenerator = BoulderGenerator(location2.topright + Vector2(0f,100f), Vector2(128f,128f), getDirectionUnitVector(Direction.RIGHT), location1)
    return listOf(door, boulderGenerator)
}
fun getLocationThreeCaveObjects(): List<GameObject>{
    val location2 = LocationManager.findLocation("location2",AreaIdentifier.DUNGEONAREA)
    val location3 = LocationManager.findLocation("location3",AreaIdentifier.DUNGEONAREA)
    val location5 = LocationManager.findLocation("location5",AreaIdentifier.DUNGEONAREA)
    val walkableTerrain = WalkableTerrain(Vector2(location3.bottomleft.x, location2.bottomleft.y), Vector2(location3.width, location2.height),location3, "dungeonFloor.jpg")
    val walkableTerrain2 = WalkableTerrain(walkableTerrain.initPosition, Vector2(location5.width, location3.topleft.y - walkableTerrain.bottomleft.y),location3, "dungeonFloor.jpg")
    val boulderGenerator1 = BoulderGenerator(Vector2(location3.originalMiddle.x,location3.topleft.y), Vector2(64f * 2,64f * 2), getDirectionUnitVector(Direction.DOWN),location3)
    val boulderGenerator4 = BoulderGenerator(Vector2(location3.originalMiddle.x +400f,location3.topleft.y), Vector2(64f * 2,64f * 2), getDirectionUnitVector(Direction.DOWN),location3)
    return listOf(boulderGenerator1,boulderGenerator4, walkableTerrain, walkableTerrain2)
}
fun getLocationFourCaveObjects():List<GameObject>{
    val location = LocationManager.findLocation("location4",AreaIdentifier.DUNGEONAREA)
    val position = location.initPosition
    val breakableObject = DefaultBreakableObject(position, Vector2(location.size.x,100f),location)
    val signSize = Vector2(80f,80f)
    val sign = Sign(middleOfObject(location.originalMiddle,signSize), signSize, location, "Press I - View Inventory", "Space or Arrow Key - Toggle")
    val WastelandKey = GenericInventoryItemObject(sign.topleft + Vector2(0f,200f), Vector2(80f, 40f), location, ItemType.WASTELANDSKEY)

    return listOf(breakableObject,sign, WastelandKey)

}
fun getLocationFiveCaveObjects(): List<GameObject>{
    val location = LocationManager.findLocation("location5",AreaIdentifier.DUNGEONAREA)
    val boulderGenerator1 = BoulderGenerator(Vector2(location.bottomleft.x,location.topright.y), Vector2(64f * 2,64f * 2), getDirectionUnitVector(Direction.DOWN),location,1f)
    val boulderGenerator2  = BoulderGenerator(Vector2(location.bottomright.x - 128f,location.topright.y), Vector2(64f * 2,64f * 2), getDirectionUnitVector(Direction.DOWN),location,2f)
    val boulderGenerator3 = BoulderGenerator(Vector2(location.originalMiddle.x - 64f,location.topright.y), Vector2(64f * 2,64f * 2), getDirectionUnitVector(Direction.DOWN),location,3f)
    return listOf(boulderGenerator1,boulderGenerator2,boulderGenerator3)
}
fun getLocationSevenCaveObjects(): List<GameObject> {
    val location = LocationManager.findLocation("location7",AreaIdentifier.DUNGEONAREA)
    val size = Vector2(80f,80f)
    val shieldItem = AbilityItemObject(middleOfObject(location.originalMiddle,size),size,location, ShieldAbility(), DefaultTextureHandler.getTexture("shield-front.png"))

    val sign = Sign(shieldItem.bottomleft - Vector2(200f, 0f), Vector2(80f,80f), location, "Wooden Shield - 2", "Weak to Ice and Fire")
    return listOf(shieldItem, sign)
}