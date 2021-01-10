package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Events.DrawSentenceEvent
import com.mygdx.game.GameObjects.ItemAbilities.Shield
import com.mygdx.game.GameObjects.MoveableEntities.Player
import com.mygdx.game.GameObjects.MoveableEntities.WaterGunSpray
import com.mygdx.game.Interfaces.KeyPressedCollition
import com.mygdx.game.Managers.EventManager
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.UI.Dialogue.OptionSentence

class ROJInputAdapter(private val camera : OrthographicCamera, val player: Player) : InputAdapter(){
    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            val vec3 = camera.unproject(Vector3(screenX.toFloat(),screenY.toFloat(),0f))
            val transformedVertices = player.polygon.transformedVertices
            println("x is :   ${vec3.x} y is : ${vec3.y}")
            println("player polygonPosition is : " + transformedVertices[0] + " " + transformedVertices[1])
        }
        return super.touchDown(screenX, screenY, pointer, button)
    }

    override fun keyDown(keycode: Int): Boolean {
        if (keycode ==Input.Keys.SPACE) {
            handleCollitions(player, player.polygon, LocationManager.ButtonCollitionGameObjects.filter {(it.collition as KeyPressedCollition).specificButton == Input.Keys.SPACE })
        }


        for (itemAbility in player.itemAbilities.List){
            if(keycode == itemAbility.triggerKey){
                itemAbility.activeAction()
            }
        }
        if(keycode == Input.Keys.LEFT || keycode == Input.Keys.RIGHT || keycode == Input.Keys.A || keycode == Input.Keys.D){
            val readSentenceEvent = EventManager.eventManager.List.find {it is DrawSentenceEvent} as DrawSentenceEvent?
            if(readSentenceEvent != null){
                if(readSentenceEvent.conversationHandler.GetSentence() is OptionSentence){
                    (readSentenceEvent.conversationHandler.GetSentence() as OptionSentence).textChoice.changeActive()
                }
            }
        }
        return super.keyDown(keycode)
    }

    override fun keyUp(keycode: Int): Boolean {
        for (itemAbility in player.itemAbilities.List){
            if(keycode == itemAbility.triggerKey && itemAbility in crossLocationGameObjects.List){
                itemAbility.InactiveAction()
            }
        }
        return super.keyUp(keycode)
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