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
import com.mygdx.game.GameObjects.Buttons.DoorButton.DoorButton
import com.mygdx.game.GameObjects.Gates.Fence
import com.mygdx.game.GameObjects.Buttons.IceButton
import com.mygdx.game.GameObjects.Gates.IceGate
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

    //val fenceBeforeGateLeft = Fence(finalGate.initPosition - Vector2(0f,150f), Vector2(location.width / 2 - 100f, 100f), location)
    //val fenceBeforeGateRight= Fence(fenceBeforeGateLeft.bottomright + Vector2(100f,0f), Vector2(200f, 100f), location)
    return listOf(iceGate, gateButton,gateButton2)
}