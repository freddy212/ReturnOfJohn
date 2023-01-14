package com.mygdx.game.Managers
import com.mygdx.game.Areas.DungeonArea.DungeonAreaInitializer
import com.mygdx.game.Areas.FireLands.FireLandsInitializer
import com.mygdx.game.Areas.FireLands.IceLandsInitializer
import com.mygdx.game.Areas.FireLandsDungeon.FireLandsDungeonInitializer
import com.mygdx.game.Areas.FireLandsDungeon.IceLandsDungeonInitializer
import com.mygdx.game.Areas.MainArea.MainAreaInitializer
import com.mygdx.game.Areas.ShopArea.FrostFireInitializer
import com.mygdx.game.Areas.WastelandArea.WastelandAreaInitializer
import com.mygdx.game.addLocationsToArea

class AreaInitializerManager(){
    companion object{
        fun init(){
            val listToInit = listOf(DungeonAreaInitializer(),
                MainAreaInitializer(), FireLandsInitializer(),
                FireLandsDungeonInitializer(), IceLandsInitializer(),IceLandsDungeonInitializer(),
                WastelandAreaInitializer(), FrostFireInitializer() )
            listToInit.forEach { x -> addLocationsToArea(x.initializeArea()) }
        }
    }
}