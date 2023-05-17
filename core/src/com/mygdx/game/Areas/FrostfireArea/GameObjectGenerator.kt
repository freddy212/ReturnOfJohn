package com.mygdx.game.Areas.FrostfireArea

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.DataClasses.DoorData
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Element
import com.mygdx.game.Enums.getDirectionUnitVector
import com.mygdx.game.Events.ButtonEvent
import com.mygdx.game.Events.DefaultEvent
import com.mygdx.game.Events.RemoveObjectPermanentlyEvent
import com.mygdx.game.GameObjects.Hazards.ConveyerBelt.ConveyerBelt
import com.mygdx.game.GameObjects.Hazards.Generators.BoulderGenerator
import com.mygdx.game.GameObjects.Buttons.DoorButton.DoorButtonDelayed
import com.mygdx.game.GameObjects.Gates.Fence
import com.mygdx.game.GameObjects.Buttons.IceButton
import com.mygdx.game.GameObjects.Gates.IceGate
import com.mygdx.game.GameObjects.Gates.StopGate
import com.mygdx.game.GameObjects.Hazards.Generators.RocketGenerator
import com.mygdx.game.GameObjects.ItemObjects.HealthObject
import com.mygdx.game.GameObjects.Other.DefaultBreakableObject
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
    val boulderGenerator = BoulderGenerator(location.bottomleft + Vector2(0f,100f), Vector2(128f,128f), getDirectionUnitVector(Direction.RIGHT), location, element = Element.FIRE)
    val walkableTerrain = WalkableTerrain(location.bottomleft + Vector2(0f,650f), Vector2(location.width, 200f), location)
    //val walkableTerrain = WalkableTerrain(location.bottomleft + Vector2(0f,650f), Vector2(location.width, 200f), location)
    return listOf(boulderGenerator, walkableTerrain)
}

fun getFrostFireLocationFiveObjects(): List<GameObject>{
    val location = LocationManager.findLocation("location5", AreaIdentifier.FROSTFIRE)
    val boulderGenerator = BoulderGenerator(location.bottomright + Vector2(0f,300f), Vector2(128f,128f), getDirectionUnitVector(Direction.LEFT), location, element = Element.ICE)
    val walkableTerrain = WalkableTerrain(location.bottomleft + Vector2(0f,650f), Vector2(location.width, 200f), location)
    //val walkableTerrain = WalkableTerrain(location.bottomleft + Vector2(0f,650f), Vector2(location.width, 200f), location)
    return listOf(boulderGenerator, walkableTerrain)
}

fun getFrostFireLocationSevenObjects(): List<GameObject>{
    val location = LocationManager.findLocation("location7", AreaIdentifier.FROSTFIRE)
    val location6 = LocationManager.findLocation("location6", AreaIdentifier.FROSTFIRE)
    val location8 = LocationManager.findLocation("location8", AreaIdentifier.FROSTFIRE)

    val teleportPad2 = TeleportPad(location6.bottomleft + Vector2(200f, 100f), Vector2(100f,50f), location, "padBottom")

    teleportPad2.onLocationEnterActions.add {
        val teleportPad = location8.gameObjects.List.firstOrNull { it is TeleportPad && it.id == "padTop" }
        if(teleportPad != null) {
            val teleportPadCasted = teleportPad as TeleportPad
            if(teleportPadCasted.connectedTeleportPads.isEmpty()){
                teleportPad2.connectedTeleportPads.add(teleportPadCasted)
                teleportPadCasted.connectedTeleportPads.add(teleportPad2)
            }
        }
    }



    val iceGate = IceGate(location.bottomleft,Vector2(location.width,150f), location)
    val removeGateEvent = ButtonEvent(RemoveObjectPermanentlyEvent(iceGate), true)
    val gateButton = IceButton(Vector2(location6.bottomright +  Vector2(-200f, 100f)),Vector2(128f,32f),location,iceGate, removeGateEvent)
    val gateButton2 = IceButton(Vector2(location6.bottomright + Vector2(-550f,100f)),Vector2(128f,32f),location,iceGate, removeGateEvent)

    val rocketGenerator = RocketGenerator(location.bottomleft + Vector2(0f,200f), Vector2(100f,100f), getDirectionUnitVector(Direction.RIGHT), location, 0f,3f,500f,4.5f)

    val breakableObject = DefaultBreakableObject(rocketGenerator.bottomright + Vector2(250f, -25f), Vector2(150f,150f), location)

    val fenceAfterTeleportPadBreakableObject = Fence(rocketGenerator.bottomleft + Vector2(0f,150f), Vector2(location.width, 100f), location)

    val teleportPadBreakableObject = TeleportPad(breakableObject.initPosition + Vector2(25f,50f), Vector2(100f,50f), location)

    val teleportPadAfterFence = TeleportPad(fenceAfterTeleportPadBreakableObject.topleft + Vector2(50f,50f), Vector2(100f,50f), location)

    teleportPadBreakableObject.connectedTeleportPads.add(teleportPadAfterFence)
    teleportPadAfterFence.connectedTeleportPads.add(teleportPadBreakableObject)

    val fenceAfterBreakableObjectLeft = Fence(location.topleft - Vector2(0f,1000f), Vector2(location.width / 2 -50f, 100f), location)
    val fenceAfterBreakableObjectRight= Fence(fenceAfterBreakableObjectLeft.bottomright + Vector2(100f,0f), Vector2(250f, 100f), location)

    val breakableObjectBeforeFence = DefaultBreakableObject(fenceAfterBreakableObjectLeft.bottomright - Vector2(0f,100f), Vector2(100f,150f), location)

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
    return listOf(iceGate, gateButton,gateButton2, fenceBeforeGateRight, fenceBeforeGateLeft, conveyerBelt, gateFence, doorButton1, doorButton2, teleportLeftConveyerBelt, teleportRightConveyerBelt, teleportFence, teleportAboveFenceLeft, teleportAboveFenceRight, rocketGenerator, breakableObject, teleportPadBreakableObject, fenceAfterTeleportPadBreakableObject, teleportPadAfterFence, fenceAfterBreakableObjectLeft, fenceAfterBreakableObjectRight, breakableObjectBeforeFence, teleportPad2)
}

fun getFrostFireLocationEightObjects(): List<GameObject>{
    val location6 = LocationManager.findLocation("location6",AreaIdentifier.FROSTFIRE)
    val location8 = LocationManager.findLocation("location8",AreaIdentifier.FROSTFIRE)
    val teleportPad = TeleportPad(location8.topleft - Vector2(-100f, 100f), Vector2(100f,50f), defaultLocation = location8, id = "padTop")

    val stopGate= StopGate(Vector2(location8.bottomleft.x, teleportPad.y - 300f), Vector2(location8.width, 200f), location8)

    val dummyEvent = ButtonEvent(DefaultEvent())

    val iceButton1 = IceButton(Vector2(stopGate.bottomleft - Vector2(-100f, 100f)), Vector2(128f, 32f), location8,stopGate, dummyEvent)

    val healthObject = HealthObject(iceButton1.initPosition - Vector2(0f,200f), Vector2(60f,60f), location8)

    teleportPad.onLocationEnterActions.add {
        val teleportPad2 = location6.gameObjects.List.firstOrNull { it is TeleportPad && it.id == "padBottom" }
        if(teleportPad2 != null) {
            val teleportPad2Casted = teleportPad2 as TeleportPad
            if(teleportPad2.connectedTeleportPads.isEmpty()){
                teleportPad.connectedTeleportPads.add(teleportPad2Casted)
                teleportPad2Casted.connectedTeleportPads.add(teleportPad)
            }
        }

    }
    return listOf(teleportPad, stopGate, iceButton1, location6, healthObject)
}