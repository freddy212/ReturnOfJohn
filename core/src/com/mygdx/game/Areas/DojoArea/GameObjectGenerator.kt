package com.mygdx.game.Areas.DojoArea

import com.mygdx.game.DefaultTextureHandler
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.DoorCollition
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.DefaultQuest
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Enums.QuestIdentifier
import com.mygdx.game.Events.DojoEvent
import com.mygdx.game.GameObjects.Door
import com.mygdx.game.GameObjects.GenericGameObject
import com.mygdx.game.GameObjects.MoveableEntities.DojoAttackObject
import com.mygdx.game.GameObjects.MoveableEntities.NPC
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Managers.EventManager
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.UI.Dialogue.Conversations.GetDojoConversation
import com.mygdx.game.doorMainAreaAndDojo
import com.mygdx.game.middleOfObject
import com.mygdx.game.playerSize

fun getDojoObjects(): List<GameObject>{
    val location1 = LocationManager.findLocation("location1", AreaIdentifier.DOJO)

    val doorPosition = Vector2(location1.middle.x -  (playerSize.x / 2),location1.bottomleft.y)

    val doorCollition = DoorCollition(doorPosition,AreaIdentifier.MAINAREA, doorMainAreaAndDojo ,Direction.DOWN)

    val door = Door(doorPosition, Vector2(32f * 2, 64f), DefaultTextureHandler.getTexture("Door.png"), location1,Direction.DOWN,doorCollition)
    val size = Vector2(120f,60f)
    val position = location1.middle
    val itemTable = GenericGameObject(middleOfObject(position,size),size,"ItemTable.png",Layer.ONGROUND,location1,IllegalMoveCollition)
    val dojoNPC = NPC(middleOfObject(Vector2(itemTable.middle.x,itemTable.middle.y + 200f),Vector2(128f,128f)), Vector2(128f,128f),location1)
    dojoNPC.conversationsHandler.addConversation("first", GetDojoConversation(dojoNPC))
    val quest = DefaultQuest(dojoNPC,QuestIdentifier.DOJO)
    quest.StartQuest()
    location1.onLocationExit = {
        val dojoEvent= EventManager.eventManager.List.find {it is DojoEvent} as DojoEvent?
        if(dojoEvent!= null){
            dojoNPC.add()
            EventManager.eventManager.remove(dojoEvent)
            val dojoAttackObject:DojoAttackObject? = location1.gameObjects.find{ it is DojoAttackObject } as DojoAttackObject?
            dojoAttackObject?.location?.removeGameObject(dojoAttackObject!!)
        }
    }
    return listOf(door,dojoNPC)
}