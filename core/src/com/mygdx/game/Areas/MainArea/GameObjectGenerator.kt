package com.mygdx.game.Areas.MainArea

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.GenericGameObject
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.*
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Utils.door1PlayerPos
import com.mygdx.game.Utils.door2PlayerPos


fun getLocationOneObjects(): List<GameObject>{
    val location = LocationManager.findLocation("location1",AreaIdentifier.MAINAREA)
    return listOf((House(location.middle.x ,location.middle.y, 150f, 200f,location)))
}

fun getLocationGraveyard(): List<GameObject>{
    val location4 = LocationManager.findLocation("location4",AreaIdentifier.MAINAREA)
    val graveyardLoc = LocationManager.findLocation("location7",AreaIdentifier.MAINAREA)
    val fence = Fence(Vector2(location4.bottomright.x,location4.bottomright.y - 100f), Vector2(graveyardLoc.middle.x - 50f - location4.bottomright.x,100f),graveyardLoc)
    val fence2 = Fence(Vector2(graveyardLoc.middle.x + 20f,location4.bottomright.y - 100f), Vector2(graveyardLoc.bottomright.x - graveyardLoc.middle.x - 20f,100f),graveyardLoc)
    val cave = GenericGameObject(Vector2(graveyardLoc.middle.x - 256f,graveyardLoc.topleft.y), Vector2(256f * 2,283f * 2), "Cave.png", Layer.ONGROUND,graveyardLoc)
    val door = Door(Vector2(cave.middle.x - 64 / 2,cave.bottomleft.y), Vector2(32f * 2,64f * 2),Texture("CaveDoor.png"),AreaIdentifier.DUNGEONAREA,
            doorMainAreaAndDungeonConnection,Direction.UP,graveyardLoc)
    doorMainAreaAndDungeonConnection.firstEntrance = Vector2(door.middle.x,door.bottomleft.y)
    //doorToCaveEnd = door.playerPosAfter
    return constructTombs(graveyardLoc) + listOf(fence, fence2,cave,door)

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