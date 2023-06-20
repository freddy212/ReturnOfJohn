package com.mygdx.game.Animation

import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.Other.DefaultBreakableObject
import com.mygdx.game.ObjectProperties.BreakableEffect
import com.mygdx.game.ObjectProperties.ROJParticleObject

class BreakableObjectAnimation(breakableObject: DefaultBreakableObject): ParticleAnimation() {
    override val animationEffect = BreakableEffect(breakableObject)
    override val duration = 30
    override val animationAction = {}
    override var actionFrame = 30
    override val layer = breakableObject.layer
}