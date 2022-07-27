package com.mygdx.game.ObjectProperties

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Enums.Layer
import com.mygdx.game.player

class FireDashEffect: ROJParticleObject(ParticleEffect(), player, Vector2(player.sprite.width/2, player.sprite.height/2)) {
    override val layer = Layer.FOREGROUND

    init {
        particleEffect.load(Gdx.files.internal("ParticleEmitters/Dash.p"), Gdx.files.internal(""))
    }
}