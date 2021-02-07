package com.mygdx.game.FileHandling

import java.io.File

class FileHandler {
    companion object{
        private val file = File("SaveFiles/CurrentSave")
        fun writeToFile(content: String){
            file.writeText(content)
        }
        fun readFromFile(): List<String>{
           return file.useLines { it.toList() }
        }

    }
}