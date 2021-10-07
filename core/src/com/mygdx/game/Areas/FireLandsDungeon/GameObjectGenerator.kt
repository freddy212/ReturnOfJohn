package com.mygdx.game.Areas.FireLandsDungeon

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.DoorCollition
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.getDirectionUnitVector
import com.mygdx.game.GameObjects.Abyss
import com.mygdx.game.GameObjects.Generators.BoulderGenerator
import com.mygdx.game.GameObjects.Door
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Managers.LocationManager


fun getFireLandsDungeonLocationOneObjects(): List<GameObject>{
    val location1 = LocationManager.findLocation("location1",AreaIdentifier.FIRELANDSDUNGEON)
    val doorPosition = Vector2(location1.originalMiddle.x -  (playerSize.x / 2) - location1.width / 3,location1.bottomleft.y)
    val doorCollition = DoorCollition(doorPosition,
        AreaIdentifier.FIRELANDS, doorFireLandsAndDungeonConnection,
        Direction.DOWN)
    val door = Door(doorPosition, Vector2(32f * 2, 64f * 2), DefaultTextureHandler.getTexture("CaveDoor.png"),location1,
        Direction.DOWN,doorCollition)
    val abyss = Abyss(door.Position + Vector2(200f,0f),Vector2(100f,location1.height),location1)
    return listOf(door,abyss)
}
fun getFireLandsDungeonLocationTwoObjects(): List<GameObject>{
    val location2 = LocationManager.findLocation("location2",AreaIdentifier.FIRELANDSDUNGEON)
    //val boulderGenerator1 = BoulderGenerator(Vector2(location2.bottomleft.x + 10f,location2.originalMiddle.y), Vector2(64f * 2,64f * 2), getDirectionUnitVector(Direction.DOWN),location2,1f,3f,true)
    //val boulderGenerator2 = BoulderGenerator(Vector2(location2.bottomleft.x + 128f + 20f,location2.originalMiddle.y), Vector2(64f * 2,64f * 2), getDirectionUnitVector(Direction.DOWN),location2,1f,6f, true)
    val boulderGenerator1 = BoulderGenerator(Vector2(location2.bottomleft.x + 35f,location2.originalMiddle.y), Vector2(230f,230f), getDirectionUnitVector(Direction.DOWN),location2,1f,3f,true)
    return listOf(boulderGenerator1)
}
