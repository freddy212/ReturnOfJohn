package com.mygdx.game.Areas.MainArea

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.GameObjects.Fence
import com.mygdx.game.Interfaces.Area
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Interfaces.AreaInitializer
import com.mygdx.game.Locations.DamageLocation
import com.mygdx.game.Managers.LocationManager
class MainAreaInitializer: AreaInitializer {
    override fun initializeArea(): Area {
        val area = DefaultArea(AreaIdentifier.MAINAREA)
        val location1 = LocationImpl(Vector2(1500f, 1500f), Vector2(0f, 0f), ::getLocationOneObjects)
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
        LocationManager.SetArea(area)
        return area
    }
    private fun createFireLands(location2: LocationImpl, area: Area){
        /*val location9 = addLocationRelative(location2, Vector2(1200f, 128f), InsertDirection.LEFT, area, InsertDirection.MIDDLE)
        val location10 = addLocationRelative(location9, Vector2(500f, 500f), InsertDirection.DOWN, area, InsertDirection.LEFT,::getFireLandsLocationTwo)
        location10.onLocationEnter = {
            val deadBoulderGenerators = getFireLandsLocationTwo()
            deadBoulderGenerators.forEach { location10.addGameObject(it) }
        }*/
        val fireLandsMainGround = DefaultTextureHandler.getTexture("MainB.jpg")
        val location9 = addLocationRelative(location2, Vector2(1000f, 2000f), InsertDirection.LEFT, area, InsertDirection.MIDDLE,::getFireLandsLocationTwo,DamageLocation())
    }
}