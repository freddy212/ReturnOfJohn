package com.mygdx.game.GameObjects.Generators

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.*
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Enums.getDirectionUnitVector
import com.mygdx.game.Events.DefaultEvent
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Boulder
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Icicle
import com.mygdx.game.Interfaces.FightableEntity
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.ObjectProperties.Fire
import com.mygdx.game.SaveHandling.DefaultRemoveObjectSaveState
import com.mygdx.game.SaveState.SaveStateEntity
import com.mygdx.game.Timer.DefaultTimer
import com.mygdx.game.Trimer.DelayTimer
import com.mygdx.game.Utils.RectanglePolygon

class IceGenerator(Position: Vector2, size: Vector2, unitVectorDirection: Vector2, defaultLocation: DefaultLocation,
                       timeUntilFire: Float = 0f, shootCoolDown:Float = 3f):
    Generator(Position, size,defaultLocation,unitVectorDirection,timeUntilFire,shootCoolDown){

    override fun generateProjectile(){
        val Position = Vector2(this.sprite.x,this.sprite.y) + getDistanceFromGenerator(unitVectorDirection)
        val icicle = Icicle(Position,Vector2(100f,34f),defaultLocation,Vector2(unitVectorDirection.x,unitVectorDirection.y))
        icicle.collition = object : ProjectileCollition() {
            override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
                if(entity is Projectile && collidedObject is FightableEntity){
                    (collidedObject as DefaultCharacter).isHit(getDirectionUnitVector(Direction.DOWN))
                }
            }

            override val canMoveAfterCollition = true
        }
        defaultLocation!!.addGameObject(icicle)
    }
}