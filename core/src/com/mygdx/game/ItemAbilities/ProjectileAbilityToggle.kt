package com.mygdx.game.ItemAbilities

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.AbilityId
import com.mygdx.game.AbstractClasses.CharacterAbility
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Interfaces.Event
import com.mygdx.game.Interfaces.Timer
import com.mygdx.game.Managers.EventManager
import com.mygdx.game.Managers.UIRendererManager
import com.mygdx.game.Timer.DefaultTimer
import com.mygdx.game.UI.Actions.RenderProjectileChoice
import com.mygdx.game.Utils.ResourceList

object ProjectileAbilityToggle: CharacterAbility() {

    val abilitiesToToggle = ResourceList<CharacterAbility>()
    val sound = Gdx.audio.newSound(Gdx.files.local("Sound/SoundEffect/clicksound.mp3"));
    init {
        abilitiesToToggle.add(WaterBallAbility())
        abilitiesToToggle.add(SmallBoulderAbility())
    }
    var activeIndex = 0
    set(newValue) {
        if(field != newValue){
            sound.play()
        }
        field = newValue
    }
    private val activeAbility get() = abilitiesToToggle.List[activeIndex]
    override val abilityId = AbilityId.PROJECTILE
    override val triggerKey = Input.Keys.NUM_1
    override val texture = activeAbility.texture
    override val toolTipTexture
        get() = activeAbility.toolTipTexture
    override val cooldownTimer = DefaultTimer(1f)
    val timeSincePressedTimer = DefaultTimer(0.25f)
    var isToggle = false

    val toggleEvent = object: Event{
        override fun execute() {
            timeSincePressedTimer.UpdateTimer()
            isToggle = timeSincePressedTimer.coolDownAvailable && active
            showToolTip = !isToggle

            if(isToggle){
                UIRendererManager.addToUIGraph(RenderProjectileChoice(abilitiesToToggle.List, Vector2(0f + 50f, Gdx.graphics.height - 100f), activeIndex))
            }
        }

    }
    override fun activeAction() {
        timeSincePressedTimer.tryUseCooldown()
        EventManager.eventManager.add(toggleEvent)
        active = true
    }

    override fun inactiveAction() {
        if(!isToggle && cooldownTimer.coolDownAvailable){
            cooldownTimer.tryUseCooldown()
            abilityAction()
        }
        timeSincePressedTimer.reset()
        EventManager.eventManager.remove(toggleEvent)
        showToolTip = true
        active = false
        isToggle = false
    }

    fun abilityAction() {
        activeAbility.activeAction()
    }

    override fun tryUseAction() {
        this.activeAction()
    }
}