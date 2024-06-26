package com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.SandGhost

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AI.EnemyActions.MoveBasedOnPlayer
import com.mygdx.game.AbstractClasses.DefaultEnemyStrategy
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Saving.SaveStateEntity
import com.mygdx.game.getUnitVectorTowardsPoint
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.Boss
import com.mygdx.game.GameObjects.SensorObjects.SandGhostSleeping
import com.mygdx.game.Managers.AreaManager
import com.mygdx.game.Managers.SignalManager
import com.mygdx.game.Saving.DefaultSaveStateHandler
import com.mygdx.game.Signal.Signal
import com.mygdx.game.Signal.Signals.RemoveObjectSignal

class SandGhost(Position: Vector2, size: Vector2 = Vector2(150f,150f),location: DefaultLocation?, signal: Signal?, val entityIdToRemove: Int)
    : Boss(Position, size, location, signal),
    SaveStateEntity by DefaultSaveStateHandler(){
    override val texture = DefaultTextureHandler.getTexture("BossFace.png")
    override val layer = Layer.PERSON
    override var direction = Direction.DOWN
    val sandHand1 = SandHand(currentPosition() - Vector2(200f,0f),Vector2(143f,128f), location, false, this)
    val sandHand2 = SandHand(currentPosition() + Vector2(200f,0f),Vector2(143f,128f), location, true, this)
    private val enemyMove = MoveBasedOnPlayer(100f,::getUnitVectorTowardsPoint, this)
    override val enemyStrategy =  DefaultEnemyStrategy(listOf(enemyMove, ExtendArms(sandHand1,sandHand2,this), GhostBoulder(this)))
    override var health = 100f
    override val maxHealth = 100f

    init{
        polygon.setScale(0.8f,0.8f)
        attachedMusic = MusicLoader.sandGhostMusic
    }

    override fun addToLocation(location: DefaultLocation) {
        listOf(sandHand1,sandHand2).forEach { it.addToLocation(location) }
        super.addToLocation(location)
    }

    override fun removeFromLocation() {
        super.removeFromLocation()
        listOf(sandHand1,sandHand2).forEach { it.removeFromLocation() }
    }

    override fun isHit(other:GameObject) {
        if(!isAggroed()){
            setAggroed()
        }
        loseHealth(10f)
    }

    override fun setRotation(unitVectorDirection: Vector2, gameObject: GameObject, angleModifier: Float) {
    }

    override var baseSpeed = 2f

    override fun death() {
        super.death()
        SignalManager.emitSignal(RemoveObjectSignal(entityIdToRemove))
    }
}