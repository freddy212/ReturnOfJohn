package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector3

class ROJInputAdapter(private val camera : OrthographicCamera, val player: Player) : InputAdapter(){

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            val vec3 = camera.unproject(Vector3(screenX.toFloat(),screenY.toFloat(),0f))
            println("x is :   ${vec3.x} y is : ${vec3.y}")
        }

        return super.touchDown(screenX, screenY, pointer, button)
    }
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