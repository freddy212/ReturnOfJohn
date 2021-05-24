package com.mygdx.game.GameObjects.MoveableEntities.Characters

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.DefaultCharacter
import com.mygdx.game.AbstractClasses.DefaultEnemyStrategy
import com.mygdx.game.AbstractClasses.Enemy
import com.mygdx.game.DefaultModelInstanceHandler
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.EnemyStrategy
import com.mygdx.game.Interfaces.ModelInstanceHandler
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.SaveHandling.DefaultRemoveObjectSaveState
import com.mygdx.game.SaveState.SaveStateEntity

class SmallDevil(Position: Vector2, size: Vector2 = Vector2(128f,128f), val location: DefaultLocation?,
                 modelHandler: ModelInstanceHandler = DefaultModelInstanceHandler("ManBlender.g3db",Position,size))
    : Enemy(Position, size, location,modelHandler,100f),
    SaveStateEntity by DefaultRemoveObjectSaveState(){
    override val texture = DefaultTextureHandler.getTexture("DefaultPerson.png")
    override val layer = Layer.ONGROUND
    override var currentSpeed = 2f
    override var direction = Direction.UP
}