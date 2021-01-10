package com.mygdx.game.GameObjects.Sensors

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.DefaultPositionChange
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.CheckKeyCollition
import com.mygdx.game.Collitions.TalkCollition
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Events.ConversationEvent
import com.mygdx.game.GameObjects.LockedDoor
import com.mygdx.game.GameObjects.MoveableEntities.NPC
import com.mygdx.game.Interfaces.DirectionalObject
import com.mygdx.game.Interfaces.DynamicEntity
import com.mygdx.game.LocationImpl

class KeySensor(Position: Vector2, size: Vector2, location: LocationImpl,val lockedDoor: LockedDoor) : GameObject(Position, size,location){
    override val texture = Texture("sensor.png")
    override val layer = Layer.ONGROUND
    override fun render(batch: PolygonSpriteBatch){
        sprite.draw(batch)
    }
    init {
        polygon.setPosition(location.middle.x - polygon.vertices[0] - sprite.width / 2,sprite.y - polygon.vertices[1] - sprite.height)
    }
    override val collition = CheckKeyCollition(lockedDoor)
}