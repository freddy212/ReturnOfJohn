package com.mygdx.game.ItemAbilities

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.AbilityId
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Animation.ParticleAnimation
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.MoveableEntities.IceClone
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Icicle
import com.mygdx.game.Managers.AnimationManager
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.ObjectProperties.ROJParticleObject
import com.mygdx.game.player
import com.mygdx.game.plus

class IceCloneAbilityUpgraded: IceCloneAbility() {

    override val abilityId = AbilityId.ICECLONEUPGRADE
    val sound = Gdx.audio.newSound(Gdx.files.internal("Sound/SoundEffect/IceExplotion.mp3"))
    override fun activeAction() {
        if(iceClone in LocationManager.ActiveGameObjects){
            val icicle = Icicle(iceClone.currentPosition() + Vector2(-50f, 25f), Vector2(100f,34f),iceClone.defaultLocation!!,
                Vector2(-0.5f,0.5f), iceClone
            )
            val icicle2 = Icicle(iceClone.currentPosition() + Vector2(0f,25f), Vector2(100f,34f),iceClone.defaultLocation!!,Vector2(0.5f,0.5f), iceClone)
            icicle.addToLocation(iceClone.defaultLocation!!)
            icicle2.addToLocation(iceClone.defaultLocation!!)
            iceClone.removeFromLocation()
            AnimationManager.animationManager.add(IceCloneExplotionAnimation(iceClone))
            sound.play(0.25f)
        }else{
            super.activeAction()
        }
    }
    override fun handleAbilityGained() {
        val iceClone = player.itemAbilities.List.firstOrNull { it is IceCloneAbility }
        player.removeAbility(iceClone)
        super.handleAbilityGained()
    }
}

class IceCloneExplotionAnimation(iceClone: IceClone) : ParticleAnimation() {
    override val duration = 60
    override val animationAction = {}
    override var actionFrame = 60
    override val animationEffect = IceCloneExplotionEffect(iceClone)
    override val layer = Layer.PERSON
}

class IceCloneExplotionEffect(objectAttached: GameObject,  posModifier: Vector2 = Vector2(objectAttached.sprite.width/2, objectAttached.sprite.height/2)): ROJParticleObject(
    ParticleEffect(), objectAttached, posModifier) {
    override val layer = Layer.PERSON
    init {
        particleEffect.load(Gdx.files.internal("ParticleEmitters/IceCloneEffect.p"), Gdx.files.internal(""))
    }
}
