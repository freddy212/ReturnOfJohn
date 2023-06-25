package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.graphics.Texture
import com.mygdx.game.GameObjects.MoveableEntities.IceClone
import com.mygdx.game.ItemAbilities.*
import com.mygdx.game.Interfaces.Timer
import com.mygdx.game.player

enum class AbilityId{AXE,ICECLONE,ICICLE,SHIELD,WATERBALL, FIREBALL, DASH, DASHUPGRADE, PROJECTILE, ICECLONEUPGRADE, SMALLBOULDER, SHIELDUPGRADE }
abstract class CharacterAbility(){
    abstract val abilityId: AbilityId
    abstract val triggerKey: Int
    abstract val texture: Texture
    abstract val cooldownTimer: Timer
    open val toolTipTexture: Texture
    get() = texture
    var showToolTip = true
    var active = false

    abstract fun activeAction()
    open fun inactiveAction(){
        active = false
    }

    open fun handleAbilityGained(){
        player.addAbility(this)
    }

    open fun tryUseAction(): Boolean {
        if(cooldownTimer.tryUseCooldown()){
            this.activeAction()
            active = true
            return true
        }
        return false
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
        AbilityId.DASH -> DashAbility()
        AbilityId.DASHUPGRADE -> DashAbilityUpgraded()
        AbilityId.PROJECTILE -> ProjectileAbilityToggle
        AbilityId.ICECLONEUPGRADE -> IceCloneAbilityUpgraded()
        AbilityId.SMALLBOULDER -> SmallBoulderAbility()
        AbilityId.SHIELDUPGRADE -> ShieldAbilityUpgraded()
    }
}

abstract class ToggleAbility(): CharacterAbility(){
    override fun handleAbilityGained() {
        ProjectileAbilityToggle.abilitiesToToggle.add(this)
        if(ProjectileAbilityToggle.abilitiesToToggle.List.size == 1){
            player.addAbility(ProjectileAbilityToggle)
        }
    }
}