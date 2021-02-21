package com.mygdx.game.GameObjects.ItemAbilities

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.CharacterAbility
import com.mygdx.game.AbstractClasses.DefaultPositionChange
import com.mygdx.game.Collitions.WaterGunCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.WaterBall
import com.mygdx.game.Interfaces.DynamicEntity
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Timer.DefaultTimer
import com.mygdx.game.player

class WaterBallAbility(Position: Vector2, size: Vector2): CharacterAbility(Position, size), DynamicEntity by DefaultPositionChange  {
    override val triggerKey = com.badlogic.gdx.Input.Keys.NUM_1
    override val texture = DefaultTextureHandler.getTexture("WaterBall.png")
    override val layer = Layer.AIR

    val cooldownTimer = DefaultTimer(1f)

    override fun activeAction(){
        if(cooldownTimer.tryUseCooldown()) {
                val waterBall = WaterBall(player.currentMiddle,Vector2(96f,120f),LocationManager.currentLocation, player.unitVectorDirection)
                waterBall.location!!.addGameObject(waterBall)
        }
    }
}