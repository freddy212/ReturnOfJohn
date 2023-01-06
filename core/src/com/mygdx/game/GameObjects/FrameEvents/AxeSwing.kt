package com.mygdx.game.GameObjects.FrameEvents

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.Other.Axe
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.player
import com.mygdx.game.rotate

class AxeSwing(Position: Vector2, size: Vector2): GameObject(Position, size) {
    override val texture = DefaultTextureHandler.getTexture("Axe.png")
    override val layer = Layer.FOREGROUND
    val framesToSwing = 30
    var counter = 0
    val currentLocation = LocationManager.newDefaultLocation
    val axe = Axe(Vector2(player.sprite.x , player.sprite.y + player.sprite.height / 2),Vector2(30f,100f),currentLocation)

    init {
        currentLocation.addGameObject(this)
        currentLocation.addGameObject(axe)
        player.freezeMoving()
        player.freezeChangingDirection()
        axe.rotate(player.sprite.rotation - 180f)
    }

    override fun frameTask() {
        if(counter <= framesToSwing){
            axe.rotate(4f)
        } else{
            player.enableMoving()
            player.enableChangingDirection()
            currentLocation.removeGameObject(axe)
            currentLocation.removeGameObject(this)
        }
        counter++
    }
}