package com.mygdx.game.Areas.FireLands

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.DOTCollition
import com.mygdx.game.Collitions.DoorCollition
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.Abyss
import com.mygdx.game.GameObjects.Door
import com.mygdx.game.GameObjects.GenericGameObject
import com.mygdx.game.GameObjects.MoveableEntities.Characters.SmallDevil
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
    val walkableTerrain = WalkableTerrain(Vector2(location1.originalMiddle.x - 150f + door.width / 4,location1.bottomleft.y), Vector2(300f, location1.topleft.y - location1.bottomleft.y), location1)
    val devil = SmallDevil(doorPosition + Vector2(0f,300f), Vector2(70f,70f),location1)
    return listOf(door,walkableTerrain,devil)
}
fun getFireLandsLocationTwoObjects(): List<GameObject>{
    val location1= LocationManager.findLocation("location1",AreaIdentifier.FIRELANDS)
    val location2 = LocationManager.findLocation("location2",AreaIdentifier.FIRELANDS)
    val walkableTerrain1 =  WalkableTerrain(location1.topleft + Vector2(location1.currentMiddle.x - 250f + 10f,0f), Vector2(500f,1000f),location2)
    val walkableTerrain2 = WalkableTerrain(location2.bottomleft + Vector2(0f,500f), Vector2(walkableTerrain1.x - location2.bottomleft.x,200f),location2)
   // val walkableTerrain3 = WalkableTerrain(walkableTerrain1.bottomleft,Vector2(200f,location2.topleft.y - walkableTerrain1.y),location2)
    return listOf(walkableTerrain1,walkableTerrain2)
}
fun getFireLandsLocationThreeObjects(): List<GameObject>{
    val location3 = LocationManager.findLocation("location3",AreaIdentifier.FIRELANDS)
    val abyss = Abyss(location3.bottomleft, Vector2(location3.width,200f),location3)
    return listOf(abyss)
}
fun getFireLandsLocationFourObjects():List<GameObject>{
    val location4 = LocationManager.findLocation("location4",AreaIdentifier.FIRELANDS)
    val cave = GenericGameObject(Vector2(location4.originalMiddle.x - 256f, location4.topleft.y), Vector2(256f * 2, 283f * 2), "Cave.png", Layer.ONGROUND, location4)

    val doorPosition = Vector2(cave.originalMiddle.x - 64 / 2,cave.bottomleft.y)

    val doorCollition = DoorCollition(doorPosition,AreaIdentifier.FIRELANDSDUNGEON, doorFireLandsAndDungeonConnection,Direction.UP)

    val door = Door(doorPosition, Vector2(32f * 2,64f * 2),
        DefaultTextureHandler.getTexture("CaveDoor.png"),location4,Direction.UP,doorCollition)
    val fireObject = FireObject(door.Position,door.size,door,location4, IllegalMoveCollition)

    return listOf(cave,door,fireObject)
}

