package com.mygdx.game.Areas.IceLands

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.DoorCollition
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Enums.getDirectionUnitVector
import com.mygdx.game.GameObjects.Door
import com.mygdx.game.GameObjects.Generators.RocketGenerator
import com.mygdx.game.GameObjects.GenericGameObject
import com.mygdx.game.GameObjects.IceButton
import com.mygdx.game.GameObjects.Terrain.IceObject
import com.mygdx.game.GameObjects.Terrain.WalkableTerrain
import com.mygdx.game.GameObjects.Thorns
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Managers.LocationManager

fun getIceLandsLocationOneObjects(): List<GameObject>{
    val location1 = LocationManager.findLocation("location1",AreaIdentifier.ICELANDS)
    val doorPosition = Vector2(location1.originalMiddle.x -  (playerSize.x / 2),location1.bottomleft.y)
    val doorCollition = DoorCollition(doorPosition,
        AreaIdentifier.MAINAREA, doorMainAreaAndIceLands,
        Direction.DOWN)
    val door = Door(doorPosition, Vector2(32f * 2, 64f * 2), DefaultTextureHandler.getTexture("CaveDoor.png"),location1,
        Direction.DOWN,doorCollition)
    val walkableTerrain = WalkableTerrain(Vector2(location1.originalMiddle.x - 150f + door.width / 4,location1.bottomleft.y), Vector2(300f, location1.topleft.y - location1.bottomleft.y), location1)
    val walkableTerrain2 = WalkableTerrain(Vector2(walkableTerrain.originalMiddle - Vector2(walkableTerrain.size.x / 2, 0f)),Vector2(location1.bottomright.x - walkableTerrain.x,300f),location1)

    return listOf(walkableTerrain, door,walkableTerrain2)
}
fun getIceLandsLocationTwoObjects(): List<GameObject>{
    val location2 = LocationManager.findLocation("location2",AreaIdentifier.ICELANDS)
    val gateTexture = DefaultTextureHandler.getTexture("IceGate.png")
    val iceGate = object : GenericGameObject(location2.bottomleft - Vector2(5f,0f),Vector2(location2.width,gateTexture.height.toFloat()),"IceGate.png",
        Layer.ONGROUND,location2, IllegalMoveCollition) {}
    val gateButton = IceButton(Vector2(location2.originalMiddle.x - 64f,location2.bottomleft.y - 250f),Vector2(128f,32f),location2,iceGate)

    return listOf(iceGate, gateButton)
}
fun getIceLandsLocationThreeObjects(): List<GameObject>{
    val location3 = LocationManager.findLocation("location3",AreaIdentifier.ICELANDS)

    val cave = GenericGameObject(Vector2(location3.originalMiddle.x + 85, location3.topleft.y), Vector2(256f * 2, 283f * 2), "Cave.png", Layer.ONGROUND, location3)

    val doorPosition = Vector2(cave.originalMiddle.x - 64 / 2,cave.bottomleft.y)

    val doorCollition = DoorCollition(doorPosition,AreaIdentifier.ICELANDSDUNGEON, doorIceLandsAndDungeonConnection,Direction.UP)

    val door = Door(doorPosition, Vector2(32f * 2,64f * 2),
        DefaultTextureHandler.getTexture("CaveDoor.png"),location3,Direction.UP,doorCollition)
    val thorns = Thorns(door.initPosition - Vector2(8f,64f), Vector2(80f,64f),location3)
    val iceObject = IceObject(thorns.initPosition - Vector2(-10f,200f),Vector2(64f,64f),location3)

    return listOf(cave,door,thorns,iceObject)
}
fun getIceLandsLocationFiveObjects(): List<GameObject> {
    val location5 = LocationManager.findLocation("location5", AreaIdentifier.ICELANDS)
    val walkableTerrain = WalkableTerrain(
        Vector2(location5.originalMiddle.x - 50f, location5.bottomleft.y),
        Vector2(100f, location5.topleft.y - location5.bottomleft.y),
        location5
    )
    val rocketGenerator = RocketGenerator(Vector2(location5.topleft.x, location5.currentMiddle.y), Vector2(100f,100f),
        getDirectionUnitVector(Direction.DOWN),location5)
    val rocketGenerator2 = RocketGenerator(walkableTerrain.currentMiddle - Vector2(200f,0f), Vector2(100f,100f),
        getDirectionUnitVector(Direction.DOWN),location5)
    return listOf(walkableTerrain, rocketGenerator, rocketGenerator2)
}
