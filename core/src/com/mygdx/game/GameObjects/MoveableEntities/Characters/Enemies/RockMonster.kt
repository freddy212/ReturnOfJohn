package com.mygdx.game.GameObjects.MoveableEntities.Characters

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AI.EnemyActions.ChangeDirection
import com.mygdx.game.AI.EnemyActions.EnemyMove
import com.mygdx.game.AI.EnemyActions.RandomAction
import com.mygdx.game.AI.EnemyActions.ShootProjectile
import com.mygdx.game.AbstractClasses.DefaultCharacter
import com.mygdx.game.AbstractClasses.DefaultEnemyStrategy
import com.mygdx.game.AbstractClasses.Enemy
import com.mygdx.game.DefaultModelInstanceHandler
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Fireball
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.SmallBoulder
import com.mygdx.game.Interfaces.EnemyStrategy
import com.mygdx.game.Interfaces.ModelInstanceHandler
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.SaveHandling.DefaultRemoveObjectSaveState
import com.mygdx.game.SaveState.SaveStateEntity
import com.mygdx.game.Timer.DefaultTimer
import com.mygdx.game.getOppositeUnitVector
import com.mygdx.game.getUnitVectorTowardsPoint

class RockMonster(Position: Vector2, size: Vector2 = Vector2(128f,128f),location: DefaultLocation?,
                 modelHandler: ModelInstanceHandler = DefaultModelInstanceHandler("ManBlender.g3db",Position,size))
    : Enemy(Position, size, location,modelHandler,100f),
    SaveStateEntity by DefaultRemoveObjectSaveState(){
    override val texture = DefaultTextureHandler.getTexture("DefaultPerson.png")
    override val layer = Layer.PERSON
    override var currentSpeed = 2f
    override var direction = Direction.UP
    private val randomAction = RandomAction(listOf( EnemyMove(0f,::getUnitVectorTowardsPoint),
        EnemyMove(200f,::getUnitVectorTowardsPoint),
        EnemyMove(200f,::getOppositeUnitVector)),DefaultTimer(2f))
    override val enemyStrategy =  DefaultEnemyStrategy(listOf(randomAction,
        ShootProjectile(::SmallBoulder, Vector2(50f, 50f))))
    override var health = 50f
    override val maxHealth = 50f



}