package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.Projectile
import com.mygdx.game.AbstractClasses.ProjectileCollition
import com.mygdx.game.Enums.Element
import com.mygdx.game.Events.DefaultEvent
import com.mygdx.game.GameObjects.Hazards.Generators.BoulderGenerator
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.SandGhost.SandHand
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Boulder
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Fireball
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Icicle
import com.mygdx.game.Locations.DamageLocationData
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.ObjectProperties.Fire
import com.mygdx.game.ObjectProperties.Ice
import com.mygdx.game.ObjectProperties.ROJParticleObject


class BoulderCollition(val boulder: Projectile) : ProjectileCollition(boulder) {
    override fun collitionHappened(collidedObject: GameObject) {
        if (collidedObject !is SandHand) {
            super.collitionHappened(collidedObject)
        }
        if ((collidedObject is BoulderGenerator || collidedObject is Boulder)) {
            collidedObject.removeFromLocation()
            boulder.removeFromLocation()
        }

        if(collidedObject is Projectile){
            if(collidedObject is Fireball && boulder.properties.List.filterIsInstance<Fire>().isEmpty()){
                boulder.properties.add(Fire(DefaultEvent(),boulder))
            }
            if(collidedObject is Icicle && boulder.properties.List.filterIsInstance<Ice>().isEmpty()){
                boulder.properties.add(Ice(boulder))
            }
            collidedObject.removeFromLocation()
        }
        if(collidedObject is DefaultLocation && collidedObject.locationStrategy is DamageLocationData && boulder.defaultLocation != null){
            val locationData = collidedObject.locationStrategy
            val location = boulder.defaultLocation!!
            if(locationData.element == Element.FIRE && !boulder.properties.List.any { it is Fire }){
                boulder.properties.clear()
                boulder.properties.add(Fire(DefaultEvent(), boulder))
            }
            if(locationData.element == Element.ICE && !boulder.properties.List.any { it is Ice }){
                boulder.properties.clear()
                boulder.properties.add(Ice(boulder))
            }
        }
    }
    override var canMoveAfterCollition = true
}