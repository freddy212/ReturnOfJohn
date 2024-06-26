package com.mygdx.game.GameObjects.ItemAbilities

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.CharacterAbility
import com.mygdx.game.AbstractClasses.DefaultPositionChange
import com.mygdx.game.AbstractClasses.DefaultRotationalObject
import com.mygdx.game.AbstractClasses.RotationalObject
import com.mygdx.game.Collitions.ShieldCollition
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.DynamicEntity

class ShieldAbility(Position: Vector2, size: Vector2): CharacterAbility(Position, size),DynamicEntity by DefaultPositionChange,
            RotationalObject by DefaultRotationalObject(){
    override val texture = DefaultTextureHandler.getTexture("shield-side.png")
    override val triggerKey = com.badlogic.gdx.Input.Keys.NUM_2
    override val toolTipTexture = DefaultTextureHandler.getTexture("shield-front.png")

    override val collition = ShieldCollition()

    override val layer = Layer.AIR

    private fun getPos():Vector2{
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
        super.frameTask()
        setActiveSide()
        setPosition(getPos(),this)
        //handleCollitions(this,polygon, LocationManager.MoveCollitionGameObjects)
    }

    override fun activeAction(){
        super.activeAction()
        setActiveSide()
        setPosition(getPos(),this)
        player.freezeMoving()
    }

    override fun InactiveAction() {
        super.InactiveAction()
        player.enableMoving()
    }
}