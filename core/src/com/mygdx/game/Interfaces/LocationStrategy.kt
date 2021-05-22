package com.mygdx.game.Interfaces

import com.badlogic.gdx.graphics.Texture
import com.mygdx.game.Locations.DefaultLocation

interface LocationDataStrategy {
    val texture: Texture
    val initialization: (DefaultLocation) -> Unit
    val collition: MoveCollition
}