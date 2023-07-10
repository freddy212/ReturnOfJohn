import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.AbilityId
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.DoorCollition
import com.mygdx.game.DataClasses.DoorData
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Element
import com.mygdx.game.Enums.ItemType
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.ItemObjects.AbilityItemObject
import com.mygdx.game.GameObjects.ItemObjects.GenericInventoryItemObject
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.RockBoss.RockBoss
import com.mygdx.game.GameObjects.MoveableEntities.Characters.NPC
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Mobs.RockMonster
import com.mygdx.game.GameObjects.Other.*
import com.mygdx.game.GameObjects.SensorObjects.SandGhostSleeping
import com.mygdx.game.GameObjects.Terrain.WalkableTerrain
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.ItemAbilities.DashAbility
import com.mygdx.game.ItemAbilities.WaterBallAbility
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Managers.SignalManager
import com.mygdx.game.Signal.Signal
import com.mygdx.game.Signal.SignalListeners.ADDMETHODS
import com.mygdx.game.Signal.Signals.AddAbilityItemSignal
import com.mygdx.game.Signal.Signals.AddObjectSignal
import com.mygdx.game.Signal.Signals.RemoveObjectSignal
import com.mygdx.game.UI.Dialogue.Conversations.*

fun getWastelandLocationOneObjects(): List<GameObject>{
    val location1 = LocationManager.findLocation("location1", AreaIdentifier.WASTELAND)

    val doorPosition = Vector2(location1.originalMiddle.x - (playerSize.x),location1.topleft.y)

    val doorCollition = DoorCollition(doorPosition,
        AreaIdentifier.MAINAREA, doorMainAreaAndWasteland,
        Direction.UP)

    val door = Door(doorPosition, Vector2(32f * 2, 32f * 2), DefaultTextureHandler.getTexture("EmptyDoor.png"),location1,
        Direction.UP,doorCollition)
    return listOf(door)
}

fun getWastelandLocationTwoObjects(): List<GameObject>{
    val location = LocationManager.findLocation("location2",AreaIdentifier.WASTELAND)

    val sign = Sign(location.currentMiddle - Vector2(40f,60f), Vector2(80f,80f), location, "Water Ball",  "Press 1 - Fire Projectile")
    val abilityItem = AbilityItemObject(sign.bottomright + Vector2(100f,0f), Vector2(60f,60f), location,
        WaterBallAbility(),DefaultTextureHandler.getTexture("WaterBall.png"))
    return listOf(sign, abilityItem)
}

fun getWastelandLocationThreeObjects(): List<GameObject>{
    val location4 = LocationManager.findLocation("location2",AreaIdentifier.WASTELAND)
    val location5 = LocationManager.findLocation("location3",AreaIdentifier.WASTELAND)
    val walkableTerrain = WalkableTerrain(Vector2(location5.x,location4.y), Vector2(location5.width,location4.height),location5)
    val incrementY = ((walkableTerrain.topleft.y - walkableTerrain.bottomleft.y) / 6).toInt()
    val thorns = ConstructObjects(::Thorns, walkableTerrain.currentMiddle.x.toInt(), 32, walkableTerrain.currentMiddle.x.toInt() + 1, walkableTerrain.topleft.y.toInt() - incrementY, incrementY , walkableTerrain.bottomleft.y.toInt(), location5)
    return listOf(walkableTerrain) + thorns
}
fun getWastelandLocationEightObjects(): List<GameObject>{
    val location7 = LocationManager.findLocation("location7",AreaIdentifier.WASTELAND)
    val location8 = LocationManager.findLocation("location8", AreaIdentifier.WASTELAND)
    val walkableTerrain = WalkableTerrain(location7.bottomleft - Vector2(250f,0f), Vector2(250f,200f),location8)
    val walkableTerrain2 = WalkableTerrain(location8.currentMiddle - Vector2(500f, 400f), Vector2(1000f,800f),location8)
    val sandGhostSleeping = SandGhostSleeping(walkableTerrain2.currentMiddle - Vector2(75f, 0f),Vector2(150f,150f), location8)
    return listOf(walkableTerrain,walkableTerrain2, sandGhostSleeping)
}
fun getWastelandLocationFourObjects(): List<GameObject>{
    val location = LocationManager.findLocation("location4",AreaIdentifier.WASTELAND)

    val sign = Sign(location.bottomleft + Vector2(80f,120f), Vector2(80f,80f), location, "Press M - View Map")
    return listOf(sign)
}

fun getWastelandLocationSevenObjects(): List<GameObject>{
    val location = LocationManager.findLocation("location7", AreaIdentifier.WASTELAND)
    val sign = Sign(location.currentMiddle, Vector2(80f,80f), location, "A  sleeping spirit rests beyond")

    return listOf(sign)
}
fun getWastelandLocationSixObjects(): List<GameObject>{
    val location9 = LocationManager.findLocation("location5",AreaIdentifier.WASTELAND)
    val location10 = LocationManager.findLocation("location6", AreaIdentifier.WASTELAND)
    val walkableTerrain = WalkableTerrain(Vector2(location9.topleft), Vector2(200f,location10.height),location10)
    val walkableTerrain2 = WalkableTerrain(Vector2(location10.topleft) - Vector2(0f,200f), Vector2(location10.width,200f),location10)

    val cave = GenericGameObject(Vector2(location10.originalMiddle.x - 256f, location10.topleft.y), Vector2(256f * 2, 283f * 2), "Cave.png", Layer.ONGROUND, location10)
    val doorPosition = Vector2(cave.originalMiddle.x - 64 / 2,cave.bottomleft.y)
    val doorCollition = DoorCollition(doorPosition,AreaIdentifier.DUNGEONAREA, doorWastelandAndDungeonConnection,Direction.UP)
    val door = Door(doorPosition, Vector2(32f * 2,36f * 2),
        DefaultTextureHandler.getTexture("CaveDoor.png"),location10,Direction.UP,doorCollition)

    val thorns = Thorns(location10.topright - Vector2(32f,64f), Vector2(32f,64f), location10)
    thorns.onRemoveAction.add {
        val removeEvents: List<RemoveObjectSignal> = SignalManager.pastSignals.List.filter { it is RemoveObjectSignal }.map { it as RemoveObjectSignal }
        val thornsRemoved = removeEvents.find { it.entityId == thorns.entityId }
        if(thornsRemoved == null){
            SignalManager.emitSignal(
                AddObjectSignal(
                    ADDMETHODS.DOOR1,"location1",AreaIdentifier.MAINAREA)
            )
            SignalManager.emitSignal(
                AddObjectSignal(
                    ADDMETHODS.DOOR2,"location6",AreaIdentifier.WASTELAND)
            )
        }
    }
    return listOf(walkableTerrain, walkableTerrain2, cave,door, thorns)
}