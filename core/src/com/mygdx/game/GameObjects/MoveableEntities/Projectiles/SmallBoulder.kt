package com.mygdx.game.GameObjects.MoveableEntities.Projectiles

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.DefaultMovement
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.Projectile
import com.mygdx.game.Collitions.BoulderCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.EdgeOfLocationStrategies.RemoveObject
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.middleOfObject

class SmallBoulder(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation?,unitVectorDirection: Vector2, shooter: GameObject):
    Projectile(middleOfObject(Position,size),size,defaultLocation,unitVectorDirection, shooter){
    override val movementStrategy = DefaultMovement(RemoveObject())
    override val texture = DefaultTextureHandler.getTexture("Boulder.png")
    override var baseSpeed = 8f
    override val layer = Layer.AIR
    override val collition = BoulderCollition(this)
}