import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.DoorCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Direction
import com.mygdx.game.GameObjects.Axe
import com.mygdx.game.GameObjects.Door
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.doorMainAreaAndWasteland
import com.mygdx.game.playerSize

fun getWastelandLocationOneObjects(): List<GameObject>{
    val location1 = LocationManager.findLocation("location1", AreaIdentifier.WASTELAND)

    val doorPosition = Vector2(location1.originalMiddle.x - (playerSize.x),location1.topleft.y)

    val doorCollition = DoorCollition(doorPosition,
        AreaIdentifier.MAINAREA, doorMainAreaAndWasteland,
        Direction.UP)

    val door = Door(doorPosition, Vector2(32f * 2, 64f * 2), DefaultTextureHandler.getTexture("CaveDoor.png"),location1,
        Direction.UP,doorCollition)

    val axe = Axe(location1.originalMiddle,Vector2(32f,64f), location1)

    return listOf(door, axe)
}