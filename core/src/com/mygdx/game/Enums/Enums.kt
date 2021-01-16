package com.mygdx.game.Enums

import com.badlogic.gdx.math.Vector2

enum class Direction{UP,LEFT,RIGHT,DOWN}
enum class Item{WORLDLEAF,KEY}
enum class Layer {BEFORELOCATION,GROUND,ONGROUND,PERSON,AIR,FOREGROUND}
enum class ConversationState {BEFORE,ONGOING}
enum class CharacterState {HIT,FREE}

fun getDirectionUnitVector(direction: Direction): Vector2{
   return when(direction){
       Direction.UP -> Vector2(0f,1f)
       Direction.LEFT -> Vector2(-1f, 0f)
       Direction.RIGHT -> Vector2(1f, 0f)
       Direction.DOWN -> Vector2(0f,-1f)
    }
}