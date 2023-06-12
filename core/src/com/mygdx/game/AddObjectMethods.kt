package com.mygdx.game

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.DoorCollition
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.DataClasses.DoorData
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Item
import com.mygdx.game.Enums.ItemType
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.Other.Door
import com.mygdx.game.GameObjects.Other.GenericGameObject
import com.mygdx.game.ItemAbilities.ShieldAbility
import com.mygdx.game.GameObjects.ShopItem.ShopItem
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.LocationManager

fun spawnEngineerItems(location: DefaultLocation): List<GameObject> {
    val itemTable = GenericGameObject(
        location.topright - Vector2(200f, 150f),
        Vector2(120f, 60f), "ItemTable.png",
        Layer.ONGROUND, location,
        IllegalMoveCollition
    )
    val shopItem = ShopItem(
        "shield-front.png", listOf(
            Item(ItemType.FLINT, 3, DefaultTextureHandler.getTexture("Flint.png")),
            Item(ItemType.WOOD, 1, DefaultTextureHandler.getTexture("wood.png"))
        ),
        middleOfObject(itemTable.originalMiddle, Vector2(60f, 40f)), Vector2(60f, 40f), location, ShieldAbility()
    )

    return listOf(itemTable, shopItem)
}

fun spawnDoorOne(location: DefaultLocation): List<GameObject> {
    val wastelandDoorPosition = Vector2(location.bottomleft - Vector2(64f, 0f))
    val wastelandDoorCollition = DoorCollition(
        wastelandDoorPosition,
        AreaIdentifier.WASTELAND, doorWastelandAndMainAreaConnection2,
        Direction.LEFT
    )
    val wastelandDoor = Door(
        wastelandDoorPosition, Vector2(32f * 2, 32f * 2), DefaultTextureHandler.getTexture("EmptyDoor.png"), location,
        Direction.LEFT, wastelandDoorCollition
    )

    return listOf(wastelandDoor)
}

fun spawnFrostFireFireLandsDoor(location: DefaultLocation): List<GameObject> {
    val door = createDoor(
        DoorData(
            Vector2(location.originalMiddle.x - 32f, location.bottomleft.y),
            AreaIdentifier.FROSTFIRE, AreaIdentifier.FIRELANDS, "location2",
            Direction.DOWN, "FirelandsFrostfire"
        )
    )
    return listOf(door)
}

fun spawnFireLandsFrostFireDoor(location: DefaultLocation): List<GameObject> {
    val door = createDoor(
        DoorData(
            Vector2(location.currentMiddle.x - 100f, location.topleft.y),
            AreaIdentifier.FIRELANDS, AreaIdentifier.FROSTFIRE, "location12",
            Direction.UP, "FirelandsFrostfire"
        )
    )
    return listOf(door)
}

fun spawnDoorTwo(location: DefaultLocation): List<GameObject> {
    val mainAreaDoorPosition = Vector2(location.topright - Vector2(0f, 64f))
    val mainAreaDoorCollition = DoorCollition(
        mainAreaDoorPosition,
        AreaIdentifier.MAINAREA,
        doorWastelandAndMainAreaConnection2,
        Direction.RIGHT
    )
    val mainAreaDoor = Door(
        mainAreaDoorPosition, Vector2(32f * 2, 32f * 2), DefaultTextureHandler.getTexture("EmptyDoor.png"), location,
        Direction.RIGHT, mainAreaDoorCollition
    )

    return listOf(mainAreaDoor)
}