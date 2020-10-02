package com.mygdx.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.PolygonRegion
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TiledMapRenderer
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.GameObjects.InventoryObjects.GenericInventoryObject

val camera: OrthographicCamera = OrthographicCamera()
class MainGame : ApplicationAdapter() {
    lateinit internal var batch: PolygonSpriteBatch
    lateinit var firstpoly: RectanglePolygon
    lateinit var secondpoly: RectanglePolygon
    lateinit var thirdpoly: RectanglePolygon
    lateinit var shapeRenderer: ShapeRenderer
    lateinit var player: Player
    lateinit var testRect: Rectangle
    lateinit var inventory: Inventory
    lateinit var inputAdapter: ROJInputAdapter

    override fun create() {

        Gdx.gl.glClearColor(7/255f,82/255f,82/255f,1f)
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT)
        batch = PolygonSpriteBatch()
        firstpoly = RectanglePolygon(Vector2(50f,0f),500f,500f)
        firstpoly.vertices = firstpoly.vertices.map { x -> x * 1f }.toFloatArray()
        secondpoly = RectanglePolygon(
                Vector2(700f,500f),
                500f,
                100f
        )
        testRect = Rectangle(0f,0f,200f,200f)
        thirdpoly = RectanglePolygon(Vector2(1000f,800f),100f,100f)
        addTerrains()
        shapeRenderer = ShapeRenderer()
        player = Player()
        camera.setToOrtho(
                false,
                Gdx.graphics.width.toFloat(),
                Gdx.graphics.height.toFloat())
        player.sprite.setPosition(Center.x, Center.y)
        font.data.setScale(2f)
        inventory = Inventory()
        inventory.addInventoryObject(GenericInventoryObject("WorldLeaf"))
        inputAdapter = ROJInputAdapter(camera,player)
        initInputAdapter()
    }

    override fun render() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch.projectionMatrix = camera.combined
        batch.begin()
        LocationManager.drawTerrain(batch)
        player.render(batch)
        LocationManager.drawObjects(batch)
        batch.end()
        inputAdapter.handleInput(player)
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line)
        //TerrainManager.drawTerrain(shapeRenderer, Vector2(camera.position.x, camera.position.y))
        //shapeRenderer.rect(10f + testRect.x,testRect.y,testRect.width,testRect.height)
        val rectangle = player.sprite.boundingRectangle
        shapeRenderer.rect(rectangle.x + (Center.x - player.sprite.x), rectangle.y + (Center.y - player.sprite.y),rectangle.width,rectangle.height)
        shapeRenderer.end()
        camera.position.set(player.sprite.x,player.sprite.y,0f)
        camera.update()
    }

    override fun dispose() {
        batch.dispose()
    }
    fun initInputAdapter(){
        Gdx.input.inputProcessor = inputAdapter
    }
}
