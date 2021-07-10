package com.mygdx.game.Interfaces

import com.mygdx.game.Locations.DefaultLocation

enum class AreaIdentifier{MAINAREA,DUNGEONAREA,SHOP,DOJO,NOTIMPLEMENTED, FIRELANDS, FIRELANDSDUNGEON, ICELANDS, ICELANDSDUNGEON }

interface Area {
    fun addLocation(defaultLocation: DefaultLocation)
    val defaultLocations: List<DefaultLocation>
    val identifier: AreaIdentifier
}