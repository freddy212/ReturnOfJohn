package com.mygdx.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.*
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector2
import java.util.*

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
    lateinit var gateSpriteOpen: Sprite
    lateinit var mainPolygonRegion: PolygonRegion
    lateinit var house:House
    lateinit var house2:House
    val camera: OrthographicCamera = OrthographicCamera()


    override fun create() {
        Gdx.gl.glClearColor(7/255f,82/255f,82/255f,1f)
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT)
        batch = PolygonSpriteBatch()
        firstpoly = RectanglePolygon(Vector2(200f,100f),500f,500f)
        secondpoly = RectanglePolygon(
                Vector2(700f,500f),
                500f,
                100f
        )
        thirdpoly = RectanglePolygon(Vector2(1000f,800f),100f,100f)
        mainPolygonRegion = PolygonRegion(TextureRegion(Texture("MainB.jpg")),firstpoly.vertices, shortArrayOf(0,1,2,0,2,3) )
        TerrainManager.addTerrain(Polygon(firstpoly.vertices))
        TerrainManager.addTerrain(Polygon(secondpoly.vertices))
        shapeRenderer = ShapeRenderer()
        initInputAdapter()
        player = Player()
        gateSpriteOpen = Sprite(Texture("GateO.png"))
        gateSpriteOpen.setOriginCenter()
        gateSpriteOpen.setSize(gateSpriteOpen.width * 0.5f, gateSpriteOpen.height * 0.5f)
        gateSpriteOpen.setPosition(695f,500f)
        gateSprite = Sprite(Texture("GateC.png"))
        gateSprite.setOriginCenter()
        gateSprite.setSize(gateSprite.width * 0.5f, gateSprite.height * 0.5f)
        gateSprite.setPosition(695f,500f)
        println(gateSprite.x)
        camera.setToOrtho(
                false,
                Gdx.graphics.width.toFloat(),
                Gdx.graphics.height.toFloat())
        player.sprite.setPosition(Center.x, Center.y + 150)
        house = House(200f,200f,300f / 2,400f / 2)
        house2 = House(400f,200f,300f / 2,400f / 2)
    }

    override fun render() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch.projectionMatrix = camera.combined
        batch.begin()
        batch.draw(mainPolygonRegion,0f,0f)
        if(gateOpened){
            gateSpriteOpen.draw(batch)
        }else {
            gateSprite.draw(batch)
        }
        player.render(batch)
        house.render(batch)
        house2.render(batch)
        batch.end()
        InputHandler.handleInput(player)
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line)
        TerrainManager.drawTerrain(shapeRenderer, Vector2(camera.position.x, camera.position.y))
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
        Gdx.input.inputProcessor = ROJInputAdapter(camera)
    }
}
