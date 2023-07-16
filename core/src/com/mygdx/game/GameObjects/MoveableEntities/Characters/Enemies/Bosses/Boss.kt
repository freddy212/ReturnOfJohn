package com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses

import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.Enemy
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Animation.FadingTextAnimation
import com.mygdx.game.DefaultMusicHandler
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.Hydra.Hydra
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.IceQueen.IceQueen
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.SandGhost.SandGhost
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.SandGhost.Sartan
import com.mygdx.game.HealthStrategy.BossHealthStrategy
import com.mygdx.game.Interfaces.CollitionMask
import com.mygdx.game.Interfaces.HealthStrategy
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.AnimationManager
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Managers.SignalManager
import com.mygdx.game.Saving.DefaultSaveStateHandler
import com.mygdx.game.Saving.SaveStateEntity
import com.mygdx.game.Signal.Signal
import com.mygdx.game.Signal.Signals.RemoveObjectSignal

open class BossCollitionMask(boss: Boss) : CollitionMask {
    override val canCollideWith = { _: GameObject -> boss.isAggroed() }

}

abstract class Boss(
    Position: Vector2,
    size: Vector2,
    location: DefaultLocation?,
    val onDeathSignal: Signal? = null
) : Enemy(Position, size, location, 300f), SaveStateEntity by DefaultSaveStateHandler() {

    val adjacentLocations = location?.adjacentDefaultLocations!!
    override val healthStrategy: HealthStrategy by lazy { BossHealthStrategy(this) }
    override val collitionMask by lazy { BossCollitionMask(this) }
    var attachedMusic: Music? = null
    var previousMusic: Music? = null
    var isRolling = false

    init {
        onLocationEnterActions.add(::resetArea)
    }

    override fun setAggroed() {
        if (attachedMusic != null) {
            previousMusic = DefaultMusicHandler.currentlyTrack
            DefaultMusicHandler.changeAndPlay(attachedMusic!!)
        }
        defaultLocation!!.adjacentDefaultLocations.forEach { it.removeAdjacentLocation(defaultLocation!!) }
        LocationManager.changeLocation()
        val animationText = getTextBasedOnBoss()
        val animationColor = Color(getColorBasedOnBoss())
        AnimationManager.animationManager.add(FadingTextAnimation(animationText, animationColor))
        super.setAggroed()
    }

    open fun resetArea() {
        if (!LocationManager.activeDefaultLocations.containsAll(adjacentLocations)) {
            adjacentLocations.forEach { it.addAdjacentLocation(defaultLocation!!) }
            LocationManager.changeLocation()
        }

        if (previousMusic != null){
            DefaultMusicHandler.changeAndPlay(previousMusic!!)
        }
    }

    override fun death() {
        enemyStrategy.actionList.forEach { it.cleanUp() }
        resetArea()
        SignalManager.emitSignal(RemoveObjectSignal(entityId))
        if (onDeathSignal != null) {
            SignalManager.emitSignal(onDeathSignal)
        }
    }

    fun getColorBasedOnBoss(): Color {
        return when (this) {
            is Hydra -> Color.PURPLE
            is IceQueen -> Color.TEAL
            is Sartan -> Color.RED
            is SandGhost -> Color.BROWN
            else -> Color.WHITE
        }
    }

    fun getTextBasedOnBoss(): String {
        return when (this) {
            is Hydra -> "Elemental God"
            is IceQueen -> "Ice Queen"
            is Sartan -> "Sartan"
            is SandGhost -> "Sand Ghost"
            else -> "Rock Monster"
        }
    }
}