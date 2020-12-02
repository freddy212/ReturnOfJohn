package com.mygdx.game.Interfaces

import com.mygdx.game.LocationImpl

enum class AreaIdentifier{MAINAREA,DUNGEONAREA,NOTIMPLEMENTED,SHOP}

interface Area {
    fun addLocation(location: LocationImpl)
    val locations: List<LocationImpl>
    val identifier: AreaIdentifier
}