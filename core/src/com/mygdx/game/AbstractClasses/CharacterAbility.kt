package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.graphics.Texture
import com.mygdx.game.GameObjects.ItemAbilities.*
import com.mygdx.game.Interfaces.Timer

enum class AbilityId{AXE,ICECLONE,ICICLE,SHIELD,WATERBALL, FIREBALL}
abstract class CharacterAbility(){
    abstract val abilityId: AbilityId
    abstract val triggerKey: Int
    abstract val texture: Texture
    abstract val cooldownTimer: Timer
    open val toolTipTexture: Texture
    get() = texture

    abstract fun activeAction()
    open fun InactiveAction(){

    }

    fun tryUseAction() {
        if(cooldownTimer.tryUseCooldown()){
            this.activeAction()
        }
    }
}

fun getAbility(abilityId: AbilityId): CharacterAbility{
    return when(abilityId){
        AbilityId.AXE -> AxeAbility()
        AbilityId.ICECLONE-> IceCloneAbility()
        AbilityId.SHIELD -> ShieldAbility()
        AbilityId.WATERBALL -> WaterBallAbility()
        AbilityId.ICICLE -> IcicleAbility()
        AbilityId.FIREBALL -> FireballAbility()
    }
}