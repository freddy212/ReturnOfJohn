package com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.SandGhost

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AI.EnemyActions.EnemyMove
import com.mygdx.game.AI.EnemyActions.RandomAction
import com.mygdx.game.AI.EnemyActions.ShootProjectile
import com.mygdx.game.AbstractClasses.DefaultEnemyStrategy
import com.mygdx.game.AbstractClasses.Enemy
import com.mygdx.game.Collitions.CanMoveCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.SmallBoulder
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.SaveHandling.DefaultRemoveObjectSaveState
import com.mygdx.game.SaveState.SaveStateEntity
import com.mygdx.game.Timer.DefaultTimer
import com.mygdx.game.getOppositeUnitVector
import com.mygdx.game.getUnitVectorTowardsPoint
import com.mygdx.game.*
import com.mygdx.game.AI.EnemyActions.MoveRight
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Enums.CharacterState
import com.mygdx.game.Enums.getDirectionUnitVector
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.Boss
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.Sartan.Trifork
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.Sartan.TriforkThrow
import com.mygdx.game.Interfaces.FightableEntity

class Sartan(Position: Vector2, size: Vector2 = Vector2(150f,150f),location: DefaultLocation?)
    : Boss(Position, size, location),
    SaveStateEntity by DefaultRemoveObjectSaveState(){
    override val texture = DefaultTextureHandler.getTexture("DefaultPerson.png")
    override val layer = Layer.PERSON
    override var direction = Direction.DOWN
    override var health = 100f
    override val maxHealth = 100f
    override val stunDuration = 3
    override var currentSpeed = 3f
    override var unitVectorDirection = getDirectionUnitVector(Direction.DOWN)
    val trifork = Trifork(currentPosition() + Vector2(0f,-30f), Vector2(40f,100f), defaultLocation,this)
    private val randomAction = RandomAction(listOf( EnemyMove(200f,::getUnitVectorTowardsPoint)),DefaultTimer(1f))
    override val enemyStrategy =  DefaultEnemyStrategy(listOf(randomAction,TriforkThrow(this,trifork)))


    override fun addToLocation(location: DefaultLocation) {
        trifork.addToLocation(location)
        super.addToLocation(location)
    }

    override fun move(unitVectorDirection: Vector2): Boolean {
        //trifork.move(unitVectorDirection)
        return super.move(unitVectorDirection)
    }

    override fun death() {
        super.death()
        trifork.removeFromLocation()
    }
}