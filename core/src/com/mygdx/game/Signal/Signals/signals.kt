package com.mygdx.game.Signal.Signals

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.AbilityId
import com.mygdx.game.AbstractClasses.CharacterAbility
import com.mygdx.game.Enums.ItemType
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Signal.Signal
import com.mygdx.game.Signal.SignalListeners.ADDMETHODS
import kotlinx.serialization.Serializable

enum class SIGNALTYPE { ITEM_PICKED_UP, REMOVE_OBJECT, ABILITY_GAINED, USE_ITEMS, CHANGE_OBJECT_LOCATION, MOVE_OBJECT, ADD_OBJECT, CHANGE_CONVERSATION, BUTTON_ACCEPTED, MAX_HEALTH_GAINED, ADD_ABILITY_ITEM, AREA_CHANGE}

@Serializable
class ItemPickedUpSignal(val itemType: ItemType) : Signal(SIGNALTYPE.ITEM_PICKED_UP) {

}

@Serializable
class RemoveObjectSignal(val entityId: Int) : Signal(SIGNALTYPE.REMOVE_OBJECT) {

}

@Serializable
class AbilityGainedSignal(val abilityId: AbilityId) : Signal(SIGNALTYPE.ABILITY_GAINED) {

}

@Serializable
class UseItemsSignal(val itemType: ItemType, val amount: Int) : Signal(SIGNALTYPE.USE_ITEMS) {

}

@Serializable
class ChangeObjectLocationSignal(val entityId: Int, val location: String, val area: AreaIdentifier) :
    Signal(SIGNALTYPE.CHANGE_OBJECT_LOCATION) {

}

@Serializable
class MoveObjectSignal(val entityId: Int, val x: Float, val y: Float) : Signal(SIGNALTYPE.MOVE_OBJECT) {

}

@Serializable
class AddObjectSignal(val addMethod: ADDMETHODS, val location: String, val areaIdentifier: AreaIdentifier) :
    Signal(SIGNALTYPE.ADD_OBJECT) {

}

@Serializable
class ChangeConversationSignal(val entityId: Int, val conversationId: String) : Signal(SIGNALTYPE.CHANGE_CONVERSATION) {

}

@Serializable
class ButtonAcceptedSignal(val entityId: Int) : Signal(SIGNALTYPE.BUTTON_ACCEPTED) {

}

@Serializable
class MaxHealthGainedSignal(val health: Int) : Signal(SIGNALTYPE.MAX_HEALTH_GAINED)

@Serializable
class AddAbilityItemSignal(val locationName: String, val area: AreaIdentifier, val x: Float, val y: Float,val abilityId: AbilityId): Signal(SIGNALTYPE.ADD_ABILITY_ITEM)

@Serializable
class AreaChangeSignal(val areaIdentifier: AreaIdentifier) : Signal(SIGNALTYPE.AREA_CHANGE){

}