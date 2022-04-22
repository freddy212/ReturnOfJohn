package com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.SandGhost

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.Enemy
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.getDirectionUnitVector
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.SmallBoulder
import com.mygdx.game.Interfaces.EnemyAction
import com.mygdx.game.generateEnemyProjectile

class GhostBoulder(val sandGhost: SandGhost): EnemyAction() {

    private var counter = 0
    val originalTexture = sandGhost.sprite.texture

    override fun executeEnemyAction(enemy: Enemy) {
        if(counter == 0){
            sandGhost.sprite.texture = DefaultTextureHandler.getTexture("BossFaceMouth.png")
        }
        if(counter == 30) {
            generateEnemyProjectile(::SmallBoulder, enemy, Vector2(50f,50f))
            sandGhost.unitVectorDirection = Vector2(0f, 0f)
        }
        counter++
    }

    override val probability: Double
        get() = if(active) 1.0 else 0.01

    override val shouldBlock = true
    override val framesToBlock = 60

    override fun cleanUp() {
        sandGhost.sprite.texture = originalTexture
        counter = 0
    }

}