package com.mygdx.game.GameObjects.Hazards.Generators

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.*
import com.mygdx.game.Enums.Element
import com.mygdx.game.Events.DefaultEvent
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Boulder
import com.mygdx.game.Interfaces.ObjectProperty
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.ObjectProperties.Fire
import com.mygdx.game.ObjectProperties.Ice

class BoulderGenerator(Position: Vector2, size: Vector2, unitVectorDirection: Vector2, defaultLocation: DefaultLocation,
                       timeUntilFire: Float = 0f, shootCoolDown:Float = 3f, val element: Element = Element.EARTH):
    Generator(Position, size,defaultLocation,unitVectorDirection,timeUntilFire,shootCoolDown){

    override fun generateProjectile(): Projectile{
        val Position = Vector2(this.sprite.x + this.sprite.width/2,this.sprite.y + this.sprite.height /2) + getDistanceFromGenerator(unitVectorDirection)
        val boulder = Boulder(Position,Vector2(size.x - (size.x / 10) ,size.y - (size.y / 10)),defaultLocation,Vector2(unitVectorDirection.x,unitVectorDirection.y), this)
        val objectProperty = getPropertyBasedOnElement(element, boulder)
        if(objectProperty != null) boulder.properties.add(objectProperty)
        return boulder
    }


}