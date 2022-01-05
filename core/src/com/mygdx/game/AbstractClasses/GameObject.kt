package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.Collitions.CanMoveCollition
import com.mygdx.game.Interfaces.Collition
import com.mygdx.game.Interfaces.ObjectProperty
import com.mygdx.game.Interfaces.Renderable
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.SignalManager
import com.mygdx.game.Utils.RenderGraph.Companion.addToSceneGraph
import com.mygdx.game.SaveHandling.FileHandler
import com.mygdx.game.SaveState.SaveStateEntity
import com.mygdx.game.Signal.SIGNALTYPE
import com.mygdx.game.Signal.Signal
import com.mygdx.game.Utils.ResourceList

abstract class GameObject (val Position: Vector2, val size: Vector2,var defaultLocation: DefaultLocation?): Renderable {
    val topleft = Vector2(Position.x,Position.y + size.y)
    val topright = Vector2(Position.x + size.x,Position.y + size.y)
    val bottomright =  Vector2(Position.x + size.x,Position.y)
    val bottomleft = Position
    val x = Position.x
    val y = Position.y
    val width = size.x
    val height = size.y

    val originalMiddle: Vector2 = Vector2(topleft.x + (topright.x - topleft.x) / 2,bottomleft.y + (topleft.y - bottomleft.y)/2)
    val currentMiddle: Vector2
    get() = Vector2(sprite.x + sprite.width/2, sprite.y + sprite.height/2)

    //Remember this. Temporary solution. texture must be overriden before polygon is called
    abstract val texture: Texture
    open val sprite: Sprite by lazy { InitSprite(texture)}
    open val polygon: Polygon by lazy {InitPolygon(sprite)}
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
    open val onLocationEnterActions: MutableList<()-> Unit> = mutableListOf({})
    val onLocationExitActions: MutableList<(newLocation: DefaultLocation)->Unit> = mutableListOf({})
    constructor(Position: Vector2, size: Vector2): this(Position,size,null)

    open fun removeFromLocation(){
        defaultLocation?.removeGameObject(this)
    }
}