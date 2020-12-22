package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.mygdx.game.Enums.Direction
import com.mygdx.game.GameObjects.ItemAbilities.Shield
import com.mygdx.game.GameObjects.MoveableEntities.Player
import com.mygdx.game.GameObjects.MoveableEntities.WaterGunSpray
import com.mygdx.game.Managers.LocationManager

class ROJInputAdapter(private val camera : OrthographicCamera, val player: Player) : InputAdapter(){
    val waterSpray = WaterGunSpray(Vector2(0f,0f), Vector2(20f,200f),null)
    val shield = Shield(Vector2(0f,0f), Vector2(40f,40f),null)
    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            val vec3 = camera.unproject(Vector3(screenX.toFloat(),screenY.toFloat(),0f))
            val transformedVertices = player.polygon.transformedVertices
            println("x is :   ${vec3.x} y is : ${vec3.y}")
            println("player polygonPosition is : " + transformedVertices[0] + " " + transformedVertices[1])
            if(shield !in crossLocationGameObjects.List && player.canMove()){
                player.freezeMoving()
                crossLocationGameObjects.add(shield)
            }
        }
        if(button == Input.Buttons.RIGHT){
            if(waterSpray !in crossLocationGameObjects.List && player.canMove()){
                crossLocationGameObjects.add(waterSpray)
            }
        }
        return super.touchDown(screenX, screenY, pointer, button)
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        if(button == Input.Buttons.RIGHT){
            crossLocationGameObjects.remove(waterSpray)
        }
        if(button == Input.Buttons.LEFT && shield in crossLocationGameObjects.List){
            player.enableMoving()
            crossLocationGameObjects.remove(shield)
        }
        return super.touchUp(screenX, screenY, pointer, button)
    }

    override fun keyDown(keycode: Int): Boolean {
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            handleCollitions(player,player.polygon,LocationManager.ButtonCollitionGameObjects)
        }
        return super.keyDown(keycode)
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