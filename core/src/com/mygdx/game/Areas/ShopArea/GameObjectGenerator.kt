package com.mygdx.game.Areas.ShopArea

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.Collitions.WaterGunItemCollition
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.Door
import com.mygdx.game.GameObjects.GenericGameObject
import com.mygdx.game.GameObjects.MoveableEntities.NPC
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.doorMainAreaAndShop
import com.mygdx.game.middleOfObject
import com.mygdx.game.playerSize

fun getShopObjects(): List<GameObject>{
    val location1 = LocationManager.findLocation("location1", AreaIdentifier.SHOP)
    val door = Door(Vector2(location1.middle.x -  (playerSize.x / 2),location1.bottomleft.y), Vector2(32f * 2, 64f * 2), Texture("CaveDoor.png"), AreaIdentifier.MAINAREA,
            doorMainAreaAndShop, Direction.DOWN,location1)
    val size = Vector2(120f,60f)
    val position = location1.middle
    val itemTable = GenericGameObject(middleOfObject(position,size),size,"ItemTable.png",Layer.ONGROUND,location1,IllegalMoveCollition)
    val shopNPC = NPC(middleOfObject(Vector2(itemTable.middle.x,itemTable.middle.y + 200f),Vector2(128f,128f)), Vector2(128f,128f),location1)
    val waterGunSize = Vector2(60f,40f)
    val waterGun = GenericGameObject(middleOfObject(itemTable.middle,waterGunSize),waterGunSize,"WaterGun.png",Layer.AIR,location1,WaterGunItemCollition())
    return listOf(door,itemTable,shopNPC,waterGun)
}