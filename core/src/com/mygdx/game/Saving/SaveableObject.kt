package com.mygdx.game.SaveHandling

import kotlinx.serialization.Serializable


@Serializable
abstract class SaveableObject() {
    abstract val entityId: Int
}
@Serializable
open class DefaultSaveableObject(override val entityId: Int): SaveableObject(){}