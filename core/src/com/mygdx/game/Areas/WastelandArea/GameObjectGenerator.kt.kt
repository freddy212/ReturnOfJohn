import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.DoorCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.ItemType
import com.mygdx.game.GameObjects.Axe
import com.mygdx.game.GameObjects.Door
import com.mygdx.game.GameObjects.ItemObjects.GenericInventoryItemObject
import com.mygdx.game.GameObjects.MoveableEntities.Characters.RockMonster
import com.mygdx.game.GameObjects.Terrain.WalkableTerrain
import com.mygdx.game.GameObjects.Tree
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.doorMainAreaAndWasteland
import com.mygdx.game.playerSize
import com.mygdx.game.plus

fun getWastelandLocationOneObjects(): List<GameObject>{
    val location1 = LocationManager.findLocation("location1", AreaIdentifier.WASTELAND)

    val doorPosition = Vector2(location1.originalMiddle.x - (playerSize.x),location1.topleft.y)

    val doorCollition = DoorCollition(doorPosition,
        AreaIdentifier.MAINAREA, doorMainAreaAndWasteland,
        Direction.UP)

    val door = Door(doorPosition, Vector2(32f * 2, 32f * 2), DefaultTextureHandler.getTexture("EmptyDoor.png"),location1,
        Direction.UP,doorCollition)

    val axe = Axe(location1.originalMiddle,Vector2(32f,64f), location1)
    val rockMonster = RockMonster(doorPosition + Vector2(-300f,-200f), Vector2(70f,70f),location1)
    return listOf(door, axe,rockMonster)
}

fun getWastelandLocationThreeObjects(): List<GameObject>{
    val location3 = LocationManager.findLocation("location3", AreaIdentifier.WASTELAND)
    val tree = Tree(location3.topleft + Vector2(300f,0f), Vector2(64f * 2, 128f * 2),location3)
    val WorldLeaf = GenericInventoryItemObject(tree.topleft + Vector2(0f, -300f), Vector2(64f, 32f), location3, ItemType.WORLDLEAF,
        DefaultTextureHandler.getTexture("WorldLeaf.png"))
    return listOf(tree,WorldLeaf)
}

fun getWastelandLocationFiveObjects(): List<GameObject>{
    val location4 = LocationManager.findLocation("location4",AreaIdentifier.WASTELAND)
    val location5 = LocationManager.findLocation("location5",AreaIdentifier.WASTELAND)

    val walkableTerrain = WalkableTerrain(Vector2(location5.x,location4.y), Vector2(location5.width,location4.height),location5)
    return listOf(walkableTerrain)
}