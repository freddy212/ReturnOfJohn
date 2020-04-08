package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.math.Polygon

class InputHandler {
    companion object {
        fun handleInput(player: Player) {
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                player.move(Direction.UP)
            } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                player.move(Direction.LEFT)
            } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                player.move(Direction.RIGHT)
            } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                player.move(Direction.DOWN)
            }
        }
    }
}