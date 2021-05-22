package com.mygdx.game.Areas.MainArea

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.Areas.DefaultArea
import com.mygdx.game.Interfaces.Area
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Interfaces.AreaInitializer
import com.mygdx.game.Locations.DamageLocationData
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.LocationManager
class MainAreaInitializer: AreaInitializer {
    override fun initializeArea(): Area {
        val area = DefaultArea(AreaIdentifier.MAINAREA)
        val location1 = DefaultLocation(Vector2(1500f, 1500f), Vector2(0f, 0f), ::getLocationOneObjects)
        addLocation(location1, area)
        val horizontalHallway = Vector2(1500f, 300f)
        val verticalHallway = Vector2(300f, 800f)
        val location2 = addLocationRelative(location1, horizontalHallway, InsertDirection.LEFT, area, InsertDirection.MIDDLE)
        val location3 = addLocationRelative(location1, verticalHallway, InsertDirection.UP, area, InsertDirection.MIDDLE)
        val location4 = addLocationRelative(location1, horizontalHallway, InsertDirection.RIGHT, area, InsertDirection.MIDDLE, ::getLocationFourObjects)
        val location5 = addLocationRelative(location1, verticalHallway, InsertDirection.DOWN, area, InsertDirection.MIDDLE)
        val location6 = addLocationRelative(location2, verticalHallway, InsertDirection.UP, area, InsertDirection.MIDDLE)
        val graveyardLoc = addLocationRelative(location4, Vector2(1000f, 2300f), InsertDirection.RIGHT, area, InsertDirection.MIDDLE, ::getLocationGraveyard)
        val worldTreeLoc = addLocationRelative(location6, Vector2(1000f, 1000f), InsertDirection.UP, area, InsertDirection.MIDDLE, ::getWorldTreeObjects)
        createFireLands(location2,area)
        createIceLands(location3,area)
        LocationManager.SetArea(area)
        return area
    }
    private fun createFireLands(defaultLocation2: DefaultLocation, area: Area){
        val location9 = addLocationRelative(defaultLocation2, Vector2(2000f, 1500f), InsertDirection.LEFT, area, InsertDirection.MIDDLE,::getFireLandsGateWayLocation,DamageLocationData())
    }
    private fun createIceLands(defaultLocation3: DefaultLocation, area: Area){
        val iceLandsMainGround = DefaultTextureHandler.getTexture("IceGround.png")
        val location10 = addLocationRelative(defaultLocation3, Vector2(1500f, 1100f), InsertDirection.UP, area, InsertDirection.MIDDLE,::getIceLandsGateWayLocation,DamageLocationData(iceLandsMainGround))
    }
}