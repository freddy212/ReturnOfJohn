package com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.RockBoss

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AI.EnemyActions.MoveBasedOnPlayer
import com.mygdx.game.AI.EnemyActions.Roll
import com.mygdx.game.AI.EnemyActions.ShootProjectile
import com.mygdx.game.AbstractClasses.DefaultEnemyStrategy
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Element
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Events.DefaultEvent
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.Boss
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Fireball
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Icicle
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.SmallBoulder
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.ObjectProperties.Fire
import com.mygdx.game.ObjectProperties.Ice
import com.mygdx.game.getUnitVectorTowardsPoint

class RockBoss(Position: Vector2, size: Vector2, location: DefaultLocation?, element: Element) : Boss(Position, size, location) {
    override val texture = DefaultTextureHandler.getTexture("RockMan.png")
    override var baseSpeed = 2f
    override val layer = Layer.ONGROUND
    override var direction = Direction.DOWN
    override var health = 50f
    override val maxHealth = 50f

    val projectile = when(element){
        Element.FIRE -> ::Fireball
        Element.ICE -> ::Icicle
        Element.EARTH -> ::SmallBoulder
    }
    val projSize = when(element){
        Element.FIRE -> Vector2(100f,33f)
        Element.ICE -> Vector2(100f,33f)
        Element.EARTH -> Vector2(50f,50f)
    }
    override val enemyStrategy =  DefaultEnemyStrategy(listOf(ShootProjectile(projectile,projSize,this), Roll(MoveBasedOnPlayer(0f,::getUnitVectorTowardsPoint, this),this)
    ))

    init {
        val property = when(element){
            Element.FIRE -> Fire(DefaultEvent(), this)
            Element.ICE -> Ice(this)
            Element.EARTH -> null
        }
        if(property != null){
            properties.add(property)
        }
    }

}