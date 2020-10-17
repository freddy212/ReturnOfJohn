package com.mygdx.game.Collitions

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Enums.Direction
import com.mygdx.game.GameObjects.Door
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Interfaces.Collition
import com.mygdx.game.Managers.AreaManager
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.GameObjects.MoveableEntities.Player
import com.mygdx.game.Interfaces.MoveableEntity

class DoorCollition(val areaId: AreaIdentifier, val connection: DoorConnection,
                    val triggerDirection: Direction): Collition{

    override var canMoveAfterCollition = true
    override fun collitionHappened(entity: MoveableEntity, collitionPosition: Vector2, collidedObject: GameObject) {
        println("triggered!")
        canMoveAfterCollition = true
        if(entity is Player && collidedObject is Door)
        {
            if(entity.direction == triggerDirection){
                val playerPosAfter = getPlayerPos(connection,triggerDirection)
                if(areaId != AreaIdentifier.NOTIMPLEMENTED){
                    val playerPosMiddle = Vector2(playerPosAfter.x - playerSize.x / 2, playerPosAfter.y)
                    entity.setPosition(playerPosMiddle)
                    LocationManager.activeArea = AreaManager.getArea(areaId)
                    canMoveAfterCollition = false
                }
            }
        }
    }
    fun getPlayerPos(connection: DoorConnection,triggerDirection: Direction): Vector2{
        return when(triggerDirection){
            Direction.UP -> connection.secondEntrance
            Direction.DOWN-> Vector2(connection.firstEntrance.x,connection.firstEntrance.y - playerSize.y)
            else -> Vector2(0f,0f)
        }
    }
}