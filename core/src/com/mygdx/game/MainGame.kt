package com.mygdx.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g3d.Environment
import com.badlogic.gdx.graphics.g3d.ModelBatch
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Enums.ItemType
import com.mygdx.game.GameObjects.ItemAbilities.AxeAbility
import com.mygdx.game.GameObjects.ItemAbilities.FireballAbility
import com.mygdx.game.GameObjects.ItemAbilities.IcicleAbility
import com.mygdx.game.GameObjects.ItemAbilities.ShieldAbility
import com.mygdx.game.SaveHandling.FileHandler
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Fireball
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Managers.*
import com.mygdx.game.SaveHandling.DefaultSaveableObject
import com.mygdx.game.SaveState.PlayerSaveState
import com.mygdx.game.SaveState.SaveStateEntity
import com.mygdx.game.Managers.UIRendererManager
import com.mygdx.game.Signal.Signal
import com.mygdx.game.Signal.initListeners
import com.mygdx.game.Utils.RectanglePolygon
import com.mygdx.game.Utils.RenderGraph
import kotlinx.serialization.json.*
import kotlinx.serialization.*

val modelBatch by lazy{ModelBatch()}
val environment by lazy{Environment()}
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

        Gdx.gl.glClearColor(0f,0f,0f,0f)
        DefaultAssetHandler.setAssetManager(InitAssets())
        batch = PolygonSpriteBatch()
        player =  Player(Vector2(0f, 0f), Vector2(32f,40f))
        firstpoly = RectanglePolygon(Vector2(50f,0f),500f,500f)
        firstpoly.vertices = firstpoly.vertices.map { x -> x * 1f }.toFloatArray()
        secondpoly = RectanglePolygon(
                Vector2(700f,500f),
                500f,
                100f
        )
        testRect = Rectangle(0f,0f,200f,200f)
        thirdpoly = RectanglePolygon(Vector2(1000f,800f),100f,100f)
        playerSize = Vector2(player.sprite.width,player.sprite.height)
        AreaInitializerManager.init()
        shapeRenderer = ShapeRenderer()
        camera.setToOrtho(
                false,
                Gdx.graphics.width.toFloat(),
                Gdx.graphics.height.toFloat())

        if(!FileHandler.SaveFileEmpty()){
            val savedState:String = FileHandler.readFromFile()[0]
            val savedPlayerSaveState:PlayerSaveState = Json.decodeFromString(savedState)
            playerSaveState = PlayerSaveState(savedPlayerSaveState.playerXPos,savedPlayerSaveState.playerYPos,
                savedPlayerSaveState.areaIdentifier, player.entityId)
        }else{
            playerSaveState = PlayerSaveState(Center.x, Center.y,AreaIdentifier.MAINAREA, player.entityId)
        }
        AreaManager.activeArea = AreaManager.getArea(playerSaveState.areaIdentifier)
        ResetPlayer(playerSaveState)
        font.data.setScale(2f)
        inventory = Inventory()
        inputAdapter = ROJInputAdapter(camera,player)
        initInputAdapter()
        initListeners()
        LocationManager.frameAction()

        val originalFile = FileHandler.readFromFile()
        val saves = originalFile.subList(1,originalFile.size)
        val savedSignals:List<Signal> = saves.map { x -> Json.decodeFromString(x)}
        savedSignals.forEach { SignalManager.emitSignal(it,false) }
        player.addAbility(FireballAbility())
        player.addAbility(ShieldAbility())
            /* val savedStates:List<DefaultSaveableObject> = saves.map { x -> Json.decodeFromString(x) }
             val savedEntities:List<SaveStateEntity> = AreaManager.getAllGameObjects()
                 .filter {it is SaveStateEntity}.map { it as SaveStateEntity }.filter {savedStates.map {it.entityId}.contains(it.entityId)}
             println("size of saved elements is : + " + savedStates.size + " and size of matching elements is : " + savedEntities.size)
             savedEntities.forEach { it.onLoadAction() }
             player.addAbility(IcicleAbility(Vector2(0f,0f),Vector2(0f,0f)))
             */
    }



    override fun render() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT)
        batch.projectionMatrix = camera.combined
        LocationManager.frameAction()
        RenderGraph.render(batch)
        inputAdapter.handleInput(player)
        drawrects()
        EventManager.executeEvents()
        UIRendererManager.render()
        SignalManager.useSignals()
        camera.position.set(player.sprite.x, player.sprite.y,0f)
        camera.update()
    }

    fun drawrects(){
        val gameObjects = LocationManager.ActiveGameObjects
        gameObjects.forEach{x -> drawPolygonShape(x.polygon,shapeRenderer)}
    }

    override fun dispose() {
        batch.dispose()
    }
    fun initInputAdapter(){
        Gdx.input.inputProcessor = inputAdapter
    }
}
