package com.mygdx.game.UI.Dialogue

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g3d.Model
import com.badlogic.gdx.graphics.g3d.ModelInstance
import com.badlogic.gdx.math.Quaternion
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.MoveableObject
import com.mygdx.game.Enums.Direction
import com.mygdx.game.GameObjects.MoveableEntities.Player
import com.mygdx.game.Interfaces.Character
import com.mygdx.game.Interfaces.DirectionalObject

abstract class DefaultCharacter(Position: Vector2, size: Vector2, location: LocationImpl?) : Character,MoveableObject(Position, size, location) {
    override val font = BitmapFont()
    val model = assets.get("ManBlender.g3db", Model::class.java)
    lateinit var modelInstance: ModelInstance

    init {
        font.data.setScale(2f)
        font.color = Color.WHITE
        modelInstance = ModelInstance(model)
        modelInstance.transform.setTranslation(Position.x + size.x / 2,Position.y + size.y / 2,-150f)
        modelInstance.nodes.get(0).rotation.setEulerAngles(0f,-20f,0f)
        modelInstance.nodes.get(0).scale.set(20f,20f,20f);
        modelInstance.calculateTransforms()
        //instances.add(modelInstance)
    }

    override fun setPosition(nextPosition: Vector2, gameObject: GameObject) {
        val rotation = when(direction){
            Direction.UP -> 180f
            Direction.RIGHT -> 90f
            Direction.DOWN -> 0f
            Direction.LEFT -> 270f
        }
        modelInstance.transform.setTranslation(nextPosition.x + sprite.width / 2, nextPosition.y + sprite.height / 2,-150f)
       // modelInstance.transform.set(Quaternion().setEulerAngles(0f,0f,rotation))
        super.setPosition(nextPosition, gameObject)
    }

    override fun setRotation(direction: Direction, directionalObject: DirectionalObject) {
        super.setRotation(direction, directionalObject)
        val rotation = when(direction){
            Direction.UP -> 180f
            Direction.RIGHT -> 90f
            Direction.DOWN -> 0f
            Direction.LEFT -> 270f
        }
        modelInstance.transform.set(Quaternion().setEulerAngles(0f,0f,rotation))
        modelInstance.transform.setTranslation(sprite.x + sprite.width / 2, sprite.y + sprite.height / 2,-150f)
    }

    override fun render(batch: PolygonSpriteBatch) {
            batch.end()
            modelBatch.begin(camera)
            modelBatch.render(modelInstance,environment)
            modelBatch.end()
            batch.begin()
            camera.update()
    }
}