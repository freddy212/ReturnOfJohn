package com.mygdx.game.GameObjects.Terrain

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.AreaEntranceCollition
import com.mygdx.game.Interfaces.Collition
import com.mygdx.game.Interfaces.DefaultAreaEntranceCollition
import com.mygdx.game.Locations.DefaultLocation

class TeleportPad(initPosition: Vector2, size: Vector2, defaultLocation: DefaultLocation?) :
    GameObject(initPosition, size, defaultLocation) {
    override val texture = DefaultTextureHandler.getTexture("TeleportPad.png")
    override val layer = Layer.ONGROUND
    val connectedTeleportPads: MutableList<TeleportPad> = mutableListOf()
    override val collition = TeleportPadCollition(connectedTeleportPads)
}

class TeleportPadCollition(val otherTeleportPads: MutableList<TeleportPad>): DefaultAreaEntranceCollition() {

    override val canMoveAfterCollition = true
    override fun movedInsideAction(objectEntered: GameObject) {
        val randomPad = getRandomTeleportPad()
        objectEntered.setPosition(randomPad.currentPosition())
        randomPad.collition.insideCollition[objectEntered] = true
    }

    override fun movedOutsideAction(objectLeaved: GameObject) {

    }

    fun getRandomTeleportPad(): TeleportPad{
        val randomList = otherTeleportPads.toMutableList()
        randomList.shuffle()
        return randomList[0]
    }
}