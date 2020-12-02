package com.mygdx.game

import com.badlogic.gdx.math.Vector2

val doorMainAreaAndDungeonConnection = DoorConnection()
val doorMainAreaAndShop = DoorConnection()
class DoorConnection() {
    var firstEntrance = Vector2(0f,0f)
    var secondEntrance = Vector2(0f,0f)
}