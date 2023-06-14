package com.mygdx.game.ItemAbilities

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.AbilityId
import com.mygdx.game.AbstractClasses.CharacterAbility
import com.mygdx.game.AbstractClasses.ToggleAbility
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Icicle
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.SmallBoulder
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Timer.DefaultTimer

class SmallBoulderAbility(): ToggleAbility() {
    override val abilityId = AbilityId.SMALLBOULDER
    override val triggerKey = com.badlogic.gdx.Input.Keys.NUM_1
    override val texture = DefaultTextureHandler.getTexture("Boulder.png")
    override val cooldownTimer = DefaultTimer(1f)

    override fun activeAction(){
        val smallBoulder = SmallBoulder(player.currentMiddle + (player.unitVectorDirection * 50f),Vector2(60f,60f),LocationManager.newDefaultLocation, player.unitVectorDirection, player)
        smallBoulder.defaultLocation!!.addGameObject(smallBoulder)
    }
}