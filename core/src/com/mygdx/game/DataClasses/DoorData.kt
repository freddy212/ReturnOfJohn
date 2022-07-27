package com.mygdx.game.DataClasses

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Interfaces.AreaIdentifier

data class DoorData(val position: Vector2, val areaFrom: AreaIdentifier, val areaTo: AreaIdentifier, val location: String, val direction: Direction, val connectionKey: String ,val size: Vector2 = Vector2(64f,64f), val textureName: String = "EmptyDoor.png") {

}