package com.mygdx.game.GameObjects.MoveableEntities.Projectiles

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.DefaultMovement
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.Projectile
import com.mygdx.game.Collitions.BoulderCollition
import com.mygdx.game.Collitions.DefaultProjectileCollition
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.EdgeOfLocationStrategies.RemoveObject
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.Interfaces.DefaultCollitionMask
import com.mygdx.game.ItemAbilities.Shield
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.middleOfObject

class Boulder(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation?,unitVectorDirection: Vector2, shooter: GameObject):
        Projectile(middleOfObject(Position,size),size,defaultLocation,unitVectorDirection, shooter){
    override val movementStrategy = DefaultMovement(RemoveObject())
    override val texture = DefaultTextureHandler.getTexture("Boulder.png")
    override var baseSpeed = 5f
    override val layer = Layer.AIR
    override val collition = BoulderCollition(this)
    override val collitionMask = DefaultCollitionMask {x -> x is Player || x is Projectile || x.collition is IllegalMoveCollition || x is Shield }
}