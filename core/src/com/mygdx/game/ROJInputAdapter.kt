package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.mygdx.game.Enums.CharacterState
import com.mygdx.game.Events.DrawSentenceEvent
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.Interfaces.KeyPressedCollition
import com.mygdx.game.Managers.EventManager
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.UI.Dialogue.OptionSentence

class ROJInputAdapter(private val camera : OrthographicCamera, val player: Player) : InputAdapter(){
    var clickPosition = Vector3(0f,0f,0f)

    override fun keyDown(keycode: Int): Boolean {
        val keyCollitions = LocationManager.ButtonCollitionGameObjects.filter {(it.collition as KeyPressedCollition).specificButton == keycode}
       /* if (keycode ==Input.Keys.SPACE) {
            handleCollitions(player, player.polygon, LocationManager.ButtonCollitionGameObjects.filter {(it.collition as KeyPressedCollition).specificButton == Input.Keys.SPACE })
        }*/
        handleCollitions(player, player.polygon,keyCollitions)

        if(player.characterState == CharacterState.FREE && player.canMove()) {
            for (itemAbility in player.itemAbilities.List) {
                if (keycode == itemAbility.triggerKey) {
                    itemAbility.tryUseAction()
                }
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
            if(keycode == itemAbility.triggerKey){
                itemAbility.InactiveAction()
            }
        }
        return super.keyUp(keycode)
    }
    fun handleInput(player: Player) {
        clickPosition = camera.unproject(Vector3(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f))
        val unitVectorTowardsPoint = getUnitVectorTowardsPoint(Vector2(player.sprite.x,player.sprite.y),Vector2(clickPosition.x,clickPosition.y))
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            player.move((unitVectorTowardsPoint))
        }
        if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
            player.setCharacterRotation(unitVectorTowardsPoint)
        }

    }

}