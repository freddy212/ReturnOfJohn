package com.mygdx.game.Areas.FrostfireArea

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.DataClasses.DoorData
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.getDirectionUnitVector
import com.mygdx.game.GameObjects.Hazards.Generators.BoulderGenerator
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.createDoor
import com.mygdx.game.plus

fun getFrostFireLocationOneObjects(): List<GameObject>{
    val location = LocationManager.findLocation("location1", AreaIdentifier.FROSTFIRE)
    val door = createDoor(
        DoorData(
            Vector2(location.originalMiddle.x - 32f, location.bottomleft.y),
            AreaIdentifier.FROSTFIRE, AreaIdentifier.ICELANDS,"location1",
            Direction.DOWN,"IcelandsFrostfire")
    )
    return listOf(door)
}
fun getFrostFireLocationFourObjects(): List<GameObject>{
    val location = LocationManager.findLocation("location4", AreaIdentifier.FROSTFIRE)
    val boulderGenerator = BoulderGenerator(location.bottomleft + Vector2(0f,100f), Vector2(128f,128f), getDirectionUnitVector(Direction.RIGHT),  location)
    return listOf(boulderGenerator)
}