package com.mygdx.game.Interfaces

import com.badlogic.gdx.graphics.Color
import com.mygdx.game.Locations.DefaultLocation

enum class AreaIdentifier{MAINAREA,DUNGEONAREA,SHOP,NOTIMPLEMENTED, FIRELANDS, FIRELANDSDUNGEON, ICELANDS, ICELANDSDUNGEON, WASTELAND, FROSTFIRE }

interface Area {
    fun addLocation(defaultLocation: DefaultLocation)
    val defaultLocations: List<DefaultLocation>
    val identifier: AreaIdentifier
    val onAreaChangedActions: MutableList<()-> Unit>
}

fun getAreaTitleText(areaIdentifier: AreaIdentifier) :String{
    return when(areaIdentifier){
        AreaIdentifier.WASTELAND -> "Earth Lands"
        AreaIdentifier.FROSTFIRE -> "Frost Fire Ridge"
        AreaIdentifier.FIRELANDS -> "Fire Lands"
        AreaIdentifier.ICELANDS -> "Ice Lands"
        AreaIdentifier.MAINAREA -> "Central"
        AreaIdentifier.ICELANDSDUNGEON -> "Ice Dungeon"
        AreaIdentifier.DUNGEONAREA -> "Earth Dungeon"
        AreaIdentifier.FIRELANDSDUNGEON -> "Fire Dungeon"
        else -> ".."
    }
}

fun getAreaColor(areaIdentifier: AreaIdentifier): Color{
    return when (areaIdentifier){
        AreaIdentifier.DUNGEONAREA -> Color.BROWN
        AreaIdentifier.FIRELANDSDUNGEON -> Color.RED
        AreaIdentifier.ICELANDSDUNGEON -> Color.TEAL
        AreaIdentifier.ICELANDS -> Color.TEAL
        AreaIdentifier.FIRELANDS -> Color.RED
        AreaIdentifier.FROSTFIRE -> Color.PURPLE
        AreaIdentifier.WASTELAND -> Color.BROWN
        AreaIdentifier.MAINAREA -> Color.WHITE
        else -> Color.WHITE
    }
}

