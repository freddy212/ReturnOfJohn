package com.mygdx.game.SaveState;

import com.mygdx.game.SaveHandling.SaveCounterHandler

interface SaveStateEntity{
    fun onLoadAction()
    val entityId: Int
}

open class DefaultSaveStateHandler():SaveStateEntity {
    override val entityId = SaveCounterHandler.getSaveCounter()
    override fun onLoadAction() {

    }
}
