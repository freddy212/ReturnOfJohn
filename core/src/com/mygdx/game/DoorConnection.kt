package com.mygdx.game

import com.badlogic.gdx.math.Vector2

val doorWastelandAndDungeonConnection = DoorConnection()
val doorMainAreaAndShop = DoorConnection()
val doorMainAreaAndDojo = DoorConnection()
val doorMainAreaAndFireLands = DoorConnection()
val doorFireLandsAndDungeonConnection = DoorConnection()
val doorIceLandsAndDungeonConnection = DoorConnection()
val doorMainAreaAndIceLands = DoorConnection()
val doorMainAreaAndWasteland = DoorConnection()
val doorWastelandAndMainAreaConnection2 = DoorConnection()
class DoorConnection() {
    var firstEntrance = Vector2(0f,0f)
    var secondEntrance = Vector2(0f,0f)
}