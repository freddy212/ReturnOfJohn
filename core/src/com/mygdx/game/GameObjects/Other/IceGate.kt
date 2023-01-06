package com.mygdx.game.GameObjects.Other

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.Collition
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Saving.DefaultSaveStateHandler
import com.mygdx.game.Saving.SaveStateEntity

class IceGate(initPosition: Vector2, size: Vector2, defaultLocation: DefaultLocation?) :
    GameObject(initPosition, size, defaultLocation), SaveStateEntity by DefaultSaveStateHandler() {
    override val texture = DefaultTextureHandler.getTexture("IceGate.png")
    override val layer = Layer.ONGROUND
    override val collition = IllegalMoveCollition
    private var openedHalfWay = false

    // UGLY, BUT LAZY
    fun openHalfWay(){
        sprite.texture = DefaultTextureHandler.getTexture("IceGateHalf.png")
        openedHalfWay = true
    }

    fun reset(){
        if(!openedHalfWay){
            sprite.texture = DefaultTextureHandler.getTexture("IceGate.png")
        }
        openedHalfWay = false
    }
}