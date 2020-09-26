package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Collitions.AbyssCollition
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.Collitions.NoCollition

val Center = Vector2(Gdx.graphics.width.toFloat() / 2, Gdx.graphics.height.toFloat() / 2)

val abyssCollition = AbyssCollition()
val illegalMoveCollition = IllegalMoveCollition()
val noCollition = NoCollition()