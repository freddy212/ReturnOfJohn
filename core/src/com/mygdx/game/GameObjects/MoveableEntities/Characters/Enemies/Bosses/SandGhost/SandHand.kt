package com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.SandGhost

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.*
import com.mygdx.game.Collitions.PlayerHitCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.EdgeOfLocationStrategies.NoAction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.SaveHandling.DefaultRemoveObjectSaveState
import com.mygdx.game.SaveState.SaveStateEntity

class SandHand(Position: Vector2, size: Vector2, location: DefaultLocation?, flip: Boolean)
    : MoveableObject(Position, size, location),
    SaveStateEntity by DefaultRemoveObjectSaveState() {
    override val collition = SandHandCollition()
    override val texture = DefaultTextureHandler.getTexture("Hand.png")
    override val layer = Layer.PERSON
    override var currentSpeed = 0f
    override val movementStrategy = DefaultMovement(NoAction())
    override var unitVectorDirection = Vector2(0f,0f)

    init {
        if (flip){
            this.sprite.flip(true,false)
            polygon.translate(polygon.x - 10f,polygon.y - 10f)
        } else {
            polygon.translate(polygon.x + 10f,polygon.y - 10f)
        }
        polygon.setScale(0.8f,0.8f)
    }
}

class SandHandCollition(): MoveCollition by PlayerHitCollition() {


}