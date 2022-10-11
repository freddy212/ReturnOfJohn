package com.mygdx.game.Saving;

interface SaveStateEntity{
    val entityId: Int
}

open class DefaultSaveStateHandler: SaveStateEntity {
    override val entityId = SaveCounterHandler.getSaveCounter()
}
