package com.mygdx.game.GameObjects.Other

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.AxeSwingCollision
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.handleCollitions
import com.mygdx.game.rotate

class Axe(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation): GameObject(Position,size,defaultLocation) {
        override val texture = DefaultTextureHandler.getTexture("Axe.png")
        override val layer = Layer.ONGROUND
        override val collition = AxeSwingCollision(this)
        init {
            sprite.color = Color.YELLOW
            sprite.setOrigin(this.size.x,0f)
            polygon.setScale(1f,0.5f)
            polygon.translate(0f,polygon.y + size.y)
            //polygon.setPosition(polygon.x,polygon.y + size.y / 2)
            polygon.setOrigin(sprite.x + this.size.x, sprite.y - size.y)

            this.rotate(-60f)
        }

    override fun frameTask() {
        super.frameTask()
        handleCollitions(this, this.polygon, LocationManager.ActiveGameObjects)
        }
    }