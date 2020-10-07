package com.mygdx.game

import com.mygdx.game.Interfaces.Area
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Managers.AreaManager

class DefaultArea(override val identifier: AreaIdentifier): Area {
    override val locations : List<LocationImpl>
        get() = Locations.toList()
    private val Locations = mutableListOf<LocationImpl>()
    override fun addLocation(location: LocationImpl){
        location.locationName = "location" + (Locations.size + 1)
        Locations.add(location)
    }
    init {
        AreaManager.addArea(this)
    }
}