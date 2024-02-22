package com.mygdx.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.glutils.FrameBuffer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Managers.*
import com.mygdx.game.Saving.FileHandler
import com.mygdx.game.Saving.FileHandler.Companion.dirPath
import com.mygdx.game.Saving.FileHandler.Companion.savePath
import com.mygdx.game.Saving.PlayerSaveState
import com.mygdx.game.Signal.Signal
import com.mygdx.game.Signal.inistSignalListeners
import com.mygdx.game.Signal.signalConvert
import com.mygdx.game.Utils.RectanglePolygon
import com.mygdx.game.Utils.RenderGraph
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File

val camera: OrthographicCamera = OrthographicCamera()
lateinit var player: Player
lateinit var playerSize: Vector2
lateinit var playerSaveState: PlayerSaveState


class MainGame : ApplicationAdapter() {
    lateinit internal var batch: PolygonSpriteBatch
    lateinit var firstpoly: RectanglePolygon
    lateinit var secondpoly: RectanglePolygon
    lateinit var thirdpoly: RectanglePolygon
    lateinit var shapeRenderer: ShapeRenderer
    lateinit var testRect: Rectangle
    lateinit var inventory: Inventory
    lateinit var inputAdapter: ROJInputAdapter

    override fun create() {

        camera.zoom = 0.6f
        println("path: " + dirPath + File.separator + savePath)
        val file = File(dirPath + File.separator + savePath)
        if (!file.exists()) {
            file.parentFile.mkdirs()
            file.createNewFile()
        }

        Gdx.gl.glClearColor(0f, 0f, 0f, 0f)
        DefaultAssetHandler.setAssetManager(InitAssets())
        batch = PolygonSpriteBatch()
        player = Player(Vector2(0f, 0f), Vector2(48f, 56f))
        playerSize = Vector2(player.sprite.width, player.sprite.height)
        AreaInitializerManager.init()
        FontManager.initFonts()
        shapeRenderer = ShapeRenderer()
        camera.setToOrtho(
            false,
            Gdx.graphics.width.toFloat(),
            Gdx.graphics.height.toFloat()
        )

        if (!FileHandler.SaveFileEmpty()) {
            val savedState: String = FileHandler.readFromFile()[0]
            val savedPlayerSaveState: PlayerSaveState = Json.decodeFromString(savedState)
            playerSaveState = PlayerSaveState(
                savedPlayerSaveState.playerXPos, savedPlayerSaveState.playerYPos,
                savedPlayerSaveState.areaIdentifier, player.entityId
            )
        } else {
            playerSaveState = PlayerSaveState(Center.x / 2, Center.y / 2, AreaIdentifier.MAINAREA, player.entityId)
            FileHandler.writeToFile(playerSaveState.entityId,playerSaveState.encode())
        }
        ResetPlayer(playerSaveState)
        font.data.setScale(2f)
        inventory = Inventory()
        inputAdapter = ROJInputAdapter(camera, player)
        initInputAdapter()
        inistSignalListeners()
        val originalFile = FileHandler.readFromFile()
        val saves = originalFile.subList(1, originalFile.size)
        val savedSignals: List<Signal> = saves.map(::signalConvert)
        savedSignals.forEach { SignalManager.emitSignal(it, false); SignalManager.pastSignals.add(it) }
        AreaManager.SetArea(AreaManager.getArea(playerSaveState.areaIdentifier))

        LocationManager.frameAction()
        player.move(Vector2(0f,0f))
        // DefaultSoundHandler.mainAreaMusic.isLooping = true
        //  DefaultSoundHandler.mainAreaMusic.play()
    }


    override fun render() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT)
        batch.projectionMatrix = camera.combined
        LocationManager.frameAction()
        AnimationManager.addAnimationsToRender()
        RenderGraph.render(batch)
        inputAdapter.handleInput(player)
        // drawrects()
        EventManager.executeEvents()
        UIRendererManager.render()
        SignalManager.useSignals()
        camera.position.set(player.sprite.x, player.sprite.y, 0f)
        camera.update()
    }

    fun drawrects() {
        val gameObjects = LocationManager.ActiveGameObjects
        gameObjects.forEach { x -> drawPolygonShape(x.polygon, shapeRenderer) }
    }

    override fun dispose() {
        batch.dispose()
    }

    fun initInputAdapter() {
        Gdx.input.inputProcessor = inputAdapter
    }
}
