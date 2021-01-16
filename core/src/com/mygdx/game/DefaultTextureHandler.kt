package com.mygdx.game

import TextureHandler
import com.badlogic.gdx.graphics.Texture
import com.mygdx.game.Managers.DefaultAssetHandler.assets

object DefaultTextureHandler: TextureHandler{
        override fun getTexture(textureName: String): Texture {
            if(!assets.isLoaded(textureName)) {
                assets.load(textureName, Texture::class.java)
                assets.finishLoading()
            }
            return assets.get(textureName,Texture::class.java)
        }
}