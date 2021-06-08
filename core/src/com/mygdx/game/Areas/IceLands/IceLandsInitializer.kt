package com.mygdx.game.Areas.FireLands

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.Areas.DefaultArea
import com.mygdx.game.Areas.IceLands.getIceLandsLocationOneObjects
import com.mygdx.game.Interfaces.Area
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Interfaces.AreaInitializer
import com.mygdx.game.Locations.DamageLocationData
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Locations.DefaultLocationData

class IceLandsInitializer: AreaInitializer {
    override fun initializeArea(): Area {
        val iceTexture = DefaultTextureHandler.getTexture("IceGround.png")
        val area = DefaultArea(AreaIdentifier.ICELANDS)
        val location1 = DefaultLocation(
            Vector2(1500f, 1500f), Vector2(0f, 0f), ::getIceLandsLocationOneObjects, DamageLocationData(iceTexture)
        )
        addLocation(location1, area)
        return area
        //addLocationsToArea(area)

    }
}