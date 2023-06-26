package com.mygdx.game.Areas.FireLands

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.AbilityId
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.DoorCollition
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.DataClasses.DoorData
import com.mygdx.game.Enums.*
import com.mygdx.game.GameObjects.Gates.FireGate
import com.mygdx.game.GameObjects.Hazards.CircularPlatform
import com.mygdx.game.GameObjects.Hazards.ConveyerBelt.ConveyerBelt
import com.mygdx.game.GameObjects.Hazards.Generators.BoulderGenerator
import com.mygdx.game.GameObjects.Other.Door
import com.mygdx.game.GameObjects.Other.GenericGameObject
import com.mygdx.game.GameObjects.ItemObjects.GenericInventoryItemObject
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.RockBoss.RockBoss
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.SandGhost.Sartan
import com.mygdx.game.GameObjects.Other.DefaultBreakableObject
import com.mygdx.game.GameObjects.Other.Thorns
import com.mygdx.game.GameObjects.Terrain.FireObject
import com.mygdx.game.GameObjects.Terrain.WalkableTerrain
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Managers.SignalManager
import com.mygdx.game.Signal.SignalListeners.ADDMETHODS
import com.mygdx.game.Signal.Signals.AddAbilityItemSignal
import com.mygdx.game.Signal.Signals.AddObjectSignal
import com.mygdx.game.Signal.Signals.RemoveObjectSignal


fun getFireLandsLocationOneObjects(): List<GameObject>{
    val location1 = LocationManager.findLocation("location1",AreaIdentifier.FIRELANDS)
    val doorPosition = Vector2(location1.originalMiddle.x -  (playerSize.x / 2),location1.bottomleft.y)
    val doorCollition = DoorCollition(doorPosition,
        AreaIdentifier.MAINAREA, doorMainAreaAndFireLands,
        Direction.DOWN)
    val door = Door(doorPosition, Vector2(32f * 2, 64f * 2), DefaultTextureHandler.getTexture("CaveDoor.png"),location1,
        Direction.DOWN,doorCollition)
    return listOf(door)
}
fun getFireLandsLocationTwoObjects(): List<GameObject>{
    val location1= LocationManager.findLocation("location1",AreaIdentifier.FIRELANDS)
    val location2 = LocationManager.findLocation("location2",AreaIdentifier.FIRELANDS)
    val walkableTerrain1 =  WalkableTerrain(location1.topleft + Vector2(location1.currentMiddle.x - 250f,0f), Vector2(500f,1000f),location2)
    val walkableTerrain2 = WalkableTerrain(location2.bottomleft + Vector2(0f,500f), Vector2(walkableTerrain1.x - location2.bottomleft.x,200f),location2)

    val walkableTerrain3 = WalkableTerrain(location2.bottomleft + Vector2(walkableTerrain1.width + walkableTerrain2.width,500f), Vector2((location2.width - (walkableTerrain1.width + walkableTerrain2.width)),200f),location2)
    //val walkableTerrain3 = WalkableTerrain(Vector2(walkableTerrain1.bottomright) + Vector2(0f,500f), Vector2(walkableTerrain1.bottomright.x - location2.bottomright.x,200f),location2)
   // val walkableTerrain3 = WalkableTerrain(walkableTerrain1.bottomleft,Vector2(200f,location2.topleft.y - walkableTerrain1.y),location2)
    return listOf(walkableTerrain1,walkableTerrain2, walkableTerrain3)
}
fun getFireLandsLocationThreeObjects(): List<GameObject>{
    val location = LocationManager.findLocation("location3", AreaIdentifier.FIRELANDS)
    val conveyerBelt = ConveyerBelt(location.bottomleft, Vector2(location.width, 140f), location, Direction.DOWN)
    return listOf(conveyerBelt)
}
fun getFireLandsLocationFourObjects():List<GameObject>{
    val location4 = LocationManager.findLocation("location4",AreaIdentifier.FIRELANDS)
    val cave = GenericGameObject(Vector2(location4.originalMiddle.x - 256f, location4.topleft.y), Vector2(256f * 2, 283f * 2), "Cave.png", Layer.ONGROUND, location4)

    val doorPosition = Vector2(cave.originalMiddle.x - 64 / 2,cave.bottomleft.y)

    val doorCollition = DoorCollition(doorPosition,AreaIdentifier.FIRELANDSDUNGEON, doorFireLandsAndDungeonConnection,Direction.UP)

    val door = Door(doorPosition, Vector2(32f * 2,64f * 2),
        DefaultTextureHandler.getTexture("CaveDoor.png"),location4,Direction.UP,doorCollition)
    val fireObject = FireObject(door.initPosition,door.size,door,location4, IllegalMoveCollition)

    return listOf(cave,door,fireObject)
}
fun getFireLandsLocationFiveObjects(): List<GameObject>{
    val location5 = LocationManager.findLocation("location5",AreaIdentifier.FIRELANDS)

    val walkableTerrain = WalkableTerrain(Vector2(location5.originalMiddle.x - 100f,location5.bottomright.y),Vector2(200f,500f),location5)
    val walkableTerrain2 = WalkableTerrain(Vector2(walkableTerrain.topleft - Vector2(300f,0f)), Vector2(800f,600f),location5)
    val sartanPos = Vector2(walkableTerrain2.currentMiddle - Vector2(75f, 0f))

    val addAbilityItemSignal = AddAbilityItemSignal("location5", AreaIdentifier.FIRELANDS, sartanPos.x, sartanPos.y, AbilityId.DASHUPGRADE)
    val sartan = Sartan(sartanPos,Vector2(150f,150f), location5, addAbilityItemSignal)

    return listOf(walkableTerrain,walkableTerrain2, sartan)
}

fun getFireLandsLocationSixObjects(): List<GameObject>{
    val location6 = LocationManager.findLocation("location6",AreaIdentifier.FIRELANDS)

    val fireGate = FireGate(location6.bottomleft, Vector2(location6.width, 100f), location6)

    return listOf(fireGate)
}

fun getFireLandsLocationSevenObjects(): List<GameObject>{
    val location7 = LocationManager.findLocation("location7",AreaIdentifier.FIRELANDS)
    val location6 = LocationManager.findLocation("location6",AreaIdentifier.FIRELANDS)

    val walkableTerrain = WalkableTerrain(Vector2(location6.bottomleft.x,location7.bottomleft.y),Vector2(location6.width,location7.height),location7)
    val addAbilityItemSignal = AddAbilityItemSignal(abilityId = AbilityId.FIREBALL, area = AreaIdentifier.FIRELANDS,x = location7.currentMiddle.x, y = location7.currentMiddle.y, locationName = location7.locationName)
    val RockBoss = RockBoss(location7.currentMiddle, Vector2(150f,160f), location7, Element.FIRE, addAbilityItemSignal)

    val thorns = Thorns(Vector2(location7.currentMiddle.x - 25f,location7.bottomleft.y), Vector2(32f,64f), location7)
    thorns.onRemoveAction.add {
        val removeEvents: List<RemoveObjectSignal> = SignalManager.pastSignals.List.filter { it is RemoveObjectSignal }.map { it as RemoveObjectSignal }
        val thornsRemoved = removeEvents.find { it.entityId == thorns.entityId }
        if(thornsRemoved == null){
            SignalManager.emitSignal(
                AddObjectSignal(
                    ADDMETHODS.FIRELANDSWASTELANDDOOR,"location7",AreaIdentifier.FIRELANDS)
            )
            SignalManager.emitSignal(
                AddObjectSignal(
                    ADDMETHODS.WASTELANDFIRELANDSDOOR,"location6",AreaIdentifier.WASTELAND)
            )
        }
    }
    return listOf(walkableTerrain, thorns, RockBoss)
}


/*
fun getFireLandsLocationNineObjects(): List<GameObject>{
    val location9 = LocationManager.findLocation("location9",AreaIdentifier.FIRELANDS)

    val fluteItem  = GenericInventoryItemObject(location9.currentMiddle, Vector2(64f, 32f), location9,
        ItemType.FLUTEOFAWAKENING)

    return listOf(fluteItem)

}*/

fun getFireLandsLocationNineObjects(): List<GameObject>{
    val location9 = LocationManager.findLocation("location9",AreaIdentifier.FIRELANDS)

    val circularPlatform = CircularPlatform(location9.bottomleft + Vector2(300f, 200f), Vector2(150f,100f), location9)

    val circularPlatform2 = CircularPlatform(circularPlatform.bottomright + Vector2(300f, 0f), Vector2(150f,100f), location9, 180f)

    val circularPlatform3 = CircularPlatform(circularPlatform2.bottomright + Vector2(700f, 0f), Vector2(150f,100f), location9, 0f)


    return listOf(circularPlatform, circularPlatform2, circularPlatform3)
}

fun getFirelandsLocationFourteenObjects(): List<GameObject>{
    val location11 = LocationManager.findLocation("location11",AreaIdentifier.FIRELANDS)
    val location12 = LocationManager.findLocation("location12",AreaIdentifier.FIRELANDS)

    val walkableTerrain = WalkableTerrain(location11.topleft, Vector2(location11.width, location12.height), location12)

    val boulderGenerator1 = BoulderGenerator(location12.bottomleft + Vector2(0f,128f), Vector2(128f,128f), getDirectionUnitVector( Direction.RIGHT), location12, element = Element.FIRE)

    val boulderGenerator2 = BoulderGenerator(location12.bottomright + Vector2(-128f,128 + 225f), Vector2(128f,128f), getDirectionUnitVector( Direction.LEFT), location12, element = Element.FIRE)

    val boulderGenerator3 = BoulderGenerator(location12.bottomleft + Vector2(0f,128 + 450f), Vector2(128f,128f), getDirectionUnitVector( Direction.RIGHT), location12, element = Element.FIRE)

    val breakableObject = DefaultBreakableObject(Vector2(location12.currentMiddle.x - 100f, location12.topleft.y - 64f), Vector2(64f,64f), location12)
    breakableObject.onRemoveAction.add {
        val removeEvents: List<RemoveObjectSignal> = SignalManager.pastSignals.List.filter { it is RemoveObjectSignal }.map { it as RemoveObjectSignal }
        val objectRemoved = removeEvents.find { it.entityId == breakableObject.entityId }
        if(objectRemoved == null){
            SignalManager.emitSignal(
                AddObjectSignal(
                    ADDMETHODS.FIRELANDSTOFROSTFIREDOOR,"location12",AreaIdentifier.FIRELANDS)
            )
            SignalManager.emitSignal(
                AddObjectSignal(
                    ADDMETHODS.FROSTFIRETOFIRELANDSDOOR,"location2",AreaIdentifier.FROSTFIRE)
            )
        }
    }

    return listOf(walkableTerrain, boulderGenerator1, boulderGenerator2, boulderGenerator3, breakableObject)
}


