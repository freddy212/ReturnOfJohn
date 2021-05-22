package com.mygdx.game.Managers

import com.mygdx.game.Areas.DojoArea.DojoAreaInitializer
import com.mygdx.game.Areas.DungeonArea.DungeonAreaInitializer
import com.mygdx.game.Areas.FireLands.FireLandsInitializer
import com.mygdx.game.Areas.FireLandsDungeon.FireLandsDungeonInitializer
import com.mygdx.game.Areas.MainArea.MainAreaInitializer
import com.mygdx.game.Areas.ShopArea.ShopAreaInitializer
import com.mygdx.game.addLocationsToArea

class AreaInitializerManager(){
    companion object{
        fun init(){
            val listToInit = listOf(DojoAreaInitializer(),DungeonAreaInitializer(),
                MainAreaInitializer(),ShopAreaInitializer(), FireLandsInitializer(),FireLandsDungeonInitializer())
            listToInit.forEach { x -> addLocationsToArea(x.initializeArea()) }
        }
    }
}