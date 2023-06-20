package com.mygdx.game.Managers

import com.mygdx.game.Animation.Animation
import com.mygdx.game.Utils.RenderGraph
import com.mygdx.game.Utils.ResourceList

class AnimationManager {
    companion object {
        val animationManager = ResourceList<Animation>()

        fun addAnimationsToRender(){
            for(animation in animationManager.List.toList()){
                RenderGraph.addToSceneGraph(animation)
                animation.currentFrame++
                if(animation.currentFrame == animation.actionFrame){
                    animation.animationAction()
                }
                if(animation.currentFrame >= animation.duration){
                    animationManager.remove(animation)
                }
            }
        }
    }
}