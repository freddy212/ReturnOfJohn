package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector3

class ROJInputAdapter(private val camera : OrthographicCamera) : InputAdapter(){

     override fun keyDown(keycode: Int): Boolean {
         handleInput(keycode)
         return false
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            val vec3 = camera.unproject(Vector3(screenX.toFloat(),screenY.toFloat(),0f))
            println("x is :   ${vec3.x} y is : ${vec3.y}")
        }

        if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
            println("right mouse click!")
            TerrainManager.addTerrain(Polygon(entrancePoly.vertices))
            gateOpened = true
        }

        return super.touchDown(screenX, screenY, pointer, button)
    }

    private fun handleInput(keycode: Int){
       /* if(keycode == Input.Keys.W){
            player.move(Direction.UP)
        }else if(keycode == Input.Keys.A){
            player.move(Direction.LEFT)
        }else if(keycode == Input.Keys.D){
            player.move(Direction.RIGHT)
        }else if (keycode == Input.Keys.S){
            player.move(Direction.DOWN)
        }*/
    }

}