package com.mygdx.tests

import TextureHandler
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.backends.headless.HeadlessApplication

import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g3d.Model
import com.badlogic.gdx.graphics.g3d.ModelInstance
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.GameObjects.MoveableEntities.Player
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Interfaces.AssetHandler
import com.mygdx.game.Interfaces.ModelInstanceHandler
import com.mygdx.game.Managers.DefaultAssetHandler
import com.mygdx.game.Managers.LocationManager
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.mockito.Matchers.any
import org.mockito.Matchers.anyString
import org.mockito.Mockito

inline fun <reified T: Any> mock() = Mockito.mock(T::class.java)

open class TestGame :ApplicationAdapter() {
    lateinit var location: LocationImpl
    lateinit var modelInstanceHandlerMock: ModelInstanceHandler
            init {
        val config = HeadlessApplicationConfiguration()
        config.renderInterval = 1f/60f
        HeadlessApplication(this,config)
        Gdx.gl = Mockito.mock(GL20::class.java)
        init()
    }
    override fun create() {

    }
    @BeforeEach
    fun init(){
        modelInstanceHandlerMock = mock<ModelInstanceHandler>()
        val assetsMock = mock<AssetManager>()
        val textureMock = mock<Texture>()
        Mockito.`when`(assetsMock.get<Texture>(any(), any())).thenReturn(textureMock)
        DefaultAssetHandler.setAssetManager(assetsMock)

        player = Player(Vector2(0f,0f),Vector2(32f,64f),modelInstanceHandlerMock)
        player.setPosition(Vector2(300f,300f),player)
        val area = DefaultArea(AreaIdentifier.NOTIMPLEMENTED)
        location = LocationImpl(Vector2(1024f,1024f), Vector2(0f,0f))
        area.addLocation(location)
        LocationManager.SetArea(area)
        LocationManager.LocationFrameTasks()
    }
    @AfterEach
    fun cleanUp(){
        LocationManager.ActiveGameObjects = listOf()
        crossLocationGameObjects.clear()
    }
}