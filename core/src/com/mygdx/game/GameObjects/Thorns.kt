package com.mygdx.game.GameObjects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.Collitions.ThornsCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.Collition
import com.mygdx.game.LocationImpl
import com.mygdx.game.SaveHandling.DefaultRemoveObjectSaveState
import com.mygdx.game.SaveHandling.SaveCounterHandler
import com.mygdx.game.SaveState.SaveStateEntity

class Thorns(Position: Vector2, size: Vector2, location: LocationImpl?) : GameObject(Position, size, location),
                                                                          SaveStateEntity by DefaultRemoveObjectSaveState(){
    override val texture = DefaultTextureHandler.getTexture("Thorns.png")
    override val layer = Layer.ONGROUND
    override val collition = ThornsCollition()
}