package com.mygdx.game.SaveHandling

class SaveCounterHandler {
    companion object{
        private var saveCounter = 0

        fun getSaveCounter():Int{
            return saveCounter++
        }
    }
}