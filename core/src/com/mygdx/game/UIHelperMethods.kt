package com.mygdx.game

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.mygdx.game.GameObjects.ShopItem.ShopItem
import com.mygdx.game.Interfaces.FightableEntity
import com.mygdx.game.Managers.UIRendererManager

fun drawHealthBar(pos: Vector2, size: Vector2, health: Float, maxHealth: Float){
    val shapeRenderer = UIRendererManager.uiShapeRenderer
    shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
    shapeRenderer.color = Color.WHITE;
    shapeRenderer.rect(pos.x, pos.y, size.x, size.y);
    shapeRenderer.end();
    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
    shapeRenderer.color = Color.GREEN
    shapeRenderer.rect(pos.x,pos.y, health / maxHealth * size.x,size.y)
    shapeRenderer.end()
}

fun drawShopItemImage(shopItem: ShopItem){
    val pos = Vector2(shopItem.x - 500f, shopItem.y)
    val size = Vector2(300f,300f)
    val shapeRenderer = UIRendererManager.uiShapeRenderer

    camera.project(Vector3(pos.x,pos.y,0f))
    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
    shapeRenderer.color = Color.BLACK;
    shapeRenderer.rect(pos.x, pos.y, size.x, size.y);
    shapeRenderer.end();
}