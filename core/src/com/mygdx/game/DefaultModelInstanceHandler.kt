package com.mygdx.game

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g3d.Model
import com.badlogic.gdx.graphics.g3d.ModelInstance
import com.badlogic.gdx.math.Quaternion
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.mygdx.game.Interfaces.ModelInstanceHandler

class DefaultModelInstanceHandler(model:Model, Position: Vector2, size:Vector2) : ModelInstanceHandler {
    val modelInstance: ModelInstance = ModelInstance(model)
    init {
        modelInstance.transform.setTranslation(Position.x + size.x / 2,Position.y + size.y / 2,-150f)
        modelInstance.nodes.get(0).rotation.setEulerAngles(0f,-20f,0f)
        modelInstance.nodes.get(0).scale.set(20f,20f,20f);
        modelInstance.calculateTransforms()
    }

    override fun setRotation(angle: Float, position: Vector3) {
        modelInstance.transform.set(Quaternion().setEulerAngles(0f,0f,angle))
        //modelInstance.transform.setTranslation(sprite.x + sprite.width / 2, sprite.y + sprite.height / 2,-150f)
        modelInstance.transform.setTranslation(position)
    }

    override fun setPosition(position: Vector3) {
        modelInstance.transform.setTranslation(position)
      //  modelInstance.transform.setTranslation(nextPosition.x + sprite.width / 2, nextPosition.y + sprite.height / 2,-150f)
    }

    override fun render(batch: PolygonSpriteBatch) {
        batch.end()
        modelBatch.begin(camera)
        modelBatch.render(modelInstance,environment)
        modelBatch.end()
        batch.begin()
    }
}