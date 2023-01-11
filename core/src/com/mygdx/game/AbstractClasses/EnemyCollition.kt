import com.mygdx.game.AbstractClasses.Enemy
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.Projectile
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.Interfaces.FightableEntity
import com.mygdx.game.Interfaces.MoveCollition

open class EnemyCollition(val enemy: Enemy): MoveCollition{

    override fun collitionHappened(collidedObject: GameObject) {
        if(collidedObject is Player){
            collidedObject.isHit(enemy)
        }
    }

    override val canMoveAfterCollition = true
}