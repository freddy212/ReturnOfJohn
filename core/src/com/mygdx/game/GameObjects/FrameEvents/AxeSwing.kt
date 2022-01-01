package com.mygdx.game.GameObjects.FrameEvents

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.Axe
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.player
import com.mygdx.game.rotate

class AxeSwing(Position: Vector2, size: Vector2): GameObject(Position, size) {
    override val texture = DefaultTextureHandler.getTexture("Axe.png")
    override val layer = Layer.FOREGROUND
    val framesToSwing = 45
    var counter = 0
    val currentLocation = LocationManager.newDefaultLocation
    val axe = Axe(Vector2(player.sprite.x , player.sprite.y + player.sprite.height / 2),Vector2(20f,50f),currentLocation)

    init {
        currentLocation.addGameObject(this)
        currentLocation.addGameObject(axe)
        player.freezeMoving()
        axe.rotate(player.sprite.rotation - 180f)
    }

    override fun frameTask() {
        if(counter <= framesToSwing){
            axe.rotate(2.5f)
        } else{
            player.enableMoving()
            currentLocation.removeGameObject(axe)
            currentLocation.removeGameObject(this)
        }
        counter++
    }
}