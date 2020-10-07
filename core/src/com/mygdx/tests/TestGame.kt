package com.mygdx.tests

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.backends.headless.HeadlessApplication

import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration
import com.badlogic.gdx.graphics.GL20
import com.mygdx.game.Player
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito


open class TestGame :ApplicationAdapter() {
    lateinit var player: Player
    init {
        val config = HeadlessApplicationConfiguration()
        config.renderInterval = 1f/60f
        HeadlessApplication(this,config)
        Gdx.gl = Mockito.mock(GL20::class.java)
    }
    override fun create() {

    }
    @BeforeEach
    fun init(){
        player = Player()
    }
}