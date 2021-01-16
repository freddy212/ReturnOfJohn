package com.mygdx.game.Managers

import com.mygdx.game.Areas.DojoArea.DojoAreaInitializer
import com.mygdx.game.Areas.DungeonArea.DungeonAreaInitializer
import com.mygdx.game.Areas.MainArea.MainAreaInitializer
import com.mygdx.game.Areas.ShopArea.ShopAreaInitializer
import com.mygdx.game.Interfaces.AreaInitializer
import com.mygdx.game.ResourceList
import com.mygdx.game.addLocationsToArea

class AreaInitializerManager(){
    companion object{
        fun init(){
            val listToInit = listOf(DojoAreaInitializer(),DungeonAreaInitializer(),MainAreaInitializer(),ShopAreaInitializer())
            listToInit.forEach { x -> addLocationsToArea(x.initializeArea()) }
        }
    }
}