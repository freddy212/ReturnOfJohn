package com.mygdx.game.GameObjects.MoveableEntities.Characters

import com.mygdx.game.DefaultTextureHandler
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.Sensors.TalkSensor
import com.mygdx.game.Interfaces.ModelInstanceHandler
import com.mygdx.game.SaveHandling.DefaultRemoveObjectSaveState
import com.mygdx.game.SaveState.SaveStateEntity
import com.mygdx.game.AbstractClasses.DefaultCharacter
import com.mygdx.game.HealthStrategy.EnemyHealthStrategy
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.UI.Dialogue.Conversations.engineerFirst

class NPC(Position: Vector2, size: Vector2 = Vector2(128f,128f), val location: DefaultLocation?,
          modelHandler: ModelInstanceHandler = DefaultModelInstanceHandler("ManBlender.g3db",Position,size))
    : DefaultCharacter(Position, size, location),
    SaveStateEntity by DefaultRemoveObjectSaveState(){
    override val texture = DefaultTextureHandler.getTexture("DefaultPerson.png")
    override val layer = Layer.ONGROUND
    override var currentSpeed = 2f
    override var direction = Direction.UP
    override var health = 10f
    override val maxHealth = 10f
    override val healthStrategy = EnemyHealthStrategy()

    override fun death() {

    }

    override val collition = IllegalMoveCollition
    val conversationsHandler = ConversationHandler(engineerFirst(this),"engineer")

    private val sensorUp = TalkSensor(this.topleft, Vector2(128f, 62f), this, Direction.UP)
    private val sensorLeft = TalkSensor(this.bottomleft - Vector2(128f, 0f), Vector2(128f, 62f), this, Direction.LEFT)
    private val sensorRight = TalkSensor(this.bottomright, Vector2(128f, 62f), this, Direction.RIGHT)
    private val sensorDown = TalkSensor(this.bottomleft - Vector2(0f, 62f), Vector2(128f, 62f), this, Direction.DOWN)
    private val sensors = listOf(sensorUp,sensorDown,sensorLeft,sensorRight)

    init {
        sensors.forEach{location!!.addGameObject(it)}
    }
    override fun move(directionUnitVector: Vector2): Boolean {
        /*val sucessfullMove = super.move(getDirectionUnitVector(this.direction))
        if(sucessfullMove){
            sensors.forEach{it.setPosition(Vector2(it.sprite.x,it.sprite.y) + getDirectionUnitVector(direction) * this.currentSpeed,it)}
        }
        return sucessfullMove*/
        return true
    }
    override fun addToLocation(location: DefaultLocation){
        sensors.forEach {location.addGameObject(it)}
        location.addGameObject(this)
    }
    override fun removeFromLocation(){
        sensors.forEach {it.removeFromLocation()}
        super.removeFromLocation()
    }

    override fun setPosition(nextPosition: Vector2) {
        val prevPos = Vector2(sprite.x,sprite.y)
        println(nextPosition)
        super.setPosition(nextPosition)
        sensors.forEach {it.setPosition( it.Position + nextPosition - prevPos) }
    }
}