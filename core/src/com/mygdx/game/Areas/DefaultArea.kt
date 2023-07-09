package com.mygdx.game.Areas

import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.Color
import com.mygdx.game.*
import com.mygdx.game.Animation.FadingTextAnimation
import com.mygdx.game.Events.DelayEvent
import com.mygdx.game.Interfaces.*
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Locations.DefaultLocationData
import com.mygdx.game.Managers.AnimationManager
import com.mygdx.game.Managers.AreaManager
import com.mygdx.game.Managers.EventManager
import com.mygdx.game.Managers.SignalManager
import com.mygdx.game.Signal.Signals.AreaChangeSignal

class DefaultArea(override val identifier: AreaIdentifier) : Area {
    override val defaultLocations: List<DefaultLocation>
        get() = Locations.toList()
    override var onAreaChangedActions: MutableList<() -> Unit> = mutableListOf()
    private val Locations = mutableListOf<DefaultLocation>()


    init {
        onAreaChangedActions.add { updateMap(this) }
        onAreaChangedActions.add(::addShowTitleEvent)
        onAreaChangedActions.add(::changeMusic)
    }

    override fun addLocation(defaultLocation: DefaultLocation) {
        defaultLocation.locationName = "location" + (Locations.size + 1)
        defaultLocation.areaIdentifier = identifier
        if(defaultLocation.locationStrategy is DefaultLocationData){
            defaultLocation.texture = DefaultTextureHandler.getTexture(defaultLocation.getAreaSpecificTextureName())
        }
        Locations.add(defaultLocation)
    }

    init {
        AreaManager.addArea(this)
    }

    val showTitleEvent = object : Event {
        override fun execute() {
            showTitleText()
        }
    }

    fun addShowTitleEvent() {
        EventManager.eventManager.add(DelayEvent(showTitleEvent, 1))
    }

    fun showTitleText() {
        val areaChangeSignals =
            SignalManager.pastSignals.List.filter { it is AreaChangeSignal && it.areaIdentifier == this.identifier }

        if (areaChangeSignals.isEmpty()) {
            SignalManager.emitSignal(AreaChangeSignal(this.identifier))
            val text = getAreaTitleText(this.identifier)
            val color = Color(getAreaColor(this.identifier))
            AnimationManager.animationManager.add(FadingTextAnimation(text, color))
            onAreaChangedActions.remove(::addShowTitleEvent)
        }
    }

    fun changeMusic() {
        val music = getMusicBasedOnArea()
        DefaultMusicHandler.changeAndPlay(music)
    }

    private fun getMusicBasedOnArea(): Music {
        return when (this.identifier) {
            AreaIdentifier.ICELANDS, AreaIdentifier.ICELANDSDUNGEON -> MusicLoader.icelandsAreaMusic
            AreaIdentifier.FIRELANDS, AreaIdentifier.FIRELANDSDUNGEON -> MusicLoader.firelandsAreaMusic
            else -> MusicLoader.mainAreaMusic
        }
    }
}