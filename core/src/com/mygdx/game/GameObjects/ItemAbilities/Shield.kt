package com.mygdx.game.GameObjects.ItemAbilities

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.ItemAbility
import com.mygdx.game.AbstractClasses.DefaultPositionChange
import com.mygdx.game.Collitions.ShieldCollition
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.DynamicEntity
import com.mygdx.game.Managers.LocationManager

class Shield(Position: Vector2, size: Vector2): ItemAbility(Position, size),DynamicEntity by DefaultPositionChange{

    override val triggerKey = com.badlogic.gdx.Input.Keys.NUM_2
    val textureFront = Texture("shield-front.png")
    val textureSide = Texture("shield-side.png")
    var activeTexture = textureFront

    val frontSprite = InitSprite(textureFront)
    val sideSprite = InitSprite(textureSide)
    var activeSprite = frontSprite

    override val collition = ShieldCollition()



    init {
        sideSprite.setSize(sideSprite.width / 4, sideSprite.height)
    }

    val frontPolygon = RectanglePolygon(frontSprite.boundingRectangle)
    val sidePolygon = RectanglePolygon(sideSprite.boundingRectangle)
    var activePolygon = frontPolygon

    override val texture
    get() = activeTexture
    override val sprite
    get() = activeSprite
    override val polygon
    get() = activePolygon
    override var layer = Layer.AIR

    private fun getPos():Vector2{
        val position = when(player.direction){
            Direction.UP -> Vector2(0f,5f)
            Direction.DOWN-> Vector2(0f, - player.size.y /  2 - 5f)
            Direction.LEFT -> Vector2(0f - 5f, - sprite.height / 2)
            Direction.RIGHT -> Vector2(player.size.x + 5f,- sprite.height / 2)
        }

        return position + Vector2(player.sprite.x + (player.size.x - size.x) / 2, player.sprite.y + player.size.y / 2)
    }

    fun setActiveSide(){
        activeTexture = when(player.direction){
            Direction.UP,Direction.DOWN -> textureFront
            Direction.RIGHT,Direction.LEFT -> textureSide
        }
        activeSprite = when(player.direction){
            Direction.UP,Direction.DOWN -> frontSprite
            Direction.RIGHT,Direction.LEFT -> sideSprite }
        activePolygon =  when(player.direction){
            Direction.DOWN,Direction.UP -> frontPolygon
            Direction.RIGHT,Direction.LEFT -> sidePolygon
        }
    }

    override fun frameTask() {
        super.frameTask()
        setActiveSide()
        val position = getPos()
        setPosition(position,this)
        layer = when(player.direction){
            Direction.UP,Direction.LEFT,Direction.RIGHT -> Layer.ONGROUND
            else -> Layer.AIR
        }
        sprite.setOriginCenter()
        if(player.direction == Direction.LEFT){
            sprite.rotation = 180f
        }else{
            sprite.rotation = 0f
        }
        handleCollitions(this,polygon, LocationManager.MoveCollitionGameObjects)
    }
    override fun activeAction(){
        super.activeAction()
        player.freezeMoving()
    }

    override fun InactiveAction() {
        super.InactiveAction()
        player.enableMoving()
    }
}