package com.mygdx.game.Areas.IceLandsDungeon

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.DoorCollition
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.getDirectionUnitVector
import com.mygdx.game.GameObjects.Door
import com.mygdx.game.GameObjects.Hazards.Generators.IceGenerator
import com.mygdx.game.GameObjects.ItemAbilities.IceCloneAbility
import com.mygdx.game.GameObjects.ItemObjects.AbilityItemObject
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Managers.LocationManager

fun getIceLandsDungeonLocationOneObjects(): List<GameObject>{
    val location1 = LocationManager.findLocation("location1", AreaIdentifier.ICELANDSDUNGEON)
    val doorPosition = Vector2(location1.originalMiddle.x +  (playerSize.x / 2) + location1.width / 3,location1.bottomleft.y)
    val doorCollition = DoorCollition(doorPosition,
        AreaIdentifier.ICELANDS, doorIceLandsAndDungeonConnection,
        Direction.DOWN)
    val door = Door(doorPosition, Vector2(32f * 2, 64f * 2), DefaultTextureHandler.getTexture("CaveDoor.png"),location1,
        Direction.DOWN,doorCollition)
    return listOf(door)
}

fun getIceLandsDungeonLocationTwoObjects(): List<GameObject>{
    val location2 = LocationManager.findLocation("location2",AreaIdentifier.ICELANDSDUNGEON)
    val iceGenerator = IceGenerator(location2.topleft - Vector2(0f,100f), Vector2(100f,100f), getDirectionUnitVector(Direction.DOWN),location2,0f,0.8f)
    val iceCloneAbility = AbilityItemObject(location2.topleft + Vector2(-500f,-300f),
                                           Vector2(80f,80f),
                                           location2,
                                           IceCloneAbility(),
                                           DefaultTextureHandler.getTexture("IceClone.png")

    )
    return listOf(iceGenerator,iceCloneAbility)
}