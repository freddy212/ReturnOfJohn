package com.mygdx.game.Managers

class GenericList<T> {
    private val mutableList: MutableList<T> = mutableListOf()
    val List : List<T>
        get() = mutableList.toList()
    fun add(t:T){
        mutableList.add(t)
    }
    fun remove(t:T){
        mutableList.remove(t)
    }

}