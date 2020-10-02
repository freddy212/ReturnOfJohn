package com.mygdx.game.GameObjects.GameEntities

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.GenericGameObject
import com.mygdx.game.GameObjects.*
import com.mygdx.game.LocationImpl
import com.mygdx.game.LocationManager
import com.mygdx.game.constructTombs
import com.mygdx.game.plus


fun getLocationOneObjects(): List<GameObject>{
    val location = LocationManager.findLocation("location1")
    return listOf((House(location.middle.x,location.middle.y, 150f, 200f)))
}

fun getLocationGraveyard(): List<GameObject>{
    val location4 = LocationManager.findLocation("location4")
    val graveyardLoc = LocationManager.findLocation("location7")
    val fence = Fence(Vector2(location4.bottomright.x,location4.bottomright.y - 100f), Vector2(graveyardLoc.middle.x - 50f - location4.bottomright.x,100f))
    val fence2 = Fence(Vector2(graveyardLoc.middle.x + 20f,location4.bottomright.y - 100f), Vector2(graveyardLoc.bottomright.x - graveyardLoc.middle.x - 20f,100f))
    return constructTombs(graveyardLoc) + listOf(fence, fence2)

}

fun getLocationFourObjects(): List<GameObject>{
    val location4 = LocationManager.findLocation("location4")
    val bridge = Bridge(Vector2(location4.middle.x,location4.middle.y - 100f), Vector2(500f, 200f))
    val abyss = Abyss(Vector2(location4.middle.x,location4.bottomleft.y), Vector2(bridge.bottomright.x - bridge.bottomleft.x
            ,bridge.bottomleft.y - location4.bottomleft.y))
    return (listOf(bridge,abyss))
}

fun getWorldTreeObjects(): List<GameObject>{
    val location = LocationManager.findLocation("location8")
    val tree = Tree(location.middle, Vector2(64f * 2, 128f * 2))
    val WorldLeaf = WorldLeaf(tree.bottomleft + Vector2(-80f,70f), Vector2(64f,32f))
    val WorldLeaf2 = WorldLeaf(tree.bottomright + Vector2(0f,70f), Vector2(64f,32f))
    return listOf(tree,WorldLeaf,WorldLeaf2)
}