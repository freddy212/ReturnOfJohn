package com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.RockBoss

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AI.EnemyActions.EnemyMove
import com.mygdx.game.AI.EnemyActions.EnemyRoll
import com.mygdx.game.AI.EnemyActions.ShootProjectile
import com.mygdx.game.AbstractClasses.DefaultEnemyStrategy
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Elements
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Events.DefaultEvent
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.Boss
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Fireball
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Icicle
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.SmallBoulder
import com.mygdx.game.Interfaces.EnemyStrategy
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.ObjectProperties.Fire
import com.mygdx.game.ObjectProperties.Ice
import com.mygdx.game.getUnitVectorTowardsPoint

class RockBoss(Position: Vector2, size: Vector2, location: DefaultLocation?, element: Elements) : Boss(Position, size, location) {
    override val texture = DefaultTextureHandler.getTexture("RockMan.png")
    override var baseSpeed = 2f
    override val layer = Layer.ONGROUND
    override var direction = Direction.DOWN
    override var health = 50f
    override val maxHealth = 50f

    val projectile = when(element){
        Elements.FIRE -> ::Fireball
        Elements.ICE -> ::Icicle
        Elements.EARTH -> ::SmallBoulder
    }
    val projSize = when(element){
        Elements.FIRE -> Vector2(100f,33f)
        Elements.ICE -> Vector2(100f,33f)
        Elements.EARTH -> Vector2(50f,50f)
    }
    override val enemyStrategy =  DefaultEnemyStrategy(listOf(ShootProjectile(projectile,projSize,this), EnemyRoll(EnemyMove(0f,::getUnitVectorTowardsPoint, this),this)
    ))

    init {
        val property = when(element){
            Elements.FIRE -> Fire(DefaultEvent(), this)
            Elements.ICE -> Ice(this)
            Elements.EARTH -> null
        }
        if(property != null){
            properties.add(property)
        }
    }

}