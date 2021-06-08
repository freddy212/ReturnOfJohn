package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Enums.CharacterState
import com.mygdx.game.SaveState.DefaultSaveStateHandler
import com.mygdx.game.SaveState.SaveStateEntity
import com.mygdx.game.crossLocationGameObjects
import com.mygdx.game.player

abstract class CharacterAbility(Position: Vector2, size: Vector2) : GameObject(Position, size){
    abstract val triggerKey: Int
    open val toolTipTexture: Texture
    get() = texture
    open fun activeAction(){
        if(player.characterState == CharacterState.FREE && player.canMove()) {
            crossLocationGameObjects.add(this)
        }
    }
    open fun InactiveAction(){
            crossLocationGameObjects.remove(this)
    }
}