package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Collitions.CanMoveCollition
import com.mygdx.game.InitSprite
import com.mygdx.game.Interfaces.Collition
import com.mygdx.game.Interfaces.ObjectProperty
import com.mygdx.game.Interfaces.Renderable
import com.mygdx.game.LocationImpl
import com.mygdx.game.ResourceList
import com.mygdx.game.RectanglePolygon
import com.mygdx.game.RenderGraph.Companion.addToSceneGraph

abstract class GameObject (val Position: Vector2, val size: Vector2,val location: LocationImpl?): Renderable {
    val topleft = Vector2(Position.x,Position.y + size.y)
    val topright = Vector2(Position.x + size.x,Position.y + size.y)
    val bottomright =  Vector2(Position.x + size.x,Position.y)
    val bottomleft = Position
    val x = Position.x
    val y = Position.y
    val width = size.x
    val height = size.y

    val middle: Vector2 = Vector2(topleft.x + (topright.x - topleft.x) / 2,bottomleft.y + (topleft.y - bottomleft.y)/2)

    //Remember this. Temporary solution. texture must be overriden before polygon is called
    abstract val texture: Texture
    open val sprite: Sprite by lazy { InitSprite(texture)}
    open val polygon: Polygon by lazy { RectanglePolygon(sprite.boundingRectangle) }
    open val collition: Collition = CanMoveCollition
    val properties = ResourceList<ObjectProperty>()
    override fun render(batch: PolygonSpriteBatch){
        sprite.draw(batch)
    }
    open fun frameTask(){
        addToSceneGraph(this)
        for(property in properties.List){
            property.frameTask()
        }
    }
    open var onLocationEnter: ()-> Unit = {}
    open var onLocationExit: ()->Unit = {}
    constructor(Position: Vector2, size: Vector2): this(Position,size,null)
}