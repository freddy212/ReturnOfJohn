package com.mygdx.game.Managers

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Interfaces.Area
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.ResourceList

class AreaManager{
    companion object{
        private val areaManager  = ResourceList<Area>()
        fun addArea(area:Area){
            areaManager.add(area)
        }
        fun getArea(areaIdentifier: AreaIdentifier): Area{
            return areaManager.List.find { x -> x.identifier == areaIdentifier }!!
        }
        fun getAllAreas(): List<Area>{
            return areaManager.List
        }
        fun getAllGameObjects(): List<GameObject>{
            return getAllAreas().flatMap {it.locations}.flatMap {it.gameObjects}
        }
    }
}