package com.mygdx.game.Areas

import com.mygdx.game.Interfaces.Area
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.AreaManager

class DefaultArea(override val identifier: AreaIdentifier): Area {
    override val defaultLocations : List<DefaultLocation>
        get() = Locations.toList()
    private val Locations = mutableListOf<DefaultLocation>()
    override fun addLocation(defaultLocation: DefaultLocation){
        defaultLocation.locationName = "location" + (Locations.size + 1)
        Locations.add(defaultLocation)
    }
    init {
        AreaManager.addArea(this)
    }
}