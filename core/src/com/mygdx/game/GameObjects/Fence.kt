package com.mygdx.game.GameObjects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.NoCharacterCanPassCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.SaveHandling.DefaultRemoveObjectSaveState
import com.mygdx.game.SaveHandling.SaveableObject
import com.mygdx.game.SaveState.SaveStateEntity
import com.mygdx.game.renderRepeatedTexture

class Fence(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation, override val texture: Texture = DefaultTextureHandler.getTexture("Fence-Start.png"), val repeat: Boolean = true)
    : GameObject(Position,size,defaultLocation), SaveStateEntity by DefaultRemoveObjectSaveState() {
    override val collition = NoCharacterCanPassCollition()
    override val layer = Layer.ONGROUND
    override fun render(batch: PolygonSpriteBatch){
        if(repeat){
            renderRepeatedTexture(batch,texture,initPosition,size)
        }
        else {
            super.render(batch)
        }
    }
}