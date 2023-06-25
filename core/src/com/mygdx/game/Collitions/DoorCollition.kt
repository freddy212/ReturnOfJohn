package com.mygdx.game.Collitions

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Enums.Direction
import com.mygdx.game.GameObjects.Other.Door
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Managers.AreaManager
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player

class DoorCollition(doorPosition: Vector2,val areaId: AreaIdentifier, val connection: DoorConnection,
                    val triggerDirection: Direction): MoveCollition{

    override var canMoveAfterCollition = true
    init {
        if(triggerDirection== Direction.DOWN || triggerDirection== Direction.LEFT){
            connection.secondEntrance = Vector2(doorPosition)
        }
        else{
            connection.firstEntrance = Vector2(doorPosition)
        }
    }
    override fun collitionHappened(collidedObject: GameObject) {
        canMoveAfterCollition = true
        if(collidedObject is Player)
        {
            if(player.direction == triggerDirection){
                val playerPosAfter = getPlayerPos(connection,triggerDirection)
                if(areaId != AreaIdentifier.NOTIMPLEMENTED){
                    val playerPosMiddle = Vector2(playerPosAfter.x + playerSize.x / 2, playerPosAfter.y)
                    player.setPosition(playerPosMiddle)
                    AreaManager.SetArea(AreaManager.getArea(areaId))
                    ResetAreaEnteredCollitions()
                    canMoveAfterCollition = false
                }
            }
        }
    }

    fun getPlayerPos(connection: DoorConnection,triggerDirection: Direction): Vector2{
        return when(triggerDirection){
            Direction.UP -> (Vector2(connection.secondEntrance.x, connection.secondEntrance.y + playerSize.y / 4))
            Direction.DOWN-> Vector2(connection.firstEntrance.x,connection.firstEntrance.y - playerSize.y)
            Direction.RIGHT -> (Vector2(connection.secondEntrance.x + playerSize.x * 2, connection.secondEntrance.y + playerSize.y / 4))
            Direction.LEFT-> Vector2(connection.firstEntrance.x - playerSize.x * 2 ,connection.firstEntrance.y + playerSize.y / 4 )
        }
    }
}