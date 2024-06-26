package com.mygdx.game.GameObjects.SensorObjects

import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.AbilityId
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.ItemType
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.SandGhost.SandGhost
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Interfaces.KeyPressedCollition
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Saving.DefaultSaveStateHandler
import com.mygdx.game.Saving.SaveStateEntity
import com.mygdx.game.Signal.Signals.AddAbilityItemSignal
import com.mygdx.game.player

class SandGhostSleeping(initPosition: Vector2, size: Vector2, defaultLocation: DefaultLocation?) :
    GameObject(initPosition, size, defaultLocation), SaveStateEntity by DefaultSaveStateHandler(){
    override val texture = DefaultTextureHandler.getTexture("BossFace.png")
    override val layer = Layer.PERSON
    var visibilityValue = texture.height / 4
    var activated = false
    val sandHandTexture = DefaultTextureHandler.getTexture("Hand.png")
    val addAbilityItemSignal = AddAbilityItemSignal(abilityId = AbilityId.SHIELDUPGRADE, area = AreaIdentifier.WASTELAND, x = initPosition.x + size.x / 2, y = initPosition.y + size.y / 2, locationName = defaultLocation!!.locationName)
    val sandGhost = SandGhost(initPosition,size, defaultLocation, addAbilityItemSignal, this.entityId)

    val handGhostDifference = texture.height - sandHandTexture.height

    init {
        polygon.setPosition(polygon.x, polygon.y - size.y / 2)
        polygon.setScale(3.2f,1f)
    }

    override val collition: KeyPressedCollition = object: KeyPressedCollition() {
        override val specificButton = Input.Keys.SPACE
        override fun renderKeyToUI(entity: GameObject, collidedObject: GameObject) {
            if(player.inventory.inventoryList.containsKey(ItemType.FLUTEOFAWAKENING)){
                super.renderKeyToUI(entity, collidedObject)
            }
        }
        override fun collitionHappened(collidedObject: GameObject) {
            if(player.inventory.inventoryList.containsKey(ItemType.FLUTEOFAWAKENING)){
                activated = true
            }
        }

    }

    override fun render(batch: PolygonSpriteBatch) {
        batch.draw(texture, this.x, this.y, 0,0, texture.width, visibilityValue)
        batch.draw(sandHandTexture, this.x - 200f, this.y - handGhostDifference, texture.width.toFloat(), visibilityValue.toFloat(),0,0,sandHandTexture.width, visibilityValue,true,false)
        batch.draw(sandHandTexture, this.x + 200f, this.y - handGhostDifference, 0,0,sandHandTexture.width, visibilityValue)
        if(visibilityValue <= texture.height && activated){
            visibilityValue += 1
        }

        if(visibilityValue >= texture.height){
            sandGhost.addToLocation(defaultLocation!!)
            this.removeFromLocation()
        }
    }
}