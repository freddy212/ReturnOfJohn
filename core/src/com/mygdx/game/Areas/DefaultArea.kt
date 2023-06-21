package com.mygdx.game.Areas

import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Animation.AreaTitleTextAnimation
import com.mygdx.game.Interfaces.Area
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.AnimationManager
import com.mygdx.game.Managers.AreaManager
import com.mygdx.game.Managers.SignalManager
import com.mygdx.game.Signal.Signals.AreaChangeSignal
import com.mygdx.game.UI.Map.Map
import com.mygdx.game.div
import com.mygdx.game.player
import com.mygdx.game.plus
import com.mygdx.game.updateMap

class DefaultArea(override val identifier: AreaIdentifier): Area {
    override val defaultLocations : List<DefaultLocation>
        get() = Locations.toList()
    override var onAreaChangedActions: MutableList<() -> Unit> = mutableListOf()
    private val Locations = mutableListOf<DefaultLocation>()

    val showTitleFunction = ::showTitleText

    init {
        onAreaChangedActions.add { updateMap(this) }
        onAreaChangedActions.add(showTitleFunction)
    }
    override fun addLocation(defaultLocation: DefaultLocation){
        defaultLocation.locationName = "location" + (Locations.size + 1)
        defaultLocation.areaIdentifier = identifier
        Locations.add(defaultLocation)
    }
    init {
        AreaManager.addArea(this)
    }

    fun showTitleText(){
        val areaChangeSignals = SignalManager.pastSignals.List.filter { it is AreaChangeSignal && it.areaIdentifier == this.identifier}

        if(areaChangeSignals.isEmpty()){
            SignalManager.emitSignal(AreaChangeSignal(this.identifier))
            AnimationManager.animationManager.add(AreaTitleTextAnimation(this.identifier))
            onAreaChangedActions.remove(showTitleFunction)
        }
    }
}