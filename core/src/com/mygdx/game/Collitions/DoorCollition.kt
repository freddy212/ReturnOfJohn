package com.mygdx.game.Collitions

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.MoveableEntity
import com.mygdx.game.Center
import com.mygdx.game.Enums.Direction
import com.mygdx.game.GameObjects.Door
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Interfaces.Collition
import com.mygdx.game.Managers.AreaManager
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Player
import com.mygdx.game.camera
import com.mygdx.game.counter

class DoorCollition(val areaId: AreaIdentifier, val playerPosAfter: Vector2): Collition {
    override fun collitionHappened(entity: MoveableEntity, collitionPosition: Vector2, collidedObject: GameObject) {
        val door = collidedObject as Door
        if(entity is Player)
        {
            if(entity.direction == Direction.UP){
                counter++
                println("Doorcolltion is finally achieved" + counter)
                if(areaId != AreaIdentifier.NOTIMPLEMENTED){
                    entity.sprite.setPosition(playerPosAfter.x, playerPosAfter.y)
                    camera.position.set(playerPosAfter.x,playerPosAfter.y,0f)
                    LocationManager.activeArea = AreaManager.getArea(areaId)
                }
            }
        }
    }
}