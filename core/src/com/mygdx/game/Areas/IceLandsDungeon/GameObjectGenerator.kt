package com.mygdx.game.Areas.IceLandsDungeon

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.BreakableCollition
import com.mygdx.game.Collitions.DoorCollition
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.ItemType
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Enums.getDirectionUnitVector
import com.mygdx.game.Events.ButtonEvent
import com.mygdx.game.Events.RemoveObjectPermanentlyEvent
import com.mygdx.game.GameObjects.Buttons.DoorButton.DoorButton
import com.mygdx.game.GameObjects.Buttons.IceButton
import com.mygdx.game.GameObjects.Gates.Fence
import com.mygdx.game.GameObjects.Gates.IceGate
import com.mygdx.game.GameObjects.Hazards.Generators.IceGenerator
import com.mygdx.game.GameObjects.Hazards.Generators.RocketGenerator
import com.mygdx.game.ItemAbilities.IceCloneAbility
import com.mygdx.game.GameObjects.ItemObjects.AbilityItemObject
import com.mygdx.game.GameObjects.ItemObjects.GenericInventoryItemObject
import com.mygdx.game.GameObjects.Other.*
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Saving.DefaultSaveStateHandler
import com.mygdx.game.Saving.SaveStateEntity

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

    val fence = Fence(Vector2(door.initPosition.x - 16f, location1.topright.y - 100f) , Vector2(120f,120f),location1, DefaultTextureHandler.getTexture("FenceGate.png"), false)
    val IceKey = GenericInventoryItemObject(fence.currentMiddle - Vector2(40f,16f), Vector2(80f, 40f), location1, ItemType.ICELANDSKEY)
    val removeFenceEvent = ButtonEvent(RemoveObjectPermanentlyEvent(fence))
    val doorButton = DoorButton( Vector2(iceGate.currentMiddle.x - 20f, location1.bottomleft.y - 30f), Vector2(40f,30f),location1,removeFenceEvent, Direction.DOWN)

    val rocketGenerator = RocketGenerator(location1.topleft - Vector2(0f,250f), Vector2(100f,100f), getDirectionUnitVector(Direction.RIGHT), location1, 0f,3f,400f,4.5f)
    return listOf(door, iceGate, gateButton, gateButton2, fence, doorButton, IceKey, rocketGenerator)
}

fun getIceLandsDungeonLocationTwoObjects(): List<GameObject>{
    val location2 = LocationManager.findLocation("location2",AreaIdentifier.ICELANDSDUNGEON)
    val iceGenerator = IceGenerator(location2.topleft - Vector2(0f,100f), Vector2(100f,100f), getDirectionUnitVector(Direction.DOWN),location2,0f,0.7f)
    val iceCloneAbility = AbilityItemObject(location2.topleft + Vector2(-500f,-300f),
                                           Vector2(80f,80f),
                                           location2,
                                           IceCloneAbility(),
                                           DefaultTextureHandler.getTexture("player.png")


    )
    val sign = Sign(iceCloneAbility.bottomleft - Vector2(0f, 100f), Vector2(80f,80f), location2, "Ice Clone - 4", "A frozen clone of yourself")
    return listOf(iceGenerator,iceCloneAbility, sign)
}

fun getIceLandsDungeonLocationThreeObjects(): List<GameObject>{
    val location3 = LocationManager.findLocation("location3",AreaIdentifier.ICELANDSDUNGEON)
    val rocketGenerator = RocketGenerator(location3.topleft - Vector2(0f,250f), Vector2(100f,100f), getDirectionUnitVector(Direction.RIGHT), location3, 0f,3f,400f,4.5f)
    val defaultBreakableObject = DefaultBreakableObject(location3.bottomleft - Vector2(0f,100f), Vector2(100f, 200f), location3)
    return listOf(rocketGenerator, defaultBreakableObject)
}
fun getIceLandsDungeonLocationFourObjects(): List<GameObject>{
    val location4 = LocationManager.findLocation("location4",AreaIdentifier.ICELANDSDUNGEON)
    val breakableObject = DefaultBreakableObject(Vector2(location4.bottomright.x - 300f, location4.currentMiddle.y),Vector2(100f,100f),location4)
    val vest = GenericInventoryItemObject(breakableObject.currentMiddle - Vector2(32f, 0f), Vector2(64f,32f), location4, ItemType.VEST)
    return listOf(vest, breakableObject)
}