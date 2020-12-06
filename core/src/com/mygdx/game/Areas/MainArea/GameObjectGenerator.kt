package com.mygdx.game.Areas.MainArea

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.DoorCollition
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.Collitions.ToggleCollition
import com.mygdx.game.GameObjects.GenericGameObject
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Events.DefaultEvent
import com.mygdx.game.Events.ToggleCollitionEvent
import com.mygdx.game.GameObjects.*
import com.mygdx.game.GameObjects.MoveableEntities.NPC
import com.mygdx.game.GameObjects.MoveableEntities.WaterGunSpray
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.ObjectProperties.Fire
import com.mygdx.game.UI.Dialogue.GetFirstConversation

fun getLocationOneObjects(): List<GameObject>{
    val location = LocationManager.findLocation("location1",AreaIdentifier.MAINAREA)
    val firstNPC = NPC(Vector2(0f,0f) + Vector2(100f,100f),Vector2(128f,128f),location)
    val firstConversation = GetFirstConversation(firstNPC)
    firstNPC.conversationsHandler.addConversation("first",firstConversation)

    val fire = Fire(firstNPC.Position,firstNPC.size,DefaultEvent(),firstNPC)

    firstNPC.properties.add(fire)


    val waterGunTest = WaterGunSpray(Vector2(0f,0f),Vector2(20f,200f),location)

    return listOf((House(location.middle.x ,location.middle.y, 150f, 200f,location, doorMainAreaAndShop,AreaIdentifier.SHOP)),firstNPC,waterGunTest)
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

    val door = Door(doorPosition, Vector2(32f * 2,64f * 2),Texture("CaveDoor.png"),graveyardLoc,Direction.UP,toggleCollition)

    val fireExtinguishedEvent = ToggleCollitionEvent(toggleCollition)
    val fire = Fire(door.Position,door.size,fireExtinguishedEvent,door)

    door.properties.add(fire)

    val waterGunTest = WaterGunSpray(Vector2(0f,0f),Vector2(20f,200f),graveyardLoc)

    return constructTombs(graveyardLoc) + listOf(fence, fence2,cave,door,waterGunTest)

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
    val WorldLeaf = WorldLeaf(tree.topleft + Vector2(0f,0f), Vector2(64f,32f),location)
    val WorldLeaf2 = WorldLeaf(tree.bottomright + Vector2(0f,0f), Vector2(64f,32f),location)
    return listOf(tree,WorldLeaf,WorldLeaf2)
}