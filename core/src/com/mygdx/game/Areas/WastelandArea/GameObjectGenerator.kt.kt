import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.DoorCollition
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.ItemType
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.*
import com.mygdx.game.GameObjects.ItemObjects.GenericInventoryItemObject
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.SandGhost.SandGhost
import com.mygdx.game.GameObjects.MoveableEntities.Characters.NPC
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Mobs.RockMonster
import com.mygdx.game.GameObjects.Terrain.WalkableTerrain
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Managers.SignalManager
import com.mygdx.game.Signal.SIGNALTYPE
import com.mygdx.game.Signal.Signal
import com.mygdx.game.Signal.SignalListeners.ADDMETHODS
import com.mygdx.game.UI.Dialogue.Conversations.*

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

    val npc = NPC(location1.bottomleft + Vector2(200f,200f),Vector2(128f,128f),location1)
    npc.conversationsHandler.addConversation("engineer", engineerFirst(npc))
    npc.conversationsHandler.addConversation("engineer2", engineerSecond(npc))
    npc.conversationsHandler.setConversation("engineer")
    return listOf(door, axe,rockMonster,npc)
}

fun getWastelandLocationThreeObjects(): List<GameObject>{
    val location3 = LocationManager.findLocation("location3", AreaIdentifier.WASTELAND)
    val tree = Tree(location3.topleft + Vector2(300f,0f), Vector2(64f * 2, 128f * 2),location3)
    val Wood = GenericInventoryItemObject(tree.topleft + Vector2(0f, -300f), Vector2(64f, 32f), location3, ItemType.WOOD)
    return listOf(tree,Wood)
}

fun getWastelandLocationFiveObjects(): List<GameObject>{
    val location4 = LocationManager.findLocation("location4",AreaIdentifier.WASTELAND)
    val location5 = LocationManager.findLocation("location5",AreaIdentifier.WASTELAND)

    val walkableTerrain = WalkableTerrain(Vector2(location5.x,location4.y), Vector2(location5.width,location4.height),location5)
    return listOf(walkableTerrain)
}
fun getWastelandLocationSevenObjects(): List<GameObject>{
    val location6 = LocationManager.findLocation("location6",AreaIdentifier.WASTELAND)
    val location7 = LocationManager.findLocation("location7", AreaIdentifier.WASTELAND)
    val walkableTerrain = WalkableTerrain(Vector2(location6.bottomleft) - Vector2(0f,400f), Vector2(300f,400f),location7)
    val walkableTerrain2 = WalkableTerrain(walkableTerrain.bottomleft - Vector2(350f,800f), Vector2(1000f,800f),location7)
    val sandGhost = SandGhost(walkableTerrain2.currentMiddle - Vector2(75f, 0f),Vector2(150f,150f), location7)
    return listOf(walkableTerrain,walkableTerrain2, sandGhost)
}
fun getWastelandLocationTenObjects(): List<GameObject>{
    val location9 = LocationManager.findLocation("location9",AreaIdentifier.WASTELAND)
    val location10 = LocationManager.findLocation("location10", AreaIdentifier.WASTELAND)
    val walkableTerrain = WalkableTerrain(Vector2(location9.topleft), Vector2(200f,location10.height),location10)
    val walkableTerrain2 = WalkableTerrain(Vector2(location10.topleft) - Vector2(0f,200f), Vector2(location10.width,200f),location10)

    val cave = GenericGameObject(Vector2(location10.originalMiddle.x - 256f, location10.topleft.y), Vector2(256f * 2, 283f * 2), "Cave.png", Layer.ONGROUND, location10)
    val doorPosition = Vector2(cave.originalMiddle.x - 64 / 2,cave.bottomleft.y)
    val doorCollition = DoorCollition(doorPosition,AreaIdentifier.DUNGEONAREA, doorWastelandAndDungeonConnection,Direction.UP)
    val door = Door(doorPosition, Vector2(32f * 2,36f * 2),
        DefaultTextureHandler.getTexture("CaveDoor.png"),location10,Direction.UP,doorCollition)

    val thorns = Thorns(location10.topright - Vector2(32f,64f), Vector2(32f,64f), location10)
    thorns.onRemoveAction.add {
        val thornsExists = SignalManager.pastSignals.List.find { it.id == thorns.entityId }
        if(thornsExists == null){
            SignalManager.emitSignal(
                Signal(
                    SIGNALTYPE.ADD_OBJECT,
                    -1,0,0f,0f,"location1",AreaIdentifier.MAINAREA.ordinal,ADDMETHODS.DOOR1.ordinal)
            )
            SignalManager.emitSignal(
                Signal(
                    SIGNALTYPE.ADD_OBJECT,
                    -1,0,0f,0f,"location10",AreaIdentifier.WASTELAND.ordinal,ADDMETHODS.DOOR2.ordinal)
            )
        }
    }
    return listOf(walkableTerrain, walkableTerrain2, cave,door, thorns)
}