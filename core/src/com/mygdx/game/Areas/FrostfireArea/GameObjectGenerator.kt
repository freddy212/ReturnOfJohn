package com.mygdx.game.Areas.FrostfireArea

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.DataClasses.DoorData
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.getDirectionUnitVector
import com.mygdx.game.Events.ButtonEvent
import com.mygdx.game.Events.RemoveObjectPermanentlyEvent
import com.mygdx.game.GameObjects.Hazards.ConveyerBelt.ConveyerBelt
import com.mygdx.game.GameObjects.Hazards.Generators.BoulderGenerator
import com.mygdx.game.GameObjects.Buttons.DoorButton.DoorButtonDelayed
import com.mygdx.game.GameObjects.Gates.Fence
import com.mygdx.game.GameObjects.Buttons.IceButton
import com.mygdx.game.GameObjects.Gates.IceGate
import com.mygdx.game.GameObjects.Terrain.TeleportPad
import com.mygdx.game.GameObjects.Terrain.WalkableTerrain
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.createDoor
import com.mygdx.game.plus
import com.mygdx.game.minus
fun getFrostFireLocationOneObjects(): List<GameObject>{
    val location = LocationManager.findLocation("location1", AreaIdentifier.FROSTFIRE)
    val door = createDoor(
        DoorData(
            Vector2(location.originalMiddle.x - 32f, location.bottomleft.y),
            AreaIdentifier.FROSTFIRE, AreaIdentifier.ICELANDS,"location1",
            Direction.DOWN,"IcelandsFrostfire")
    )
    return listOf(door)
}
fun getFrostFireLocationFourObjects(): List<GameObject>{
    val location = LocationManager.findLocation("location4", AreaIdentifier.FROSTFIRE)
    val boulderGenerator = BoulderGenerator(location.bottomleft + Vector2(0f,100f), Vector2(128f,128f), getDirectionUnitVector(Direction.RIGHT), location)
    val walkableTerrain = WalkableTerrain(location.bottomleft + Vector2(0f,650f), Vector2(location.width, 200f), location)
    return listOf(boulderGenerator, walkableTerrain)
}

fun getFrostFireLocationSevenObjects(): List<GameObject>{
    val location = LocationManager.findLocation("location7", AreaIdentifier.FROSTFIRE)
    val location6 = LocationManager.findLocation("location6", AreaIdentifier.FROSTFIRE)

    val iceGate = IceGate(location.bottomleft,Vector2(location.width,150f), location)
    val removeGateEvent = ButtonEvent(RemoveObjectPermanentlyEvent(iceGate), true)
    val gateButton = IceButton(Vector2(location6.bottomleft+  Vector2(100f, 100f)),Vector2(128f,32f),location,iceGate, removeGateEvent)
    val gateButton2 = IceButton(Vector2(location6.bottomleft + Vector2(350f,100f)),Vector2(128f,32f),location,iceGate, removeGateEvent)

    val fenceBeforeGateLeft = Fence(location.topleft - Vector2(0f,800f), Vector2(location.width / 2 -50f, 100f), location)
    val fenceBeforeGateRight= Fence(fenceBeforeGateLeft.bottomright + Vector2(100f,0f), Vector2(250f, 100f), location)

    val conveyerBelt = ConveyerBelt(fenceBeforeGateLeft.bottomleft - Vector2(0f,100f), Vector2(location.width, 300f),location, Direction.UP)

    val gateFence = Fence(Vector2(location.currentMiddle.x - 100f,location.topleft.y - 100f), Vector2(200f,100f),location, DefaultTextureHandler.getTexture("FenceGate.png"), false)
    val removeFenceEvent = RemoveObjectPermanentlyEvent(gateFence)
    val buttonEvent = ButtonEvent(removeFenceEvent)
    val doorButton1 = DoorButtonDelayed(Vector2( location.topleft + Vector2(50f,0f)), Vector2(40f,30f),location,buttonEvent)
    val doorButton2 = DoorButtonDelayed(Vector2( location.topright - Vector2(90f,0f)), Vector2(40f,30f),location,buttonEvent)

    doorButton1.otherButtons.add(doorButton2)
    doorButton2.otherButtons.add(doorButton1)

    val teleportLeftConveyerBelt = TeleportPad(conveyerBelt.topleft + Vector2(50f,100f), Vector2(100f,50f), location)
    val teleportRightConveyerBelt = TeleportPad(conveyerBelt.topright + Vector2(-150f,100f), Vector2(100f,50f), location)

    val teleportFence = Fence(Vector2(location.bottomleft.x, teleportLeftConveyerBelt.y + 100f), Vector2(location.width, 100f), location)

    val teleportAboveFenceLeft = TeleportPad(Vector2(teleportFence.currentMiddle.x - 125f, teleportFence.y + 200f), Vector2(100f,50f), location)
    val teleportAboveFenceRight= TeleportPad(Vector2(teleportFence.currentMiddle.x + 25, teleportFence.y + 200f), Vector2(100f,50f), location)

    teleportLeftConveyerBelt.connectedTeleportPads.add(teleportAboveFenceLeft)
    teleportAboveFenceLeft.connectedTeleportPads.add(teleportLeftConveyerBelt)

    teleportRightConveyerBelt.connectedTeleportPads.add(teleportAboveFenceRight)
    teleportAboveFenceRight.connectedTeleportPads.add(teleportRightConveyerBelt)
    //teleportBelowFence.connectedTeleportPads.add(teleportAboveFence)
    return listOf(iceGate, gateButton,gateButton2, fenceBeforeGateRight, fenceBeforeGateLeft, conveyerBelt, gateFence, doorButton1, doorButton2, teleportLeftConveyerBelt, teleportRightConveyerBelt, teleportFence, teleportAboveFenceLeft, teleportAboveFenceRight)
}