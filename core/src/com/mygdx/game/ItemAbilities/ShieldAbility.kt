package com.mygdx.game.ItemAbilities

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.*
import com.mygdx.game.Collitions.ShieldCollition
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Events.ActionAfterFramesEvent
import com.mygdx.game.Interfaces.DynamicEntity
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Managers.EventManager
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Timer.DefaultTimer

open class ShieldAbility(): CharacterAbility(){
    override val abilityId = AbilityId.SHIELD
    override val triggerKey = com.badlogic.gdx.Input.Keys.NUM_2
    override val texture = DefaultTextureHandler.getTexture("shield-front.png")
    override val cooldownTimer = DefaultTimer(3f)
    val shield = Shield(Vector2(0f,0f),
        Vector2(20f,40f))

    override fun activeAction(){
        shield.setPosition(shield.getPos(),shield)
        shield.setActiveSide()
        LocationManager.newDefaultLocation.addGameObject(shield)
        player.freezeMoving()
        EventManager.eventManager.add(ActionAfterFramesEvent(120f, ::inactiveAction))
    }

    override fun inactiveAction() {
        super.inactiveAction()
        shield.removeFromLocation()
        player.enableMoving()
    }
}

class Shield(Position: Vector2, size: Vector2): GameObject(Position, size),DynamicEntity by DefaultPositionChange,
    RotationalObject by DefaultRotationalObject() {
    override val texture = DefaultTextureHandler.getTexture("shield-side.png")
    override var collition = ShieldCollition(this)
    override val layer = Layer.AIR

    fun getPos():Vector2{
        return Vector2(player.sprite.x + player.sprite.width - 10f,player.sprite.y)
    }
    init {
        polygon.setOrigin(- player.sprite.width / 2 + 10f, player.sprite.height / 2)
        sprite.setOrigin(- player.sprite.width / 2 + 10f, player.sprite.height / 2)
    }
    fun setActiveSide(){
        setRotation(player.unitVectorDirection,this,0f)
    }

    override fun frameTask() {
        setPosition(getPos(),this)
        setActiveSide()
        super.frameTask()
    }
}