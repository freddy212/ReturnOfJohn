package com.mygdx.game

import java.util.function.Predicate

class ResourceList<T> {
    private val mutableList: MutableList<T> = mutableListOf()
    val List : List<T>
        get() = mutableList.toList()
    fun add(t:T){
        mutableList.add(t)
    }
    fun remove(t:T){
        mutableList.remove(t)
    }
    fun removeIf(p:Predicate<T>){
        mutableList.removeIf(p)
    }

}