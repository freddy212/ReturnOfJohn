package com.mygdx.game.GameObjects.Gates

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.ButtonGate
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Saving.DefaultSaveStateHandler
import com.mygdx.game.Saving.SaveStateEntity

class IceGate(initPosition: Vector2, size: Vector2, defaultLocation: DefaultLocation?) :
    GameObject(initPosition, size, defaultLocation), SaveStateEntity by DefaultSaveStateHandler(), ButtonGate {
    override val texture = DefaultTextureHandler.getTexture("IceGate.png")
    override val layer = Layer.ONGROUND
    override val collition = IllegalMoveCollition

    // UGLY, BUT LAZY
    override fun buttonPressed(){
        sprite.texture = DefaultTextureHandler.getTexture("IceGateHalf.png")
    }

    override fun buttonReleased(){
        sprite.texture = DefaultTextureHandler.getTexture("IceGate.png")
    }
}