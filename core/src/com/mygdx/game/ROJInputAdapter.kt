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
import com.mygdx.game.InputActions.ChangeDialogueOption
import com.mygdx.game.InputActions.RenderInventoryAction
import com.mygdx.game.Interfaces.KeyPressedCollition
import com.mygdx.game.Managers.EventManager
import com.mygdx.game.Managers.InputActionManager
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.UI.Dialogue.OptionSentence

class ROJInputAdapter(private val camera : OrthographicCamera, val player: Player) : InputAdapter(){
    var clickPosition = Vector3(0f,0f,0f)

    init {
        InputActionManager.InputActionManager.addAll(listOf(ChangeDialogueOption(), RenderInventoryAction()))
    }

    override fun keyDown(keycode: Int): Boolean {
        val keyCollitions = LocationManager.ButtonCollitionGameObjects.filter {(it.collition as KeyPressedCollition).specificButton == keycode}
        handleKeyCollitions(keyCollitions)

        if(player.characterState == CharacterState.FREE && player.canMove()) {
            for (itemAbility in player.itemAbilities.List) {
                if (keycode == itemAbility.triggerKey) {
                    itemAbility.tryUseAction()
                }
            }
        }

        InputActionManager.InputActionManager.List.forEach {
            if(it.keycodes.contains(keycode)){
                it.action()
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
        InputActionManager.InputActionManager.List.forEach {
            if(it.keycodes.contains(keycode)){
                it.inactiveAction()
            }
        }
        return super.keyUp(keycode)
    }
    fun handleInput(player: Player) {
        handleKeyPressable(LocationManager.ButtonCollitionGameObjects)

        if(player.characterState == CharacterState.FREE) {
            clickPosition = camera.unproject(Vector3(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f))
            val unitVectorTowardsPoint = getUnitVectorTowardsPoint(
                Vector2(player.sprite.x, player.sprite.y),
                Vector2(clickPosition.x, clickPosition.y)
            )
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                player.move((unitVectorTowardsPoint))
            }
            if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
                player.setCharacterRotation(unitVectorTowardsPoint)
            }
        }
    }

}