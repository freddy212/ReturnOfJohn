package com.mygdx.game.Areas.MainArea

import com.mygdx.game.DefaultTextureHandler
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.*
import com.mygdx.game.Enums.*
import com.mygdx.game.GameObjects.GenericGameObject
import com.mygdx.game.Events.ToggleCollitionEvent
import com.mygdx.game.GameObjects.*
import com.mygdx.game.GameObjects.ItemObjects.GenericItemObject
import com.mygdx.game.GameObjects.MoveableEntities.Boulder
import com.mygdx.game.GameObjects.MoveableEntities.NPC
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.ObjectProperties.Fire
import com.mygdx.game.SaveHandling.DefaultRemoveObjectSaveState
import com.mygdx.game.SaveHandling.DefaultSaveableObject
import com.mygdx.game.SaveHandling.FileHandler
import com.mygdx.game.SaveState.SaveStateEntity
import com.mygdx.game.UI.Dialogue.Conversations.GetFireConversation
import com.mygdx.game.UI.Dialogue.Conversations.GetFireFixedConversation
import com.mygdx.game.UI.Dialogue.Conversations.GetFireNotFixedConversation
import com.mygdx.game.UI.Dialogue.Conversations.GetFirstConversation
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun getLocationOneObjects(): List<GameObject>{
    val location = LocationManager.findLocation("location1",AreaIdentifier.MAINAREA)
    val firstNPC = NPC(Vector2(0f,0f) + Vector2(100f,100f),Vector2(128f,128f),location)
    val firstConversation = GetFirstConversation(firstNPC)
    firstNPC.conversationsHandler.addConversation("first",firstConversation)
    val shop = House(location.middle.x ,location.middle.y, 150f, 200f,location, doorMainAreaAndShop,AreaIdentifier.SHOP)
    val dojo = House(location.topleft.x + 300f,location.topleft.y - 200f,300f,200f,location, doorMainAreaAndDojo,AreaIdentifier.DOJO)

    return listOf(shop,dojo,firstNPC)
}

fun getLocationGraveyard(): List<GameObject>{
    val location4 = LocationManager.findLocation("location4",AreaIdentifier.MAINAREA)
    val graveyardLoc = LocationManager.findLocation("location7",AreaIdentifier.MAINAREA)
    val fence = Fence(Vector2(location4.bottomright.x,location4.bottomright.y - 100f), Vector2(graveyardLoc.middle.x - 50f - location4.bottomright.x,100f),graveyardLoc)
    val fence2 = Fence(Vector2(graveyardLoc.middle.x + 20f,location4.bottomright.y - 100f), Vector2(graveyardLoc.bottomright.x - graveyardLoc.middle.x - 20f,100f),graveyardLoc)
    val cave = GenericGameObject(Vector2(graveyardLoc.middle.x - 256f, graveyardLoc.topleft.y), Vector2(256f * 2, 283f * 2), "Cave.png", Layer.ONGROUND, graveyardLoc)

    val doorPosition = Vector2(cave.middle.x - 64 / 2,cave.bottomleft.y)

    val doorCollition = DoorCollition(doorPosition,AreaIdentifier.DUNGEONAREA, doorMainAreaAndDungeonConnection,Direction.UP)

    val toggleCollition = ToggleCollition(IllegalMoveCollition,doorCollition)

    val door = Door(doorPosition, Vector2(32f * 2,64f * 2),DefaultTextureHandler.getTexture("CaveDoor.png"),graveyardLoc,Direction.UP,toggleCollition)

    val fireExtinguishedEvent = ToggleCollitionEvent(toggleCollition)
    val fire = Fire(door.Position,door.size,fireExtinguishedEvent,door)

    door.properties.add(fire)

    val npc = NPC(middleOfObject(graveyardLoc.middle,Vector2(120f,120f)) - Vector2(0f,450f),Vector2(120f,120f),graveyardLoc)

    val quest = DefaultQuest(npc,QuestIdentifier.FIRE)
    quest.StartQuest()

    npc.conversationsHandler.addConversation("first", GetFireConversation(npc))
    npc.conversationsHandler.addConversation("firefixed", GetFireFixedConversation(npc))
    npc.conversationsHandler.addConversation("firenotfixed", GetFireNotFixedConversation(npc))

    return constructTombs(graveyardLoc) + listOf(fence, fence2,cave,door,npc)

}

fun getLocationFourObjects(): List<GameObject>{
    val location4 = LocationManager.findLocation("location4",AreaIdentifier.MAINAREA)
    val bridge = Bridge(Vector2(location4.middle.x,location4.middle.y - 100f), Vector2(500f, 200f),location4)
    val abyss = Abyss(Vector2(location4.middle.x,location4.bottomleft.y),Vector2(bridge.bottomright.x - bridge.bottomleft.x
            ,bridge.bottomleft.y - location4.bottomleft.y),location4)
    return (listOf(bridge,abyss))
}

fun getWorldTreeObjects(): List<GameObject>{
    val location = LocationManager.findLocation("location8",AreaIdentifier.MAINAREA)
    val tree = Tree(location.middle, Vector2(64f * 2, 128f * 2),location)
    val WorldLeaf = GenericItemObject(tree.topleft + Vector2(0f, 0f), Vector2(64f, 32f), location,Item.WORLDLEAF,DefaultTextureHandler.getTexture("WorldLeaf.png"))
    val WorldLeaf2 = GenericItemObject(tree.bottomright + Vector2(0f, 0f), Vector2(64f, 32f), location,Item.WORLDLEAF,DefaultTextureHandler.getTexture("WorldLeaf.png"))
    return listOf(tree,WorldLeaf,WorldLeaf2)
}
fun getFireLandsLocationTwo(): List<GameObject> {
    /*val listOfBoulderGenerator = mutableListOf<BoulderGenerator>()
    val location = LocationManager.findLocation("location10",AreaIdentifier.MAINAREA)
    val firstBoulderGenerator = BoulderGenerator(location.bottomleft, Vector2(128f,128f), Vector2(0.5f,0.5f),location)
    val secondBoulderGenerator = BoulderGenerator(location.bottomright - Vector2(128f,0f), Vector2(128f,128f),Vector2(-0.5f,0.5f),location)

    listOf(firstBoulderGenerator,secondBoulderGenerator).forEach { if(it !in location.gameObjects) listOfBoulderGenerator.add(it) }
    return listOfBoulderGenerator.toList()*/
    val location2 = LocationManager.findLocation("location2", AreaIdentifier.MAINAREA)
    val location9 = LocationManager.findLocation("location9", AreaIdentifier.MAINAREA)
    val removeDamageCollition = object : MoveCollition by CanMoveCollition {
        override fun filterCollitions(gameObjects: List<GameObject>): List<GameObject> {
            return gameObjects.filter { it !is LocationImpl }
        }
    }
    val fireGateCollition = object : MoveCollition by IllegalMoveCollition{
        override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
            if(entity is Boulder){
                entity.removeFromLocation()
                collidedObject.removeFromLocation()
                if(collidedObject is SaveStateEntity){
                    FileHandler.writeSaveStateEntity(collidedObject)
                }
            }
        }
    }
    val walkableTerrain = GenericGameObject(location2.bottomleft - Vector2(1000f, 0f), Vector2(1000f, location2.topleft.y - location2.bottomleft.y),
            "MainB.jpg", Layer.ONGROUND, location9, removeDamageCollition)

    /*val bridge = GenericGameObject(Vector2(genericGameObject.x,genericGameObject.middle.y), Vector2(60f, 400f),
            "BridgeSideways.png",Layer.AIR,location9,IllegalMoveCollition)*/
    val texture = DefaultTextureHandler.getTexture("LavaFence.png")
    val fence = Fence(Vector2(walkableTerrain.x - texture.width, location9.bottomright.y), Vector2(texture.width.toFloat(), walkableTerrain.bottomleft.y - location9.bottomright.y), location9, texture)
    val fence2 = Fence(Vector2(walkableTerrain.x - texture.width, walkableTerrain.topleft.y), Vector2(texture.width.toFloat(),location9.topright.y - walkableTerrain.topleft.y ), location9, texture)

    val gateTexture = DefaultTextureHandler.getTexture("FireGate.png")

    val fireGate = object : GenericGameObject(Vector2(fence.x,walkableTerrain.bottomleft.y),Vector2(gateTexture.width.toFloat(),walkableTerrain.topleft.y-
                                    walkableTerrain.bottomleft.y),"FireGate.png",Layer.ONGROUND,location9,fireGateCollition), SaveStateEntity by DefaultRemoveObjectSaveState() {}
    val walkableTerrain2 = GenericGameObject(Vector2(location9.bottomleft.x,fireGate.y),Vector2(fireGate.x - location9.bottomleft.x, location2.topleft.y - location2.bottomleft.y) + Vector2(fireGate.size.x,0f),
            "MainB.jpg", Layer.ONGROUND, location9, removeDamageCollition)

    val boulderGenSize = Vector2(128f,128f)
    val boulderGenerator1 = BoulderGenerator(Vector2(location9.bottomleft.x + 350f,location9.bottomleft.y),boulderGenSize, getDirectionUnitVector(Direction.RIGHT) + Vector2(-0.5f,0.5f),location9,1f)
    //val boulderGenerator2 = BoulderGenerator(Vector2(location9.topleft.x + 350f,location9.topleft.y) - Vector2(0f,128f), boulderGenSize, getDirectionUnitVector(Direction.RIGHT) + Vector2(-0.5f,-0.5f),location9,3f)

     val  walkableTerrain3 = GenericGameObject(walkableTerrain2.topleft, Vector2(300f,location9.topleft.y - walkableTerrain2.topleft.y),
            "MainB.jpg", Layer.ONGROUND, location9, removeDamageCollition)

    return listOf(walkableTerrain, fence,fence2,walkableTerrain2,fireGate,boulderGenerator1,walkableTerrain3)
}
