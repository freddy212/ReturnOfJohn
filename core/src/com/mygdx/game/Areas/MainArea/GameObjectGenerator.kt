package com.mygdx.game.Areas.MainArea

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.*
import com.mygdx.game.Enums.*
import com.mygdx.game.Events.ButtonEvent
import com.mygdx.game.Events.RemoveObjectPermanentlyEvent
import com.mygdx.game.GameObjects.Buttons.DoorButton.DoorButton
import com.mygdx.game.GameObjects.Gates.Fence
import com.mygdx.game.GameObjects.Hazards.Generators.BoulderGenerator
import com.mygdx.game.GameObjects.ItemObjects.GenericInventoryItemObject
import com.mygdx.game.GameObjects.Other.*
import com.mygdx.game.GameObjects.Terrain.IceObject
import com.mygdx.game.GameObjects.Terrain.WalkableTerrain
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Saving.DefaultSaveStateHandler
import com.mygdx.game.Saving.SaveStateEntity

fun getLocationOneObjects(): List<GameObject>{

    val location1= LocationManager.findLocation("location1", AreaIdentifier.MAINAREA)
    val location2 = LocationManager.findLocation("location2", AreaIdentifier.MAINAREA)
    val sign = Sign(location2.topright + Vector2(50f,-50f), Vector2(80f,80f), defaultLocation = location1, "Left - Firelands")

    return listOf(sign)
}

fun getLocationGraveyard(): List<GameObject>{
    val location4 = LocationManager.findLocation("location4",AreaIdentifier.MAINAREA)
    val graveyardLoc = LocationManager.findLocation("location7",AreaIdentifier.MAINAREA)
    val fence = Fence(Vector2(location4.bottomright.x,location4.bottomright.y - 100f), Vector2(graveyardLoc.originalMiddle.x - 50f - location4.bottomright.x,100f),graveyardLoc)
    val fence2 = Fence(Vector2(graveyardLoc.originalMiddle.x + 20f,location4.bottomright.y - 100f), Vector2(graveyardLoc.bottomright.x - graveyardLoc.originalMiddle.x - 20f,100f),graveyardLoc)
    val cave = GenericGameObject(Vector2(graveyardLoc.originalMiddle.x - 256f, graveyardLoc.topleft.y), Vector2(256f * 2, 283f * 2), "Cave.png", Layer.ONGROUND, graveyardLoc)

    val doorPosition = Vector2(cave.originalMiddle.x - 64 / 2,cave.bottomleft.y)

    val doorCollition = DoorCollition(doorPosition,AreaIdentifier.DUNGEONAREA, doorWastelandAndDungeonConnection,Direction.UP)

    val door = Door(doorPosition, Vector2(32f * 2,36f * 2),
        DefaultTextureHandler.getTexture("CaveDoor.png"),graveyardLoc,Direction.UP,doorCollition)

    return  listOf(fence, fence2,cave,door)

}

fun getLocationFourObjects(): List<GameObject>{
    return (listOf())
}

fun getWorldTreeObjects(): List<GameObject>{
    val location = LocationManager.findLocation("location8",AreaIdentifier.MAINAREA)
    val WorldLeaf = GenericInventoryItemObject(location.currentMiddle, Vector2(64f, 32f), location,ItemType.WORLDLEAF)
    val WorldLeaf2 = GenericInventoryItemObject(location.currentMiddle + Vector2(100f,100f), Vector2(64f, 32f), location,ItemType.WORLDLEAF)
    return listOf(WorldLeaf,WorldLeaf2)
}
fun getIceLandsGateWayLocation():List<GameObject>{
    val location10 = LocationManager.findLocation("location10", AreaIdentifier.MAINAREA)
    val walkableTerrain = WalkableTerrain(location10.bottomleft +  Vector2(location10.width / 2 - 150f,0f), Vector2(300f,location10.height ), location10)
    val iceGrounds = ConstructObjects(::IceObject,walkableTerrain.bottomleft.x.toInt(),100,walkableTerrain.bottomleft.x.toInt() + 199,
        walkableTerrain.bottomleft.y.toInt() + 400,100,walkableTerrain.bottomleft.y.toInt(),location10)

    val fence = Fence(Vector2(walkableTerrain.topleft.x,walkableTerrain.topleft.y - 130f), Vector2(walkableTerrain.width,150f),location10, DefaultTextureHandler.getTexture("FenceGate.png"), false)
    val removeFenceEvent = RemoveObjectPermanentlyEvent(fence)
    val buttonEvent = ButtonEvent(removeFenceEvent)
    val doorButton1 = DoorButton(walkableTerrain.topleft + Vector2(50f,0f), Vector2(40f,30f),location10,buttonEvent)
    val doorButton2 = DoorButton(doorButton1.bottomleft + Vector2(150f,0f), Vector2(40f,30f),location10,buttonEvent)

    val iceObject = IceObject(Vector2(fence.bottomleft.x + 30f, fence.bottomleft.y - 75f),
        Vector2(75f,75f),location10
    )
    val iceObject2 = IceObject(Vector2(iceObject.bottomleft.x + 150f, iceObject.bottomleft.y),Vector2(75f,75f),location10)
    val doorPosition = Vector2(walkableTerrain.originalMiddle.x - (playerSize.x),walkableTerrain.topleft.y)

    val doorCollition = DoorCollition(doorPosition,
        AreaIdentifier.ICELANDS, doorMainAreaAndIceLands,
        Direction.UP)

    val door = Door(doorPosition, Vector2(32f * 2, 64f * 2), DefaultTextureHandler.getTexture("CaveDoor.png"),location10,
        Direction.UP,doorCollition)

    return listOf(walkableTerrain,door) + iceGrounds + iceObject + iceObject2 + fence + doorButton1 + doorButton2
}
fun getFireLandsGateWayLocation(): List<GameObject> {
    val location2 = LocationManager.findLocation("location2", AreaIdentifier.MAINAREA)
    val location9 = LocationManager.findLocation("location9", AreaIdentifier.MAINAREA)
    val walkableTerrain = WalkableTerrain(location2.bottomleft - Vector2(1000f, 0f), Vector2(1000f, location2.topleft.y - location2.bottomleft.y), location9)

    val texture = DefaultTextureHandler.getTexture("LavaFence.png")
    val fence = Fence(Vector2(walkableTerrain.x - texture.width, location9.bottomright.y), Vector2(texture.width.toFloat(), walkableTerrain.bottomleft.y - location9.bottomright.y), location9, texture)
    val fence2 = Fence(Vector2(walkableTerrain.x - texture.width, walkableTerrain.topleft.y), Vector2(texture.width.toFloat(),location9.topright.y - walkableTerrain.topleft.y ), location9, texture)

    val gateTexture = DefaultTextureHandler.getTexture("FireGate.png")

    val fireGate = object : GenericGameObject(Vector2(fence.x,walkableTerrain.bottomleft.y),Vector2(gateTexture.width.toFloat(),walkableTerrain.topleft.y-
                                    walkableTerrain.bottomleft.y),"FireGate.png",Layer.ONGROUND,location9), SaveStateEntity by DefaultSaveStateHandler() {}
    val breakableCollition = BreakableCollition(fireGate)
    fireGate.collition = breakableCollition
    val walkableTerrain2 = WalkableTerrain(Vector2(location9.bottomleft.x,fireGate.y),Vector2(fireGate.x - location9.bottomleft.x, location2.topleft.y - location2.bottomleft.y) + Vector2(fireGate.size.x,0f), location9)

    val boulderGenSize = Vector2(128f,128f)
    val boulderGenerator1 = BoulderGenerator(Vector2(location9.bottomleft.x + 400f,location9.bottomleft.y),boulderGenSize, getDirectionUnitVector(Direction.RIGHT) + Vector2(-0.3f,0.5f),location9,1f)

    val walkableTerrain3 = WalkableTerrain(walkableTerrain2.topleft, Vector2(300f,location9.topleft.y - walkableTerrain2.topleft.y), location9)
    val doorPosition = Vector2(walkableTerrain3.originalMiddle.x - 64 / 2,walkableTerrain3.topleft.y)

    val doorCollition = DoorCollition(doorPosition,AreaIdentifier.FIRELANDS, doorMainAreaAndFireLands,Direction.UP)

    val door = Door(doorPosition, Vector2(32f * 2,64f * 2),
        DefaultTextureHandler.getTexture("CaveDoor.png"),location9,Direction.UP,doorCollition)

    return listOf(walkableTerrain, fence,fence2,walkableTerrain2,fireGate,boulderGenerator1,walkableTerrain3,door)
}

fun getWastelandGatewayLocation(): List<GameObject>{
    val location5 = LocationManager.findLocation("location5", AreaIdentifier.MAINAREA)

    val doorPosition = Vector2(location5.originalMiddle.x - (playerSize.x),location5.bottomleft.y)

    val doorCollition = DoorCollition(doorPosition,
        AreaIdentifier.WASTELAND, doorMainAreaAndWasteland,
        Direction.DOWN)

    val door = Door(doorPosition, Vector2(32f * 2, 32f * 2), DefaultTextureHandler.getTexture("EmptyDoor.png"),location5,
        Direction.DOWN,doorCollition)

    return listOf(door)
}