package com.mygdx.game.Areas

import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Interfaces.Area
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.AreaManager
import com.mygdx.game.UI.Map.Map
import com.mygdx.game.div
import com.mygdx.game.player
import com.mygdx.game.plus

class DefaultArea(override val identifier: AreaIdentifier): Area {
    override val defaultLocations : List<DefaultLocation>
        get() = Locations.toList()
    override val onAreaChangedActions: MutableList<() -> Unit> = mutableListOf()
    private val Locations = mutableListOf<DefaultLocation>()

    init {
        onAreaChangedActions.add(::updateMap)
    }
    override fun addLocation(defaultLocation: DefaultLocation){
        defaultLocation.locationName = "location" + (Locations.size + 1)
        defaultLocation.areaIdentifier = identifier
        Locations.add(defaultLocation)
    }
    init {
        AreaManager.addArea(this)
    }

    fun updateMap(){

        val locations = this.defaultLocations

        val polygons = locations.map {
            val newVertices = it.polygon.transformedVertices.map { vertice -> vertice / 20 }
            Polygon(newVertices.toFloatArray())
        }

        val cumuPos: Vector2 = polygons.fold(Vector2(0f,0f)) { vec, pol ->
             vec + Vector2(pol.vertices[0], pol.vertices[1])
        }

        println("cumu pos :" + cumuPos)
        val middlePos = cumuPos / polygons.size.toFloat()

        println("middlePos" + middlePos)

        Map.offset = middlePos

        Map.currentMap = polygons.zip(locations).toMutableList()
    }
}