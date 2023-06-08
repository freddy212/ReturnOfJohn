package com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.SandGhost

import EnemyCollition
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AI.EnemyActions.MoveBasedOnPlayer
import com.mygdx.game.AI.EnemyActions.RandomAction
import com.mygdx.game.AbstractClasses.DefaultEnemyStrategy
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Saving.SaveStateEntity
import com.mygdx.game.Timer.DefaultTimer
import com.mygdx.game.getUnitVectorTowardsPoint
import com.mygdx.game.*
import com.mygdx.game.AI.EnemyActions.Dash
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Enums.CharacterState
import com.mygdx.game.Enums.getDirectionUnitVector
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.Boss
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.Sartan.Trifork
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.Sartan.TriforkThrow
import com.mygdx.game.ItemAbilities.Shield
import com.mygdx.game.Saving.DefaultSaveStateHandler
import com.mygdx.game.Signal.Signal

class Sartan(Position: Vector2, size: Vector2 = Vector2(150f,150f),location: DefaultLocation?, signal: Signal?)
    : Boss(Position, size, location, signal),
    SaveStateEntity by DefaultSaveStateHandler() {
    override val texture = DefaultTextureHandler.getTexture("DefaultPerson.png")
    override val layer = Layer.PERSON
    override var direction = Direction.DOWN
    override var health = 100f
    override val maxHealth = 100f
    override val stunDuration = 3
    override var baseSpeed = 4f
    override var unitVectorDirection = getDirectionUnitVector(Direction.DOWN)
    override val collition = SartanCollition(this)
    val trifork = Trifork(currentPosition() + Vector2(0f,-30f), Vector2(40f,100f), defaultLocation,this)
    private val randomAction = RandomAction(listOf( MoveBasedOnPlayer(200f,::getUnitVectorTowardsPoint, this)),DefaultTimer(1f), this)
    override val enemyStrategy =  DefaultEnemyStrategy(listOf(randomAction,TriforkThrow(this,trifork), Dash(this)))


    override fun addToLocation(location: DefaultLocation) {
        trifork.addToLocation(location)
        super.addToLocation(location)
    }
    override fun removeFromLocation() {
        trifork.removeFromLocation()
        super.removeFromLocation()
    }
}

class SartanCollition(val sartan: Sartan): EnemyCollition(sartan){

    override fun collitionHappened(collidedObject: GameObject) {
        super.collitionHappened(collidedObject)

        if(collidedObject is Shield && sartan.characterState == CharacterState.DASHING){
            sartan.unitVectorDirection = - sartan.unitVectorDirection
            sartan.move(sartan.unitVectorDirection)
        }

    }

}