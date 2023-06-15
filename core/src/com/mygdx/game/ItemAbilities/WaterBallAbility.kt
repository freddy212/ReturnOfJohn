package com.mygdx.game.ItemAbilities

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.AbilityId
import com.mygdx.game.AbstractClasses.CharacterAbility
import com.mygdx.game.AbstractClasses.ToggleAbility
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.WaterBall
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Timer.DefaultTimer
import com.mygdx.game.player

class WaterBallAbility: ToggleAbility(){
    override val abilityId = AbilityId.WATERBALL
    override val triggerKey = com.badlogic.gdx.Input.Keys.NUM_1
    override val texture = DefaultTextureHandler.getTexture("WaterBall.png")

    override val cooldownTimer = DefaultTimer(1f)

    override fun activeAction(){
        val waterBall = WaterBall(player.currentMiddle,Vector2(96 / 1.5f,120f / 1.5f),LocationManager.newDefaultLocation, player.unitVectorDirection, player)
        waterBall.defaultLocation!!.addGameObject(waterBall)
    }
}