package com.mygdx.game.GameObjects.ItemObjects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.Interfaces.Collition
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.SignalManager
import com.mygdx.game.Saving.DefaultSaveStateHandler
import com.mygdx.game.Saving.SaveStateEntity
import com.mygdx.game.Signal.Signals.MaxHealthGainedSignal
import com.mygdx.game.Signal.Signals.RemoveObjectSignal

class HealthObject(initPosition: Vector2, size: Vector2, defaultLocation: DefaultLocation?,val maxHealthGained: Int = 25) :
    GameObject(initPosition, size, defaultLocation), SaveStateEntity by DefaultSaveStateHandler() {
    override val texture = DefaultTextureHandler.getTexture("healthGainedObject.png")
    override val layer = Layer.ONGROUND
    override val collition = HealthObjectCollition(this)
}

class HealthObjectCollition(val healthObject: HealthObject): MoveCollition{
    override val canMoveAfterCollition = true

    override fun collitionHappened(collidedObject: GameObject) {
        if(collidedObject is Player){
            SignalManager.emitSignal(RemoveObjectSignal(healthObject.entityId))
            SignalManager.emitSignal(MaxHealthGainedSignal(healthObject.maxHealthGained))
        }

    }

}