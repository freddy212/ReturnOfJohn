package com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.Hydra

import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.CharacterState
import com.mygdx.game.Interfaces.EnemyAction
import com.mygdx.game.rotate

class HydraEarthHeadAttack(val hydra: Hydra): EnemyAction() {

    private var counter = 0
    override var framesToBlock = 300
    val changeTime = framesToBlock / 2
    var hydraHead = HydraEarthHead(hydra.initPosition, hydra.defaultLocation, hydra, 0f)
    var returning = false
    override fun executeEnemyAction() {

        if(counter == 0) {
            hydraHead = HydraEarthHead(hydra.currentPosition(), hydra.defaultLocation, hydra, hydra.sprite.rotation)
            hydraHead.addToLocation(hydra.location!!)
            hydraHead.rotatingForward = true
            hydra.sprite.texture = DefaultTextureHandler.getTexture("HydraWithoutEarthHead.png")
        }

        if(counter <= changeTime && hydraHead.rotatingForward){
            hydraHead.rotateForward()
        }else{
            if(!returning){
                framesToBlock = counter * 2
                returning = true
            }
            hydraHead.rotateBack()
        }
        counter++

        if(hydra.characterState == CharacterState.STUNNED){
            framesToBlock = counter + 1
        }
    }

    override val probability: Double
        get() = if(active) 1.0 else 0.01
    override val shouldBlock = true
    override fun cleanUp() {
        hydraHead.removeFromLocation()
        counter = 0
        hydraHead.rotatingForward = false
        returning = false
        hydra.sprite.texture = DefaultTextureHandler.getTexture("hydra.png")
        framesToBlock = 300
    }
}