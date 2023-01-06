package com.mygdx.game.InputActions

import com.badlogic.gdx.Input
import com.mygdx.game.AbstractClasses.CharacterAbility
import com.mygdx.game.Interfaces.InputAction
import com.mygdx.game.ItemAbilities.ProjectileAbilityToggle
import com.mygdx.game.player

class ChangeProjectileAction() : InputAction {

    override val keycodes = listOf(Input.Keys.UP, Input.Keys.DOWN, Input.Keys.SPACE)
    override fun action(keycode: Int) {
        if (ProjectileAbilityToggle.isToggle) {
            val abilityListSize = ProjectileAbilityToggle.abilitiesToToggle.List.size
            val activeIndex = ProjectileAbilityToggle.activeIndex
            if (keycode == Input.Keys.UP) {
                if (activeIndex - 1 < 0) {
                    ProjectileAbilityToggle.activeIndex = abilityListSize - 1
                } else {
                    ProjectileAbilityToggle.activeIndex = activeIndex - 1
                }
            } else if (keycode == Input.Keys.DOWN) {
                if (activeIndex + 1 == abilityListSize) {
                    ProjectileAbilityToggle.activeIndex = 0
                } else {
                    ProjectileAbilityToggle.activeIndex = activeIndex + 1
                }
            }

            else if (keycode == Input.Keys.SPACE){
                if (activeIndex + 1 == abilityListSize) {
                    ProjectileAbilityToggle.activeIndex = 0
                } else {
                    ProjectileAbilityToggle.activeIndex = activeIndex + 1
                }
            }
        }
    }
}