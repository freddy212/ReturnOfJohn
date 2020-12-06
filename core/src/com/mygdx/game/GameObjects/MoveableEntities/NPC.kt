package com.mygdx.game.GameObjects.MoveableEntities

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.DefaultMovement
import com.mygdx.game.AbstractClasses.MoveableObject
import com.mygdx.game.Collitions.BoulderPlayerCollition
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.EdgeOfLocationStrategies.NoAction
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Events.ConversationEvent
import com.mygdx.game.GameObjects.TalkSensor
import com.mygdx.game.Interfaces.Character
import com.mygdx.game.UI.Dialogue.Conversation
import com.mygdx.game.UI.Dialogue.DefaultCharacter

class NPC(Position: Vector2, size: Vector2 = Vector2(128f,128f), location: LocationImpl?) : MoveableObject(Position, size, location),Character by DefaultCharacter{
    override val texture = Texture("DefaultPerson.png")
    override val layer = Layer.PERSON
    override var speed = 2f
    override var direction = Direction.UP
    override val movementStrategy = DefaultMovement(NoAction())
    val conversationsHandler = ConversationHandler()
    private val sensorUp = TalkSensor(this.topleft,Vector2(128f,62f),this,Direction.UP)
    private val sensorLeft = TalkSensor(this.bottomleft - Vector2(128f,0f),Vector2(128f,62f),this, Direction.LEFT)
    private val sensorRight = TalkSensor(this.bottomright,Vector2(128f,62f),this, Direction.RIGHT)
    private val sensorDown = TalkSensor(this.bottomleft - Vector2(0f,62f),Vector2(128f,62f),this,Direction.DOWN)
    private val sensors = listOf(sensorUp,sensorDown,sensorLeft,sensorRight)
    override val collition = IllegalMoveCollition
    init {
        sensors.forEach{location!!.addGameObject(it)}
    }

    override fun frameTask() {
        super.frameTask()
        this.move(Direction.RIGHT)
    }
    override fun move(d: Direction): Boolean {
        val sucessfullMove = super.move(d)
        if(sucessfullMove){
            sensors.forEach{it.setPosition(Vector2(it.sprite.x,it.sprite.y) + GetNextStep(d,this.speed),it)}
        }
        return sucessfullMove
    }
}