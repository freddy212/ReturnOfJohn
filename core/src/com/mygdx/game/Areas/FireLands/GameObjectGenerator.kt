package com.mygdx.game.Areas.FireLands

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.DoorCollition
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.DataClasses.DoorData
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Element
import com.mygdx.game.Enums.ItemType
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.Hazards.CircularPlatform
import com.mygdx.game.GameObjects.Other.Door
import com.mygdx.game.GameObjects.Other.GenericGameObject
import com.mygdx.game.GameObjects.ItemObjects.GenericInventoryItemObject
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.RockBoss.RockBoss
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.SandGhost.Sartan
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Mobs.SmallDevil
import com.mygdx.game.GameObjects.Terrain.FireObject
import com.mygdx.game.GameObjects.Terrain.WalkableTerrain
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Managers.LocationManager


fun getFireLandsLocationOneObjects(): List<GameObject>{
    val location1 = LocationManager.findLocation("location1",AreaIdentifier.FIRELANDS)
    val doorPosition = Vector2(location1.originalMiddle.x -  (playerSize.x / 2),location1.bottomleft.y)
    val doorCollition = DoorCollition(doorPosition,
        AreaIdentifier.MAINAREA, doorMainAreaAndFireLands,
        Direction.DOWN)
    val door = Door(doorPosition, Vector2(32f * 2, 64f * 2), DefaultTextureHandler.getTexture("CaveDoor.png"),location1,
        Direction.DOWN,doorCollition)
    return listOf(door)
}
fun getFireLandsLocationTwoObjects(): List<GameObject>{
    val location1= LocationManager.findLocation("location1",AreaIdentifier.FIRELANDS)
    val location2 = LocationManager.findLocation("location2",AreaIdentifier.FIRELANDS)
    val walkableTerrain1 =  WalkableTerrain(location1.topleft + Vector2(location1.currentMiddle.x - 250f + 10f,0f), Vector2(500f,1000f),location2)
    val walkableTerrain2 = WalkableTerrain(location2.bottomleft + Vector2(0f,500f), Vector2(walkableTerrain1.x - location2.bottomleft.x,200f),location2)

    val walkableTerrain3 = WalkableTerrain(location2.bottomleft + Vector2(walkableTerrain1.width + walkableTerrain2.width,500f), Vector2((location2.width - (walkableTerrain1.width + walkableTerrain2.width)),200f),location2)
    //val walkableTerrain3 = WalkableTerrain(Vector2(walkableTerrain1.bottomright) + Vector2(0f,500f), Vector2(walkableTerrain1.bottomright.x - location2.bottomright.x,200f),location2)
   // val walkableTerrain3 = WalkableTerrain(walkableTerrain1.bottomleft,Vector2(200f,location2.topleft.y - walkableTerrain1.y),location2)
    return listOf(walkableTerrain1,walkableTerrain2, walkableTerrain3)
}
fun getFireLandsLocationThreeObjects(): List<GameObject>{
    return listOf()
}
fun getFireLandsLocationFourObjects():List<GameObject>{
    val location4 = LocationManager.findLocation("location4",AreaIdentifier.FIRELANDS)
    val cave = GenericGameObject(Vector2(location4.originalMiddle.x - 256f, location4.topleft.y), Vector2(256f * 2, 283f * 2), "Cave.png", Layer.ONGROUND, location4)

    val doorPosition = Vector2(cave.originalMiddle.x - 64 / 2,cave.bottomleft.y)

    val doorCollition = DoorCollition(doorPosition,AreaIdentifier.FIRELANDSDUNGEON, doorFireLandsAndDungeonConnection,Direction.UP)

    val door = Door(doorPosition, Vector2(32f * 2,64f * 2),
        DefaultTextureHandler.getTexture("CaveDoor.png"),location4,Direction.UP,doorCollition)
    val fireObject = FireObject(door.initPosition,door.size,door,location4, IllegalMoveCollition)

    return listOf(cave,door,fireObject)
}
fun getFireLandsLocationFiveObjects(): List<GameObject>{
    val location5 = LocationManager.findLocation("location5",AreaIdentifier.FIRELANDS)

    val walkableTerrain = WalkableTerrain(Vector2(location5.originalMiddle.x - 100f,location5.bottomright.y),Vector2(200f,500f),location5)
    val walkableTerrain2 = WalkableTerrain(Vector2(walkableTerrain.topleft - Vector2(300f,0f)), Vector2(800f,600f),location5)

    val sartan = Sartan(walkableTerrain2.currentMiddle - Vector2(75f, 0f),Vector2(150f,150f), location5)

    return listOf(walkableTerrain,walkableTerrain2, sartan)
}

fun getFireLandsLocationSevenObjects(): List<GameObject>{
    val location7 = LocationManager.findLocation("location7",AreaIdentifier.FIRELANDS)
    val location6 = LocationManager.findLocation("location6",AreaIdentifier.FIRELANDS)

    val walkableTerrain = WalkableTerrain(Vector2(location6.bottomleft.x,location7.bottomleft.y),Vector2(location6.width,location7.height),location7)
    val RockBoss = RockBoss(location7.currentMiddle, Vector2(150f,160f), location7, Element.FIRE)

    val door = createDoor(DoorData(Vector2(walkableTerrain.currentMiddle.x - 25f,location7.bottomleft.y),AreaIdentifier.FIRELANDS,AreaIdentifier.WASTELAND,
    "location7",Direction.DOWN,"WastelandFirelands"))
    return listOf(walkableTerrain, door, RockBoss)
}

fun getFireLandsLocationNineObjects(): List<GameObject>{
    val location9 = LocationManager.findLocation("location9",AreaIdentifier.FIRELANDS)

    val fluteItem  = GenericInventoryItemObject(location9.currentMiddle, Vector2(64f, 32f), location9,
        ItemType.FLUTEOFAWAKENING)

    return listOf(fluteItem)

}

fun getFireLandsLocationElevenObjects(): List<GameObject>{
    val location11 = LocationManager.findLocation("location11",AreaIdentifier.FIRELANDS)

    val circularPlatform = CircularPlatform(location11.bottomleft + Vector2(500f, 200f), Vector2(150f,100f), location11)

    val circularPlatform2 = CircularPlatform(circularPlatform.bottomright + Vector2(300f, 0f), Vector2(150f,100f), location11, 180f)

    val circularPlatform3 = CircularPlatform(circularPlatform2.bottomright + Vector2(600f, 0f), Vector2(150f,100f), location11, 0f)

    return listOf(circularPlatform, circularPlatform2, circularPlatform3)
}


