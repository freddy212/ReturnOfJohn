package com.mygdx.game.SaveHandling

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Managers.AreaManager
import com.mygdx.game.SaveState.DefaultSaveStateHandler
import com.mygdx.game.SaveState.SaveStateEntity

class DefaultRemoveObjectSaveState(): DefaultSaveStateHandler() {
    override fun onLoadAction() {
            val relevantObjects: List<GameObject> = AreaManager.getAllGameObjects().filter { it is SaveStateEntity }
            val matchingObject:GameObject? = relevantObjects.find {(it as SaveStateEntity).entityId == entityId }
            matchingObject?.removeFromLocation()
    }
}