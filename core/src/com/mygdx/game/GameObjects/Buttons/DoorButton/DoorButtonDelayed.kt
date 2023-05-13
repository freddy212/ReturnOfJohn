package com.mygdx.game.GameObjects.Buttons.DoorButton

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Events.ButtonEvent
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Icicle
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Timer.DefaultTimer
import com.mygdx.game.Trimer.DelayTimer

enum class DOORBUTTONDELAYEDSTATE { NOTACTIVATED, PENDING, ACTIVATED }

class DoorButtonDelayed(
    initPosition: Vector2, size: Vector2, defaultLocation: DefaultLocation?,
    doorButtonEvent: ButtonEvent
) : DoorButton(
    initPosition, size,
    defaultLocation, doorButtonEvent
) {

    var state: DOORBUTTONDELAYEDSTATE = DOORBUTTONDELAYEDSTATE.NOTACTIVATED
    val otherButtons: MutableList<DoorButtonDelayed> = mutableListOf()
    override val collition = DoorButtonDelayedCollition(this, otherButtons)

    val timer = DefaultTimer(0.6f)

    override fun render(batch: PolygonSpriteBatch) {
        sprite.color = when (state) {
            DOORBUTTONDELAYEDSTATE.NOTACTIVATED -> Color.RED
            DOORBUTTONDELAYEDSTATE.PENDING -> Color.BLUE
            DOORBUTTONDELAYEDSTATE.ACTIVATED -> Color.GREEN
        }
        sprite.draw(batch)
    }

    override fun frameTask() {
        if(!this.activated && timer.cooldownAvailable()){
            state = DOORBUTTONDELAYEDSTATE.NOTACTIVATED
        }
        super.frameTask()
    }
}

class DoorButtonDelayedCollition(
    doorButton: DoorButtonDelayed,
    val otherButtons: MutableList<DoorButtonDelayed>
) : DoorButtonCollition(doorButton) {
    override fun collitionHappened(collidedObject: GameObject) {
        val doorButtonDelayed = doorButton as DoorButtonDelayed
        if (!doorButtonDelayed.activated) {
            if (collidedObject is Icicle && doorButtonDelayed.state == DOORBUTTONDELAYEDSTATE.NOTACTIVATED) {
                doorButtonDelayed.state = DOORBUTTONDELAYEDSTATE.PENDING
                doorButtonDelayed.timer.tryUseCooldown()
            }
            if (doorButtonDelayed.state == DOORBUTTONDELAYEDSTATE.PENDING && otherButtons.all { it.state == DOORBUTTONDELAYEDSTATE.PENDING }) {
                otherButtons.forEach {
                    it.acceptButton()
                    it.state = DOORBUTTONDELAYEDSTATE.ACTIVATED
                }
                doorButtonDelayed.acceptButton()
                doorButtonDelayed.state = DOORBUTTONDELAYEDSTATE.ACTIVATED
            }

        }
    }
}