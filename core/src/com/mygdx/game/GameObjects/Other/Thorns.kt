package com.mygdx.game.GameObjects.Other

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.ThornsCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Saving.DefaultSaveStateHandler
import com.mygdx.game.Saving.SaveStateEntity

class Thorns(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation?) : GameObject(Position, size, defaultLocation),
                                                                          SaveStateEntity by DefaultSaveStateHandler() {
    override val texture = DefaultTextureHandler.getTexture("Thorns.png")
    override val layer = Layer.ONGROUND
    override val collition = ThornsCollition(this)
}