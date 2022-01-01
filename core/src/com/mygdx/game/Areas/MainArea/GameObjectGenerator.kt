package com.mygdx.game.Areas.MainArea

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.*
import com.mygdx.game.Enums.*
import com.mygdx.game.GameObjects.GenericGameObject
import com.mygdx.game.GameObjects.*
import com.mygdx.game.GameObjects.Generators.BoulderGenerator
import com.mygdx.game.GameObjects.ItemObjects.GenericItemObject
import com.mygdx.game.GameObjects.MoveableEntities.Characters.NPC
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Boulder
import com.mygdx.game.GameObjects.Terrain.IceObject
import com.mygdx.game.GameObjects.Terrain.WalkableTerrain
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.SaveHandling.DefaultRemoveObjectSaveState
import com.mygdx.game.SaveState.SaveStateEntity
import com.mygdx.game.UI.Dialogue.Conversations.GetFireConversation
import com.mygdx.game.UI.Dialogue.Conversations.GetFireFixedConversation
import com.mygdx.game.UI.Dialogue.Conversations.GetFireNotFixedConversation
import com.mygdx.game.UI.Dialogue.Conversations.GetFirstConversation
import com.mygdx.game.Utils.DefaultQuest

fun getLocationOneObjects(): List<GameObject>{
    val location = LocationManager.findLocation("location1",AreaIdentifier.MAINAREA)
    val firstNPC = NPC(Vector2(0f,0f) + Vector2(100f,100f),Vector2(128f,128f),location)
    val firstConversation = GetFirstConversation(firstNPC)
    firstNPC.conversationsHandler.addConversation("first",firstConversation)
    val shop = House(location.originalMiddle.x ,location.originalMiddle.y, 150f, 200f,location, doorMainAreaAndShop,AreaIdentifier.SHOP)
    val dojo = House(location.topleft.x + 300f,location.topleft.y - 200f,300f,200f,location, doorMainAreaAndDojo,AreaIdentifier.DOJO)

    return listOf(shop,dojo,firstNPC)
}

fun getLocationGraveyard(): List<GameObject>{
    val location4 = LocationManager.findLocation("location4",AreaIdentifier.MAINAREA)
    val graveyardLoc = LocationManager.findLocation("location7",AreaIdentifier.MAINAREA)
    val fence = Fence(Vector2(location4.bottomright.x,location4.bottomright.y - 100f), Vector2(graveyardLoc.originalMiddle.x - 50f - location4.bottomright.x,100f),graveyardLoc)
    val fence2 = Fence(Vector2(graveyardLoc.originalMiddle.x + 20f,location4.bottomright.y - 100f), Vector2(graveyardLoc.bottomright.x - graveyardLoc.originalMiddle.x - 20f,100f),graveyardLoc)
    val cave = GenericGameObject(Vector2(graveyardLoc.originalMiddle.x - 256f, graveyardLoc.topleft.y), Vector2(256f * 2, 283f * 2), "Cave.png", Layer.ONGROUND, graveyardLoc)

    val doorPosition = Vector2(cave.originalMiddle.x - 64 / 2,cave.bottomleft.y)

    val doorCollition = DoorCollition(doorPosition,AreaIdentifier.DUNGEONAREA, doorMainAreaAndDungeonConnection,Direction.UP)

    //val toggleCollition = ToggleCollition(IllegalMoveCollition,doorCollition)

    val door = Door(doorPosition, Vector2(32f * 2,64f * 2),
        DefaultTextureHandler.getTexture("CaveDoor.png"),graveyardLoc,Direction.UP,doorCollition)

    /*val fireExtinguishedEvent = ToggleCollitionEvent(toggleCollition)
    val fire = Fire(door.Position,door.size,fireExtinguishedEvent,door)

    door.properties.add(fire)*/

    val npc = NPC(middleOfObject(graveyardLoc.originalMiddle,Vector2(120f,120f)) - Vector2(0f,450f),Vector2(120f,120f),graveyardLoc)

    val quest = DefaultQuest(npc,QuestIdentifier.FIRE)
    quest.StartQuest()

    npc.conversationsHandler.addConversation("first", GetFireConversation(npc))
    npc.conversationsHandler.addConversation("firefixed", GetFireFixedConversation(npc))
    npc.conversationsHandler.addConversation("firenotfixed", GetFireNotFixedConversation(npc))

    return constructTombs(graveyardLoc) + listOf(fence, fence2,cave,door,npc)

}

fun getLocationFourObjects(): List<GameObject>{
    val location4 = LocationManager.findLocation("location4",AreaIdentifier.MAINAREA)
    val bridge = Bridge(Vector2(location4.originalMiddle.x,location4.originalMiddle.y - 100f), Vector2(500f, 200f),location4)
    val abyss = Abyss(Vector2(location4.originalMiddle.x,location4.bottomleft.y),Vector2(bridge.bottomright.x - bridge.bottomleft.x
            ,bridge.bottomleft.y - location4.bottomleft.y),location4)
    return (listOf(bridge,abyss))
}

fun getWorldTreeObjects(): List<GameObject>{
    val location = LocationManager.findLocation("location8",AreaIdentifier.MAINAREA)
    val tree = Tree(location.originalMiddle, Vector2(64f * 2, 128f * 2),location)
    val WorldLeaf = GenericItemObject(tree.topleft + Vector2(0f, 0f), Vector2(64f, 32f), location,Item.WORLDLEAF,
        DefaultTextureHandler.getTexture("WorldLeaf.png"))
    val WorldLeaf2 = GenericItemObject(tree.bottomright + Vector2(0f, 0f), Vector2(64f, 32f), location,Item.WORLDLEAF,
        DefaultTextureHandler.getTexture("WorldLeaf.png"))
    return listOf(tree,WorldLeaf,WorldLeaf2)
}
fun getIceLandsGateWayLocation():List<GameObject>{
    val location10 = LocationManager.findLocation("location10", AreaIdentifier.MAINAREA)
    val walkableTerrain = GenericGameObject(location10.bottomleft +  Vector2(location10.width / 2 - 150f,0f), Vector2(300f,location10.height ),
        "MainB.jpg", Layer.ONGROUND, location10, RemoveDotDamageCollition)
    val thorns = ConstructObjects(::Thorns,walkableTerrain.topleft.x.toInt(),60,walkableTerrain.topright.x.toInt() - 1,
        walkableTerrain.topleft.y.toInt() - 100, 100,walkableTerrain.topleft.y.toInt() - 200,
        location10)
    val iceGrounds = ConstructObjects(::IceObject,walkableTerrain.bottomleft.x.toInt(),100,walkableTerrain.bottomleft.x.toInt() + 199,
                                    walkableTerrain.bottomleft.y.toInt() + 400,100,walkableTerrain.bottomleft.y.toInt(),location10)
    val iceGrounds2 = ConstructObjects(::IceObject, walkableTerrain.bottomleft.x.toInt() + 100,100,walkableTerrain.topright.x.toInt() - 100,
                                        walkableTerrain.topleft.y.toInt() - 300, 100,walkableTerrain.topleft.y.toInt() - 499,location10)

    val doorPosition = Vector2(walkableTerrain.originalMiddle.x - (playerSize.x),walkableTerrain.topleft.y)

    val doorCollition = DoorCollition(doorPosition,
        AreaIdentifier.ICELANDS, doorMainAreaAndIceLands,
        Direction.UP)

    val door = Door(doorPosition, Vector2(32f * 2, 64f * 2), DefaultTextureHandler.getTexture("CaveDoor.png"),location10,
        Direction.UP,doorCollition)

    return listOf(walkableTerrain,door) + thorns + iceGrounds + iceGrounds2
}
fun getFireLandsGateWayLocation(): List<GameObject> {
    val location2 = LocationManager.findLocation("location2", AreaIdentifier.MAINAREA)
    val location9 = LocationManager.findLocation("location9", AreaIdentifier.MAINAREA)
    val fireGateCollition = object : MoveCollition by IllegalMoveCollition{
        override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
            if(entity is Boulder){
                entity.removeFromLocation()
                collidedObject.removeFromLocation()
            }
        }
    }
    val walkableTerrain = WalkableTerrain(location2.bottomleft - Vector2(1000f, 0f), Vector2(1000f, location2.topleft.y - location2.bottomleft.y), location9)

    val texture = DefaultTextureHandler.getTexture("LavaFence.png")
    val fence = Fence(Vector2(walkableTerrain.x - texture.width, location9.bottomright.y), Vector2(texture.width.toFloat(), walkableTerrain.bottomleft.y - location9.bottomright.y), location9, texture)
    val fence2 = Fence(Vector2(walkableTerrain.x - texture.width, walkableTerrain.topleft.y), Vector2(texture.width.toFloat(),location9.topright.y - walkableTerrain.topleft.y ), location9, texture)

    val gateTexture = DefaultTextureHandler.getTexture("FireGate.png")

    val fireGate = object : GenericGameObject(Vector2(fence.x,walkableTerrain.bottomleft.y),Vector2(gateTexture.width.toFloat(),walkableTerrain.topleft.y-
                                    walkableTerrain.bottomleft.y),"FireGate.png",Layer.ONGROUND,location9,fireGateCollition), SaveStateEntity by DefaultRemoveObjectSaveState() {}
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

    val door = Door(doorPosition, Vector2(32f * 2, 64f * 2), DefaultTextureHandler.getTexture("CaveDoor.png"),location5,
        Direction.DOWN,doorCollition)

    return listOf(door)
}
