import com.mygdx.game.AbstractClasses.Enemy
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.Projectile
import com.mygdx.game.Enums.CharacterState
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.Interfaces.FightableEntity
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.ItemAbilities.DashAbilityUpgraded
import com.mygdx.game.player

open class EnemyCollition(val enemy: Enemy): MoveCollition{

    override fun collitionHappened(collidedObject: GameObject) {
        if(collidedObject is Player){
            if(collidedObject.characterState == CharacterState.DASHING && player.itemAbilities.List.any { it is DashAbilityUpgraded }){
                enemy.isHit(player)
            }
            else{
                collidedObject.isHit(enemy)
            }
        }
    }

    override val canMoveAfterCollition = true
}