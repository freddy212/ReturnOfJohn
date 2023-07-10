package com.mygdx.game.Areas.IceLands

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.AbilityId
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.ConveyerBeltStrength
import com.mygdx.game.Events.ButtonEvent
import com.mygdx.game.Collitions.DoorCollition
import com.mygdx.game.DataClasses.DoorData
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Element
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Enums.getDirectionUnitVector
import com.mygdx.game.Events.DefaultEvent
import com.mygdx.game.Events.RemoveObjectPermanentlyEvent
import com.mygdx.game.GameObjects.Buttons.DoorButton.DoorButton
import com.mygdx.game.GameObjects.Buttons.DoorButton.DoorButtonDelayed
import com.mygdx.game.GameObjects.Buttons.IceButton
import com.mygdx.game.GameObjects.Gates.Fence
import com.mygdx.game.GameObjects.Gates.IceGate
import com.mygdx.game.GameObjects.Gates.StopGate
import com.mygdx.game.GameObjects.Hazards.ConveyerBelt.ConveyerBelt
import com.mygdx.game.GameObjects.Hazards.Generators.RocketGenerator
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.IceQueen.IceQueen
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.RockBoss.RockBoss
import com.mygdx.game.GameObjects.Other.*
import com.mygdx.game.GameObjects.Terrain.IceObject
import com.mygdx.game.GameObjects.Terrain.TeleportPad
import com.mygdx.game.GameObjects.Terrain.WalkableTerrain
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Managers.SignalManager
import com.mygdx.game.Signal.SignalListeners.ADDMETHODS
import com.mygdx.game.Signal.Signals.AddAbilityItemSignal
import com.mygdx.game.Signal.Signals.AddObjectSignal
import com.mygdx.game.Signal.Signals.RemoveObjectSignal

fun getIceLandsLocationOneObjects(): List<GameObject>{
    val location1 = LocationManager.findLocation("location1",AreaIdentifier.ICELANDS)
    val doorPosition = Vector2(location1.originalMiddle.x -  (playerSize.x / 2),location1.bottomleft.y)
    val doorCollition = DoorCollition(doorPosition,
        AreaIdentifier.MAINAREA, doorMainAreaAndIceLands,
        Direction.DOWN)
    val door = Door(doorPosition, Vector2(32f * 2, 64f * 2), DefaultTextureHandler.getTexture("CaveDoor.png"),location1,
        Direction.DOWN,doorCollition)
    val walkableTerrain = WalkableTerrain(Vector2(location1.originalMiddle.x - 150f + door.width / 4,location1.bottomleft.y), Vector2(300f, location1.topleft.y - location1.bottomleft.y), location1)
    val walkableTerrain2 = WalkableTerrain(Vector2(location1.topleft.x, location1.originalMiddle.y),Vector2(location1.width,300f),location1)

    return listOf(walkableTerrain, door,walkableTerrain2)
}
fun getIceLandsLocationTwoObjects(): List<GameObject>{
    val location2 = LocationManager.findLocation("location2",AreaIdentifier.ICELANDS)
    val iceGate = IceGate(location2.bottomleft - Vector2(5f,0f),Vector2(location2.width,100f), location2)
    val removeGateEvent = ButtonEvent(RemoveObjectPermanentlyEvent(iceGate), true)
    val gateButton = IceButton(Vector2(location2.originalMiddle.x - 158f,location2.bottomleft.y - 250f),Vector2(128f,32f),location2,iceGate, removeGateEvent)
    val gateButton2 = IceButton(Vector2(location2.originalMiddle.x + 30f,location2.bottomleft.y - 250f),Vector2(128f,32f),location2,iceGate, removeGateEvent)

    return listOf(iceGate, gateButton, gateButton2)
}
fun getIceLandsLocationThreeObjects(): List<GameObject>{
    val location3 = LocationManager.findLocation("location3",AreaIdentifier.ICELANDS)

    val cave = GenericGameObject(Vector2(location3.originalMiddle.x + 85, location3.topleft.y), Vector2(256f * 2, 283f * 2), "Cave.png", Layer.ONGROUND, location3)

    val doorPosition = Vector2(cave.originalMiddle.x - 64 / 2,cave.bottomleft.y)

    val doorCollition = DoorCollition(doorPosition,AreaIdentifier.ICELANDSDUNGEON, doorIceLandsAndDungeonConnection,Direction.UP)

    val door = Door(doorPosition, Vector2(32f * 2,50f * 2),
        DefaultTextureHandler.getTexture("CaveDoor.png"),location3,Direction.UP,doorCollition)
    val fence = Fence(Vector2(door.initPosition - Vector2(100f,120f)), Vector2(door.width + 200f,150f),location3, DefaultTextureHandler.getTexture("FenceGate.png"), false)
    val removeFenceEvent = RemoveObjectPermanentlyEvent(fence)
    val buttonEvent = ButtonEvent(removeFenceEvent)
    val doorButton1 = DoorButton( Vector2(door.currentMiddle.x + 50f,door.initPosition.y), Vector2(40f,30f),location3,buttonEvent)
    val doorButton2 = DoorButton(Vector2( door.currentMiddle.x - 100f,door.initPosition.y), Vector2(40f,30f),location3,buttonEvent)
    val iceObject = IceObject(door.initPosition - Vector2(0f,200f),Vector2(64f,64f),location3)

    val sign = Sign(iceObject.bottomleft - Vector2(300f,100f), Vector2(80f,80f), location3, "Hold right-click - Turn around")

    return listOf(cave,door,fence,doorButton1,doorButton2,iceObject, sign)
}
fun getIceLandsLocationFiveObjects(): List<GameObject> {
    val location5 = LocationManager.findLocation("location5", AreaIdentifier.ICELANDS)
    val walkableTerrain = WalkableTerrain(
        Vector2(location5.originalMiddle.x - 100f, location5.bottomleft.y),
        Vector2(200f, location5.topleft.y - location5.bottomleft.y),
        location5
    )
    val rocketGenerator = RocketGenerator(
        Vector2(location5.topleft.x, location5.currentMiddle.y), Vector2(100f,100f),
        getDirectionUnitVector(Direction.RIGHT),location5)
    val rocketGenerator2 = RocketGenerator(Vector2(location5.topright.x - 100f, location5.currentMiddle.y), Vector2(100f,100f),
        getDirectionUnitVector(Direction.LEFT),location5)

    return listOf(walkableTerrain, rocketGenerator, rocketGenerator2)
}

fun getIceLandsLocationEightObjects(): List<GameObject> {
    val location8 = LocationManager.findLocation("location4", AreaIdentifier.ICELANDS)
    val location9 = LocationManager.findLocation("location5", AreaIdentifier.ICELANDS)

    val fence = Fence(location9.bottomleft, Vector2(location9.width - 100f,100f),location8, DefaultTextureHandler.getTexture("FenceGate.png"), false)
    val removeFenceEvent = RemoveObjectPermanentlyEvent(fence)
    val buttonEvent = ButtonEvent(removeFenceEvent)
    val doorButton1 = DoorButtonDelayed(Vector2( location8.topright.x - 80f, location8.topright.y), Vector2(40f,30f),location8,buttonEvent)
    val doorButton2 = DoorButtonDelayed(Vector2( location8.currentMiddle.x + 40f, location8.topright.y), Vector2(40f,30f),location8,buttonEvent)

    doorButton1.otherButtons.add(doorButton2)
    doorButton2.otherButtons.add(doorButton1)

    return listOf(fence, doorButton1, doorButton2)
}


fun getIceLandsLocationNineObjects(): List<GameObject>{
    val location9 = LocationManager.findLocation("location5", AreaIdentifier.ICELANDS)

    val dummyEvent = ButtonEvent(DefaultEvent())
    val stopGate = StopGate(location9.bottomleft + Vector2(0f,300f), Vector2(location9.width - 100f, 82f * 2), location9)
    val stopGate2 = StopGate(location9.bottomleft + Vector2(0f,600f), Vector2(location9.width - 100f, 82f * 2), location9)

    val iceButton1 = IceButton(Vector2(location9.currentMiddle.x - 114f, location9.bottomleft.y + 150f), Vector2(128f, 32f), location9,stopGate, dummyEvent)
    val iceButton2 = IceButton(Vector2(location9.currentMiddle.x - 114f, location9.bottomleft.y + 500f), Vector2(128f, 32f), location9,stopGate2, dummyEvent)

    val conveyerBelt = ConveyerBelt(Vector2(location9.topright.x - 100f, location9.bottomright.y), Vector2(100f, location9.height), location9, Direction.DOWN, ConveyerBeltStrength.STRONG)

    val thorns = Thorns(Vector2(location9.originalMiddle.x - 32f, location9.topleft.y - 64f), Vector2(32f,64f), location9)
    thorns.onRemoveAction.add {
        val removeEvents: List<RemoveObjectSignal> = SignalManager.pastSignals.List.filter { it is RemoveObjectSignal }.map { it as RemoveObjectSignal }
        val thornsRemoved = removeEvents.find { it.entityId == thorns.entityId }
        if(thornsRemoved == null){
            SignalManager.emitSignal(
                AddObjectSignal(
                    ADDMETHODS.ICELANDSFROSTFIREDOOR,"location5",AreaIdentifier.ICELANDS)
            )
            SignalManager.emitSignal(
                AddObjectSignal(
                    ADDMETHODS.FROSTFIREICELANDSDOOR,"location1",AreaIdentifier.FROSTFIRE)
            )
        }
    }

    return listOf(stopGate, stopGate2, iceButton1, iceButton2, thorns, conveyerBelt)
}
fun getIceLandsLocationElevenObjects(): List<GameObject>{
    val location11 = LocationManager.findLocation("location7",AreaIdentifier.ICELANDS)

    val fence = Fence(location11.bottomleft + Vector2(0f,200f), Vector2(location11.width, 100f), location11)

    val teleportBelowFence = TeleportPad(fence.bottomleft - Vector2(-location11.width/2 + 50,100f), Vector2(100f,50f), location11)
    val teleportAboveFence = TeleportPad(fence.topleft - Vector2(-location11.width/2 + 50,- 100f), Vector2(100f,50f), location11)

    teleportAboveFence.connectedTeleportPads.add(teleportBelowFence)
    teleportBelowFence.connectedTeleportPads.add(teleportAboveFence)

    return listOf(fence, teleportAboveFence, teleportBelowFence)
}

fun getIceLandsLocationTwelveObjects(): List<GameObject>{
    val location5 = LocationManager.findLocation("location8",AreaIdentifier.ICELANDS)

    val walkableTerrain = WalkableTerrain(Vector2(location5.originalMiddle.x - 100f,location5.bottomright.y),Vector2(200f,500f),location5)
    val walkableTerrain2 = WalkableTerrain(Vector2(walkableTerrain.topleft - Vector2(300f,0f)), Vector2(800f,600f),location5)

    val teleportBottomLeft = TeleportPad(walkableTerrain2.bottomleft + Vector2(100f,50f), Vector2(100f,50f), location5)
    val teleportBottomRight = TeleportPad(walkableTerrain2.bottomright - Vector2(200f, -50f), Vector2(100f,50f), location5)
    val teleportTopLeft = TeleportPad(walkableTerrain2.topleft - Vector2(-100f,100f), Vector2(100f,50f), location5)
    val teleportTopRight = TeleportPad(walkableTerrain2.topright - Vector2(200f,100f), Vector2(100f,50f), location5)

    teleportBottomLeft.connectedTeleportPads.addAll(mutableListOf(teleportBottomRight, teleportTopLeft, teleportTopRight))
    teleportBottomRight.connectedTeleportPads.addAll(mutableListOf(teleportBottomLeft, teleportTopLeft, teleportTopRight))
    teleportTopLeft.connectedTeleportPads.addAll(mutableListOf(teleportBottomRight, teleportBottomLeft, teleportTopRight))
    teleportTopRight.connectedTeleportPads.addAll(mutableListOf(teleportBottomRight, teleportTopLeft, teleportBottomLeft))

    val iceQueenPosition = walkableTerrain2.currentMiddle - Vector2(75f, 0f)

    val addAbilityItemSignal = AddAbilityItemSignal(abilityId = AbilityId.ICECLONEUPGRADE, area = AreaIdentifier.ICELANDS, x = iceQueenPosition.x, y = iceQueenPosition.y, locationName = location5.locationName)

    val iceQueen = IceQueen(iceQueenPosition,Vector2(150f,150f), location5, listOf<TeleportPad>(teleportBottomLeft,teleportBottomRight, teleportTopLeft, teleportTopRight), addAbilityItemSignal)

    return listOf(walkableTerrain,walkableTerrain2, iceQueen, teleportBottomLeft, teleportBottomRight, teleportTopLeft, teleportTopRight)
}

