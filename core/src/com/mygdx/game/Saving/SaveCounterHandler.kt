package com.mygdx.game.Saving

class SaveCounterHandler {
    companion object{
        private var saveCounter = 0

        fun getSaveCounter():Int{
            return saveCounter++
        }
    }
}