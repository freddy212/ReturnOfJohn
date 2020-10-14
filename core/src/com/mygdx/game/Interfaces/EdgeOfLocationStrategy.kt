package com.mygdx.game.Interfaces

import com.mygdx.game.AbstractClasses.GameObject

interface EdgeOfLocationStrategy {
    fun handleEdgeOfLocation(gameObject: GameObject)
}