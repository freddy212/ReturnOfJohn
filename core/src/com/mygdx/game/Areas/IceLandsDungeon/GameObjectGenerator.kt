package com.mygdx.game.Areas.IceLandsDungeon

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.DoorCollition
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.ItemType
import com.mygdx.game.Enums.getDirectionUnitVector
import com.mygdx.game.Events.ButtonEvent
import com.mygdx.game.Events.RemoveObjectPermanentlyEvent
import com.mygdx.game.GameObjects.Hazards.Generators.IceGenerator
import com.mygdx.game.ItemAbilities.IceCloneAbility
import com.mygdx.game.GameObjects.ItemObjects.AbilityItemObject
import com.mygdx.game.GameObjects.ItemObjects.GenericInventoryItemObject
import com.mygdx.game.GameObjects.Other.*
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Managers.LocationManager

fun getIceLandsDungeonLocationOneObjects(): List<GameObject>{
    val location1 = LocationManager.findLocation("location1", AreaIdentifier.ICELANDSDUNGEON)
    val location2 = LocationManager.findLocation("location2", AreaIdentifier.ICELANDSDUNGEON)
    val doorPosition = Vector2(location1.originalMiddle.x +  (playerSize.x / 2) + location1.width / 3,location1.bottomleft.y)
    val doorCollition = DoorCollition(doorPosition,
        AreaIdentifier.ICELANDS, doorIceLandsAndDungeonConnection,
        Direction.DOWN)
    val door = Door(doorPosition, Vector2(32f * 2, 64f * 2), DefaultTextureHandler.getTexture("CaveDoor.png"),location1,
        Direction.DOWN,doorCollition)

    val iceGate = IceGate(location2.bottomleft, Vector2(location2.width,50f), location1)
    val removeGateEvent = ButtonEvent(RemoveObjectPermanentlyEvent(iceGate), true)
    val gateButton = IceButton(Vector2(iceGate.currentMiddle - Vector2(100f + 128f, 150f)),Vector2(128f,32f),location2,iceGate, removeGateEvent)
    val gateButton2 = IceButton(Vector2(iceGate.currentMiddle - Vector2(-100f, 150f)),Vector2(128f,32f),location2,iceGate, removeGateEvent)

    val fence = Fence(Vector2(door.initPosition.x - 16f, location1.topright.y - 100f) , Vector2(100f,120f),location1, DefaultTextureHandler.getTexture("FenceGate.png"), false)
    val WorldLeaf = GenericInventoryItemObject(fence.currentMiddle - Vector2(32f,16f), Vector2(64f, 32f), location1, ItemType.WORLDLEAF)
    val removeFenceEvent = ButtonEvent(RemoveObjectPermanentlyEvent(fence))
    val doorButton = DoorButton( Vector2(iceGate.currentMiddle.x - 20f, location1.bottomleft.y - 30f), Vector2(40f,30f),location1,removeFenceEvent, Direction.DOWN)
    println(doorButton.currentPosition())
    println(door.currentPosition())
    return listOf(door, iceGate, gateButton, gateButton2, fence, doorButton, WorldLeaf)
}

fun getIceLandsDungeonLocationTwoObjects(): List<GameObject>{
    val location2 = LocationManager.findLocation("location2",AreaIdentifier.ICELANDSDUNGEON)
    val iceGenerator = IceGenerator(location2.topleft - Vector2(0f,100f), Vector2(100f,100f), getDirectionUnitVector(Direction.DOWN),location2,0f,0.8f)
    val iceCloneAbility = AbilityItemObject(location2.topleft + Vector2(-500f,-300f),
                                           Vector2(80f,80f),
                                           location2,
                                           IceCloneAbility(),
                                           DefaultTextureHandler.getTexture("IceClone.png")

    )
    return listOf(iceGenerator,iceCloneAbility)
}