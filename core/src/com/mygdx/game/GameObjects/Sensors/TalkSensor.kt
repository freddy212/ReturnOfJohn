package com.mygdx.game.GameObjects.Sensors

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.DefaultPositionChange
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.TalkCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Events.ConversationEvent
import com.mygdx.game.GameObjects.MoveableEntities.Characters.NPC
import com.mygdx.game.Interfaces.DynamicEntity
import com.mygdx.game.Interfaces.DirectionalObject

class TalkSensor(Position: Vector2, size: Vector2, val npc: NPC, override var direction: Direction) : GameObject(Position, size,npc.defaultLocation),DirectionalObject,
DynamicEntity by DefaultPositionChange{
    override val texture = DefaultTextureHandler.getTexture("sensor.png")
    override val layer = Layer.ONGROUND
    override var canChangeDirection = false
    override fun render(batch: PolygonSpriteBatch){
        sprite.draw(batch)
    }
    val conversationEvent =  ConversationEvent(npc.conversationsHandler.getActiveConversation(),npc)
    override val collition = TalkCollition(conversationEvent, this)
}