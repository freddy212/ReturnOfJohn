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
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.Boss
import com.mygdx.game.Interfaces.FightableEntity

class SandGhost(Position: Vector2, size: Vector2 = Vector2(150f,150f),location: DefaultLocation?)
    : Boss(Position, size, location),
    SaveStateEntity by DefaultRemoveObjectSaveState(){
    override val texture = DefaultTextureHandler.getTexture("BossFace.png")
    override val layer = Layer.PERSON
    override var direction = Direction.DOWN
    val sandHand1 = SandHand(currentPosition() - Vector2(200f,0f),Vector2(143f,128f), location, false, this)
    val sandHand2 = SandHand(currentPosition() + Vector2(200f,0f),Vector2(143f,128f), location, true, this)
    private val randomAction = RandomAction(listOf( EnemyMove(100f,::getUnitVectorTowardsPoint)),DefaultTimer(1f))
    override val enemyStrategy =  DefaultEnemyStrategy(listOf(randomAction, MoveRight(), ExtendArms(sandHand1,sandHand2,this), GhostBoulder(this)))
    override var health = 100f
    override val maxHealth = 100f

    init{
        polygon.setScale(0.8f,0.8f)
    }

    override fun addToLocation(location: DefaultLocation) {
        listOf(sandHand1,sandHand2).forEach { it.addToLocation(location) }
        super.addToLocation(location)
    }

    override fun removeFromLocation() {
        super.removeFromLocation()
        listOf(sandHand1,sandHand2).forEach { it.removeFromLocation() }
    }

    override fun HitAction(other: GameObject, thisEntity: FightableEntity) {
        isHit(Vector2(0f,0f))
    }

    override fun isHit(launchUnitVector: Vector2) {
        setAggroed()
        loseHealth(10f)
    }

    override fun setRotation(unitVectorDirection: Vector2, gameObject: GameObject, angleModifier: Float) {
    }

    override var currentSpeed = 3f
}