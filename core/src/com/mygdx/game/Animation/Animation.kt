package com.mygdx.game.Animation

import com.mygdx.game.Interfaces.Renderable

interface Animation: Renderable {
   val duration: Int
   val animationAction : () -> Unit
   var currentFrame: Int
   var actionFrame: Int
   fun reset()
}