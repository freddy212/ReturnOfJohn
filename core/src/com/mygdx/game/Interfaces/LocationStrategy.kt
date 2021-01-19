package com.mygdx.game.Interfaces

import com.badlogic.gdx.graphics.Texture
import com.mygdx.game.LocationImpl

interface LocationStrategy {
    val texture: Texture
    val initialization: (LocationImpl) -> Unit
    val collition: MoveCollition
}