package com.mygdx.game.SaveHandling

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Managers.AreaManager
import com.mygdx.game.SaveState.DefaultSaveStateHandler
import com.mygdx.game.SaveState.SaveStateEntity
import com.mygdx.game.getGameObjectWithEntityId

open class DefaultRemoveObjectSaveState: DefaultSaveStateHandler() {
    override fun onLoadAction() {
        val matchingObject: GameObject? = getGameObjectWithEntityId(entityId)
        matchingObject?.removeFromLocation()
    }
}