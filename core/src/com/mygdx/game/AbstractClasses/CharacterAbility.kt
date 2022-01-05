package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Enums.CharacterState
import com.mygdx.game.GameObjects.ItemAbilities.*
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Icicle
import com.mygdx.game.SaveState.DefaultSaveStateHandler
import com.mygdx.game.SaveState.SaveStateEntity
import com.mygdx.game.crossLocationGameObjects
import com.mygdx.game.player

enum class AbilityId{AXE,ICECLONE,ICICLE,SHIELD,WATERBALL}
abstract class CharacterAbility(){
    abstract val abilityId: AbilityId
    abstract val triggerKey: Int
    abstract val texture: Texture
    open val toolTipTexture: Texture
    get() = texture

    abstract fun activeAction()
    open fun InactiveAction(){

    }
}

fun getAbility(abilityId: AbilityId): CharacterAbility{
    return when(abilityId){
        AbilityId.AXE -> AxeAbility()
        AbilityId.ICECLONE-> IceCloneAbility()
        AbilityId.SHIELD -> ShieldAbility()
        AbilityId.WATERBALL -> WaterBallAbility()
        AbilityId.ICICLE -> IcicleAbility()
    }
}