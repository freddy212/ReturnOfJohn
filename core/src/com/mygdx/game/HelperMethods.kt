package com.mygdx.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g3d.Model
import com.badlogic.gdx.math.Intersector.intersectSegments
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.FloatArray
import com.mygdx.game.AbstractClasses.*
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.ItemType
import com.mygdx.game.GameObjects.*
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.Interfaces.*
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Locations.DefaultLocationData
import com.mygdx.game.Managers.AreaManager
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Managers.SignalManager
import com.mygdx.game.SaveState.PlayerSaveState
import com.mygdx.game.SaveState.SaveStateEntity
import com.mygdx.game.Signal.SIGNALTYPE
import com.mygdx.game.Signal.Signal
import com.mygdx.game.Utils.RectanglePolygon
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.pow
import kotlin.math.sqrt


var font: BitmapFont = BitmapFont()

fun distance(point1: Vector2, point2: Vector2): Float{
     val first = (point2.x - point1.x).pow(2) + (point2.y - point1.y).pow(2)
     return sqrt(first)
}
fun getPolygonPoints(polygon: Polygon): List<Vector2>{
        val floatArray = polygon.transformedVertices
        val xValues = floatArray.filterIndexed{ index, _ -> index.toFloat() % 2f == 0f}
        val yValues = floatArray.filterIndexed{ index, _ -> index % 2f == 1f}
        val listOfVectors = xValues.zip(yValues).map{ (xvalue,yvalue) -> Vector2(xvalue,yvalue) }
        return listOfVectors
}

fun intersectPolygonEdges(polygon1: FloatArray, polygon2: FloatArray): Boolean {
        val last1 = polygon1.size - 2
        val last2 = polygon2.size - 2
        val p1 = polygon1.items
        val p2 = polygon2.items
        var x1 = p1[last1]
        var y1 = p1[last1 + 1]
        var i = 0
        while (i <= last1) {
                val x2 = p1[i]
                val y2 = p1[i + 1]
                var x3 = p2[last2]
                var y3 = p2[last2 + 1]
                var j = 0
                while (j <= last2) {
                        val x4 = p2[j]
                        val y4 = p2[j + 1]
                        if (intersectSegments(x1, y1, x2, y2, x3, y3, x4, y4, null)) return true
                        x3 = x4
                        y3 = y4
                        j += 2
                }
                x1 = x2
                y1 = y2
                i += 2
        }
        return false
}

fun getUnitVectorTowardsPoint(position: Vector2, point: Vector2): Vector2{
        return point.sub(position).nor()
}
fun getOppositeUnitVector(position: Vector2, point: Vector2): Vector2{
        val unitvector = getUnitVectorTowardsPoint(position,point)
        return Vector2(-unitvector.x, -unitvector.y)
}

fun unitVectorToAngle(unitVector: Vector2):Float{
        return (atan2(unitVector.y,unitVector.x)*180/PI).toFloat()
}


enum class InsertDirection{LEFT,UP,RIGHT,DOWN,MIDDLE}
fun GetPositionRelativeToLocation(defaultLocation: DefaultLocation, size: Vector2, direction: InsertDirection, directionOnPlane:InsertDirection, modifier: Vector2 = Vector2(0f,0f)): Vector2{

        val DirectionOnPlane = when(direction){
                InsertDirection.LEFT, InsertDirection.RIGHT ->
                        when(directionOnPlane) {
                               InsertDirection.UP -> (defaultLocation.topleft.y - defaultLocation.bottomleft.y) - size.y
                               InsertDirection.DOWN -> 0f
                               else ->  ((defaultLocation.topleft.y - defaultLocation.bottomleft.y) / 2f - size.y / 2)
                        }
                InsertDirection.UP, InsertDirection.DOWN ->
                        when(directionOnPlane){
                                InsertDirection.RIGHT -> (defaultLocation.topright.x - defaultLocation.topleft.x) - size.x
                                InsertDirection.LEFT -> 0f
                                else -> (defaultLocation.topright.x - defaultLocation.topleft.x) / 2f - size.x / 2
                        }
                else -> 0f
        }

        return when(direction){
                InsertDirection.LEFT -> Vector2(defaultLocation.bottomleft.x - size.x,
                defaultLocation.bottomleft.y + DirectionOnPlane)
                InsertDirection.UP -> Vector2(defaultLocation.topleft.x + DirectionOnPlane,
                        defaultLocation.topleft.y)
                InsertDirection.RIGHT -> Vector2(defaultLocation.bottomright.x,
                        defaultLocation.bottomleft.y + DirectionOnPlane)
                InsertDirection.DOWN -> Vector2(defaultLocation.topleft.x + DirectionOnPlane,
                        defaultLocation.bottomleft.y - size.y)
                else -> Vector2(0f,0f)
        } + modifier
}

fun addLocation(defaultLocation: DefaultLocation, area: Area): DefaultLocation {
        area.addLocation(defaultLocation)
        return defaultLocation
}
fun addLocationRelative(defaultLocation: DefaultLocation, size:Vector2, direction:InsertDirection, area: Area,
                        directionOnPlane:InsertDirection, objectCreationMethod: () -> List<GameObject> = {listOf()}, locationDataStrategy:LocationDataStrategy = DefaultLocationData(),
                        positionModifier: Vector2 = Vector2(0f,0f)): DefaultLocation {
        val pos1 = GetPositionRelativeToLocation(defaultLocation,size,direction,directionOnPlane,positionModifier)
        val newLocation = DefaultLocation(size,pos1, objectCreationMethod,locationDataStrategy)
        defaultLocation.addAdjacentLocation(newLocation)
        newLocation.addAdjacentLocation(defaultLocation)
        return addLocation(newLocation,area)
}

fun addLocationsToArea(area: Area){
        area.defaultLocations.forEach{ x -> x.initLocation()}
}

fun handleCollitions(gameObject: GameObject,polygonToCheck: Polygon, objectsToCheck: List<GameObject>):Boolean {
        val collidingObjects = GetCollidingObjects(polygonToCheck ,objectsToCheck - gameObject)
        val collitions = collidingObjects.map { x -> x.collition }
        collidingObjects.forEach { x -> x.collition.collitionHappened(gameObject, x);
                                        gameObject.collition.collitionHappened(gameObject,x)}
        return collitions.filterIsInstance<MoveCollition>().all { x -> x.canMoveAfterCollition }
}

fun GameObject.rotate(rotationDegree: Float) {
        this.sprite.rotate(rotationDegree)
        this.polygon.rotate(rotationDegree)
}

fun GameObject.InitSprite(texture: Texture): Sprite{
        val sprite = Sprite(texture)
        sprite.setSize(size.x,size.y)
        sprite.setOriginCenter()
        sprite.setPosition(Position.x,Position.y)
        return sprite
}
fun GameObject.InitPolygon(sprite: Sprite): Polygon{
        val polygon = RectanglePolygon(sprite.boundingRectangle)
        polygon.setOrigin(sprite.x + sprite.originX, sprite.y + sprite.originY)
        polygon.setPosition(sprite.x - polygon.vertices[0],sprite.y - polygon.vertices[1])
        return polygon
}

inline fun ConstructObjects(gameobjectFactory: (Position: Vector2, Size: Vector2, defaultLocation: DefaultLocation) -> GameObject, fromx: Int, incrementx: Int, endx: Int,
                            fromy: Int, incrementy: Int, endy: Int, defaultLocation: DefaultLocation
): List<GameObject>{
        val objects = mutableListOf<GameObject>()
        for(y in fromy downTo endy step incrementy){
                for(x in fromx..endx step incrementx){
                        val gameObject = gameobjectFactory(Vector2(x.toFloat(),y.toFloat()), Vector2(incrementx.toFloat(),incrementy.toFloat()),defaultLocation)
                        objects.add(gameObject)
                }
        }
        return objects.toList()
}

fun constructTombs(defaultLocation: DefaultLocation): List<GameObject>{
        return ConstructObjects(::Tomb,defaultLocation.bottomleft.x.toInt() + 20, 120, defaultLocation.bottomright.x.toInt(),
                defaultLocation.bottomleft.y.toInt() + 200, 128, defaultLocation.bottomleft.y.toInt(),defaultLocation)
}

fun middleOfObject(Position: Vector2,size: Vector2): Vector2{
        return Vector2(Position.x - (size.x / 2), Position.y - (size.y / 2))
}
fun renderRepeatedTexture(batch: PolygonSpriteBatch,texture: Texture,position: Vector2,size: Vector2){
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat)
        batch.draw(texture,position.x,position.y,0,0,size.x.toInt(),size.y.toInt())
}

fun checkOpposingDirections(player: Player, directionalObject: DirectionalObject): Boolean{
        return when(player.direction){
                Direction.UP -> directionalObject.direction == Direction.DOWN
                Direction.LEFT -> directionalObject.direction == Direction.RIGHT
                Direction.RIGHT -> directionalObject.direction == Direction.LEFT
                Direction.DOWN -> directionalObject.direction == Direction.UP
        }
}
fun GetCollidingObjects(polygon: Polygon, gameObjects: List<GameObject>): List<GameObject>{
        val collidingObjects =  gameObjects.filter { p -> isPolygonsColliding(polygon,p.polygon)}
        val filteredCollitions = collidingObjects.fold(collidingObjects, {objects,nextObject -> nextObject.collition.filterCollitions(objects)})
        return filteredCollitions
}
fun isPolygonsColliding(polygon1: Polygon, polygon2: Polygon): Boolean{
       return intersectPolygonEdges(FloatArray(polygon1.transformedVertices), FloatArray(polygon2.transformedVertices))
                || polygon1.anyPointInPolygon(polygon2)
}

fun InitAssets(): AssetManager {
        val assetManager = AssetManager()
        assetManager.load("ManBlender.g3db", Model::class.java)
        assetManager.finishLoading()
        return assetManager
}
fun entityWithinLocations(polygonToCheck: Polygon): Boolean {
        var inLocation1 = false
        for (point in getPolygonPoints(polygonToCheck)) {
                inLocation1 = false
                for (rectangle in LocationManager.activeDefaultLocations.map { x -> x.sprite.boundingRectangle }) {
                        if (rectangle.contains(point)) {
                                inLocation1 = true
                                break
                        }
                }
                if (!inLocation1) {
                        break
                }
        }
        return inLocation1
}
fun getDirectionFromAngle(angleToCheck: Float):Direction{

        return when(angleToCheck){
                in -45f..45f -> Direction.DOWN
                in 45f..135f -> Direction.RIGHT
                in 135f..225f -> Direction.UP
                else -> Direction.LEFT
        }
}

fun getGameObjectWithEntityId(entityId: Int): GameObject? {
        val relevantObjects: List<GameObject> = AreaManager.getAllGameObjects().filter { it is SaveStateEntity }
        val matchingObject: GameObject? = relevantObjects.find { (it as SaveStateEntity).entityId == entityId }
        return matchingObject
}


fun itemObjectAddToInventory(itemType: ItemType, itemObject: GameObject) {
        SignalManager.emitSignal(Signal(SIGNALTYPE.ITEM_PICKED_UP,itemType.ordinal))
        val id = if(itemObject is SaveStateEntity) itemObject.entityId else NOTSAVEDID
        SignalManager.emitSignal(Signal(SIGNALTYPE.REMOVE_OBJECT,id))

}

fun HitOppositeDirection(
        entity: GameObject,
        fightableEntity: FightableEntity
) {
        val character = fightableEntity as DefaultCharacter
        val centerPointBoulder =
                Vector2(entity.sprite.x + entity.sprite.width / 2, entity.sprite.y + entity.sprite.height / 2)
        val centerPointPlayer =
                Vector2(character.sprite.x + character.sprite.width / 2, character.sprite.y + character.sprite.height / 2)
        val oppositeDirection = getOppositeUnitVector(centerPointPlayer, centerPointBoulder)
        character.isHit(oppositeDirection)
}
fun ResetPlayer(playerSaveState: PlayerSaveState){
        player.setPosition(Vector2(playerSaveState.playerXPos, playerSaveState.playerYPos), player)
        player.health = player.maxHealth
}