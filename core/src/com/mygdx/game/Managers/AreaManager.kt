package com.mygdx.game.Managers

import com.mygdx.game.Interfaces.Area
import com.mygdx.game.Interfaces.AreaIdentifier

class AreaManager{
    companion object{
        private val Areas: MutableList<Area> = mutableListOf()
        val areas : List<Area>
            get() = Areas.toList()
        fun addArea(area:Area){
            Areas.add(area)
        }
        fun getArea(areaIdentifier: AreaIdentifier): Area{
            return areas.find { x -> x.identifier == areaIdentifier }!!
        }
    }
}