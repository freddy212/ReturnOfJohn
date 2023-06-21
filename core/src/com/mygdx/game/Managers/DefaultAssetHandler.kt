package com.mygdx.game.Managers

import com.badlogic.gdx.assets.AssetManager
import com.mygdx.game.Interfaces.AssetHandler

object DefaultAssetHandler: AssetHandler {
    override lateinit var assets: AssetManager
    fun setAssetManager(assets:AssetManager){
        this.assets = assets
    }
}