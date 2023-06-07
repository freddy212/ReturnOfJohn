package com.mygdx.game.Areas.FireLandsDungeon

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.ConveyerBeltStrength
import com.mygdx.game.Collitions.DoorCollition
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.getDirectionUnitVector
import com.mygdx.game.GameObjects.Other.Door
import com.mygdx.game.GameObjects.Hazards.ConveyerBelt.ConveyerBelt
import com.mygdx.game.GameObjects.Hazards.Generators.BoulderGenerator
import com.mygdx.game.ItemAbilities.DashAbility
import com.mygdx.game.GameObjects.ItemObjects.AbilityItemObject
import com.mygdx.game.GameObjects.Terrain.WalkableTerrain
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Managers.LocationManager


fun getFireLandsDungeonLocationOneObjects(): List<GameObject>{
    val location1 = LocationManager.findLocation("location1",AreaIdentifier.FIRELANDSDUNGEON)
    val doorPosition = Vector2(location1.originalMiddle.x,location1.bottomleft.y)
    val doorCollition = DoorCollition(doorPosition,
        AreaIdentifier.FIRELANDS, doorFireLandsAndDungeonConnection,
        Direction.DOWN)
    val door = Door(doorPosition, Vector2(32f * 2, 64f * 2), DefaultTextureHandler.getTexture("CaveDoor.png"),location1,
        Direction.DOWN,doorCollition)
    val conveyerBelt = ConveyerBelt(Vector2(location1.originalMiddle.x + 200f, location1.bottomleft.y + 100f),
        Vector2(400f, location1.size.y - 100f),location1, Direction.DOWN)
    val conveyerBeltTerrain = WalkableTerrain(Vector2(location1.originalMiddle.x + 200f, location1.bottomleft.y + 100f),
        Vector2(400f, location1.size.y - 100f),location1)

    val startTerrain = WalkableTerrain(location1.currentPosition(), Vector2(location1.size.x, 100f), location1)
    val upPath = WalkableTerrain(Vector2(startTerrain.currentPosition() + Vector2(250f, startTerrain.size.y)), Vector2(250f, location1.size.y - startTerrain.size.y), location1)
    val rightPath = WalkableTerrain(Vector2(upPath.topright - Vector2(0f, 200f)), Vector2(location1.size.x  - upPath.topright.x, 200f),location1)
    return listOf(door, conveyerBelt, startTerrain, conveyerBeltTerrain, upPath, rightPath)
}

fun getFireLandsDungeonLocationFourObject(): List<GameObject>{
    val location5 = LocationManager.findLocation("location4", AreaIdentifier.FIRELANDSDUNGEON)
    val conveyerBelt = ConveyerBelt(location5.currentPosition(),location5.size,location5,Direction.DOWN)

    return listOf(conveyerBelt)
}

fun getFireLandsDungeonLocationTwoObjects(): List<GameObject>{
    val location2 = LocationManager.findLocation("location2", AreaIdentifier.FIRELANDSDUNGEON)
    val walkableTerrain = WalkableTerrain(Vector2(location2.bottomleft.x, location2.originalMiddle.y), Vector2(500f,200f),location2)
    val conveyerBeltDown = ConveyerBelt(walkableTerrain.bottomright - Vector2(0f,150f),
        Vector2(250f, 500f),location2,Direction.DOWN)
    val conveyerBeltUp = ConveyerBelt(walkableTerrain.bottomright + Vector2(conveyerBeltDown.size.x, 0f)  - Vector2(0f,150f),
        Vector2(250f,500f),location2,Direction.UP)
    val WalkableTerrainDown = WalkableTerrain(walkableTerrain.bottomright - Vector2(0f,150f),
        Vector2(250f, 500f),location2)
    val WalkableTerrainUp = WalkableTerrain(walkableTerrain.bottomright + Vector2(conveyerBeltDown.size.x, 0f)  - Vector2(0f,150f),
        Vector2(250f,500f),location2)
    val walkableTerrainToNextZone = WalkableTerrain(conveyerBeltDown.topleft,
        Vector2(location2.topright.x - conveyerBeltDown.topleft.x,location2.size.y - conveyerBeltDown.topright.y),location2)



    return listOf(walkableTerrain, conveyerBeltDown,conveyerBeltUp, WalkableTerrainUp,WalkableTerrainDown,walkableTerrainToNextZone)
}

fun getFirelandsDungeonLocationSevenObjects(): List<GameObject>{
    val location7 = LocationManager.findLocation("location7", AreaIdentifier.FIRELANDSDUNGEON)

    val conveyerBelt = ConveyerBelt(location7.initPosition + Vector2(0f,100f),location7.size - Vector2(100f,100f),location7,Direction.DOWN)

    return listOf(conveyerBelt)
}

fun getFireLandsDungeonLocationNineObjects(): List<GameObject>{
    val location9 = LocationManager.findLocation("location9", AreaIdentifier.FIRELANDSDUNGEON)

    val walkableTerrain = WalkableTerrain(Vector2(location9.topright.x - 950f, location9.originalMiddle.y - 100f), Vector2(1000f,200f), location9)
    val walkableTerrain2 = WalkableTerrain(Vector2(walkableTerrain.bottomleft.x, location9.y), Vector2(400f, walkableTerrain.topright.y - location9.y), location9)

    return listOf(walkableTerrain, walkableTerrain2)
}

fun getFirelandsDungeonLocationFiveObjects(): List<GameObject>{
    val location5 = LocationManager.findLocation("location5", AreaIdentifier.FIRELANDSDUNGEON)
    val abilityItem = AbilityItemObject(location5.originalMiddle, Vector2(60f,60f), location5,DashAbility(),DefaultTextureHandler.getTexture("fireball.png"))
    return listOf(abilityItem)
}


fun getFirelandsDungeonLocationElevenObjects(): List<GameObject>{
    val location = LocationManager.findLocation("location11", AreaIdentifier.FIRELANDSDUNGEON)
    val conveyerBeltRight= ConveyerBelt(location.bottomright - Vector2(200f,0f),
        Vector2(199f, 100f),location,Direction.RIGHT)
    return listOf(conveyerBeltRight)
}

fun getFirelandsDungeonLocationFifteenObjects(): List<GameObject>{
    val location = LocationManager.findLocation("location15", AreaIdentifier.FIRELANDSDUNGEON)
    val conveyerBeltDown= ConveyerBelt(location.bottomleft,
        Vector2(location.width - 1, 50f),location,Direction.RIGHT, ConveyerBeltStrength.STRONG)
    val conveyerBeltUp= ConveyerBelt(location.topleft - Vector2(0f,50f),
        Vector2(location.width - 1, 50f),location,Direction.RIGHT, ConveyerBeltStrength.STRONG)
    val boulderGenerator = BoulderGenerator(Vector2(location.currentMiddle.x, location.topleft.y), Vector2(128f,128f), getDirectionUnitVector(Direction.DOWN), location, 0f, 1.0f)
    val boulderGenerator2 = BoulderGenerator(Vector2(location.currentMiddle.x - 156f, location.topleft.y), Vector2(128f,128f), getDirectionUnitVector(Direction.DOWN), location, 0f, 1.0f)
    return listOf(conveyerBeltDown, conveyerBeltUp, boulderGenerator, boulderGenerator2)
}

fun getFirelandsDungeonLocationSixteenObjects(): List<GameObject>{
    val location = LocationManager.findLocation("location16", AreaIdentifier.FIRELANDSDUNGEON)
    val conveyerBeltDown= ConveyerBelt(location.bottomleft,
        Vector2(location.width, location.height),location,Direction.DOWN, ConveyerBeltStrength.STRONG)
    return listOf(conveyerBeltDown)
}