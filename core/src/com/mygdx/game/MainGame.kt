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
import com.mygdx.game.GameObjects.House

val entrancePoly = RectanglePolygon(Vector2(650f,500f), 100f,100f)
var gateOpened = false
class MainGame : ApplicationAdapter() {
    lateinit internal var batch: PolygonSpriteBatch
    lateinit var firstpoly: RectanglePolygon
    lateinit var secondpoly: RectanglePolygon
    lateinit var thirdpoly: RectanglePolygon
    lateinit var orgPoly: Polygon
    lateinit var shapeRenderer: ShapeRenderer
    lateinit var player: Player
    lateinit var gateSprite: Sprite
    lateinit var testRect: Rectangle
    lateinit var gateSpriteOpen: Sprite
    lateinit var mainPolygonRegion: PolygonRegion
    val camera: OrthographicCamera = OrthographicCamera()
    lateinit var tiledMap: TiledMap;
    lateinit var tiledMapRenderer:TiledMapRenderer;

    override fun create() {

        Gdx.gl.glClearColor(7/255f,82/255f,82/255f,1f)
        val floor = Texture("MainB.jpg")
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT)
        batch = PolygonSpriteBatch()
        tiledMap = TmxMapLoader().load("TileMap.tmx")
        tiledMapRenderer = OrthogonalTiledMapRenderer(tiledMap,batch)
        firstpoly = RectanglePolygon(Vector2(50f,0f),500f,500f)
        firstpoly.vertices = firstpoly.vertices.map { x -> x * 1f }.toFloatArray()
        secondpoly = RectanglePolygon(
                Vector2(700f,500f),
                500f,
                100f
        )
        testRect = Rectangle(0f,0f,200f,200f)
        floor.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear)
        floor.setWrap(Texture.TextureWrap.Repeat,Texture.TextureWrap.MirroredRepeat)
        thirdpoly = RectanglePolygon(Vector2(1000f,800f),100f,100f)
        mainPolygonRegion = PolygonRegion(TextureRegion(floor),firstpoly.vertices, shortArrayOf(0,1,2,0,2,3) )
        addTerrains(floor)
        shapeRenderer = ShapeRenderer()
        player = Player()
        initInputAdapter()
        camera.setToOrtho(
                false,
                Gdx.graphics.width.toFloat(),
                Gdx.graphics.height.toFloat())
        player.sprite.setPosition(Center.x, Center.y)
        font.data.setScale(2f)
    }

    override fun render() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch.projectionMatrix = camera.combined
        batch.begin()
        //batch.draw(mainPolygonRegion,0f,0f)
        TerrainManager.drawTerrain(batch)
        batch.end()
        tiledMapRenderer.render();
        batch.begin()
        player.render(batch)
        batch.end()
        InputHandler.handleInput(player,camera)
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line)
        //TerrainManager.drawTerrain(shapeRenderer, Vector2(camera.position.x, camera.position.y))
        //shapeRenderer.rect(10f + testRect.x,testRect.y,testRect.width,testRect.height)
        val rectangle = player.sprite.boundingRectangle
        shapeRenderer.rect(rectangle.x + (Center.x - player.sprite.x), rectangle.y + (Center.y - player.sprite.y),rectangle.width,rectangle.height)
        shapeRenderer.end()
        camera.position.set(player.sprite.x,player.sprite.y,0f)
        camera.update()
        tiledMapRenderer.setView(camera);
    }

    override fun dispose() {
        batch.dispose()
    }
    fun initInputAdapter(){
        Gdx.input.inputProcessor = ROJInputAdapter(camera,player)
    }
}
