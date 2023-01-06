package com.mygdx.game.Signal

import com.mygdx.game.Signal.Signals.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

fun signalConvert(signalString: String): Signal{
    val processedString = signalString.split(",")[0] + '}'
    val newSignal:Signal = Json.decodeFromString(processedString)


    return when(newSignal.signaltype){
        SIGNALTYPE.ITEM_PICKED_UP -> Json.decodeFromString<ItemPickedUpSignal>(signalString)
        SIGNALTYPE.USE_ITEMS -> Json.decodeFromString<UseItemsSignal>(signalString)
        SIGNALTYPE.BUTTON_ACCEPTED -> Json.decodeFromString<ButtonAcceptedSignal>(signalString)
        SIGNALTYPE.CHANGE_CONVERSATION-> Json.decodeFromString<ChangeConversationSignal>(signalString)
        SIGNALTYPE.ADD_OBJECT -> Json.decodeFromString<AddObjectSignal>(signalString)
        SIGNALTYPE.MOVE_OBJECT-> Json.decodeFromString<MoveObjectSignal>(signalString)
        SIGNALTYPE.CHANGE_OBJECT_LOCATION-> Json.decodeFromString<ChangeObjectLocationSignal>(signalString)
        SIGNALTYPE.ABILITY_GAINED-> Json.decodeFromString<AbilityGainedSignal>(signalString)
        SIGNALTYPE.REMOVE_OBJECT-> Json.decodeFromString<RemoveObjectSignal>(signalString)
    }
}