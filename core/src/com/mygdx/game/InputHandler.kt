package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3

class InputHandler {
    companion object {
        val ClickPosVector = Vector3(0f,0f,0f)
        fun handleInput(player: Player) {
            when {
                Gdx.input.isKeyPressed(Input.Keys.W) -> {
                    player.move(Direction.UP)
                }
                Gdx.input.isKeyPressed(Input.Keys.A) -> {
                    player.move(Direction.LEFT)
                }
                Gdx.input.isKeyPressed(Input.Keys.D) -> {
                    player.move(Direction.RIGHT)
                }
                Gdx.input.isKeyPressed(Input.Keys.S) -> {
                    player.move(Direction.DOWN)
                }
            }
        }
    }
}