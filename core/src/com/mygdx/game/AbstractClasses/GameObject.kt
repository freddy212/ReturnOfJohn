package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.Collitions.CanMoveCollition
import com.mygdx.game.Interfaces.*
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.ObjectProperties.ROJParticleObject
import com.mygdx.game.Utils.RenderGraph.Companion.addToSceneGraph
import com.mygdx.game.Utils.ResourceList

abstract class GameObject  (val initPosition: Vector2, val size: Vector2, var defaultLocation: DefaultLocation?): Renderable {
    val topleft = Vector2(initPosition.x,initPosition.y + size.y)
    val topright = Vector2(initPosition.x + size.x,initPosition.y + size.y)
    val bottomright =  Vector2(initPosition.x + size.x,initPosition.y)
    val bottomleft = initPosition
    val x = initPosition.x
    val y = initPosition.y
    val width = size.x
    val height = size.y

    val originalMiddle: Vector2 = Vector2(topleft.x + (topright.x - topleft.x) / 2,bottomleft.y + (topleft.y - bottomleft.y)/2)
    val currentMiddle: Vector2
    get() = Vector2(sprite.x + sprite.width/2, sprite.y + sprite.height/2)
    var startingPosition = initPosition

    //Remember this. Temporary solution. texture must be overriden before polygon is called
    abstract val texture: Texture
    open val sprite: Sprite by lazy { InitSprite(texture)}
    open val polygon: Polygon by lazy {InitPolygon(sprite)}
    open val shouldCollide = true
    open val collition: Collition = CanMoveCollition
    var collidingObjects: List<GameObject> = listOf()
    val properties = ResourceList<ROJParticleObject>()
    override fun render(batch: PolygonSpriteBatch){
        sprite.draw(batch)
    }
    open fun frameTask(){
        addToSceneGraph(this)
        for(property in properties.List){
            addToSceneGraph(property)
        }
    }
    val onLocationEnterActions: MutableList<()-> Unit> = mutableListOf()
    val onLocationExitActions: MutableList<(newLocation: DefaultLocation)->Unit> = mutableListOf()
    val onRemoveAction: MutableList<()->Unit> = mutableListOf({})
    open val collitionMask: CollitionMask = DefaultCollitionMask()
    constructor(Position: Vector2, size: Vector2): this(Position,size,null)

    open fun removeFromLocation(){
        defaultLocation?.removeGameObject(this)
        this.defaultLocation = null
        onRemoveAction.forEach { it() }
    }
    open fun addToLocation(location: DefaultLocation){
        location.addGameObject(this)
        this.defaultLocation = location
    }

    open fun setPosition(position: Vector2) {
        sprite.setPosition(position.x,position.y)
        polygon.setPosition(position.x - polygon.vertices[0],position.y - polygon.vertices[1])
    }

    fun currentPosition(): Vector2 {
        return Vector2(sprite.x,sprite.y)
    }
}