package com.mygdx.game.SaveHandling

import com.mygdx.game.SaveState.SaveStateEntity
import java.io.BufferedWriter
import java.io.File
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class FileHandler {
    companion object{
        private val file = File("SaveFiles/CurrentSave")
        private lateinit var fileWriter: BufferedWriter
        fun writeToFile(entityId : Int,content: String){
            val lines = readFromFile().toMutableList()
            val existingLineIndex = lines.indexOfFirst{ it.contains("\"entityId\":$entityId") }
            if (existingLineIndex == -1){
                lines.add(content)
            }else{
                lines[existingLineIndex] = content
            }
            fileWriter = file.bufferedWriter()
            fileWriter.use {writer -> lines.forEach { writer.write(it)
                                                      writer.newLine()}}
        }
        fun readFromFile(): List<String>{
           return file.useLines { it.toList() }
        }
        fun SaveFileEmpty(): Boolean{
            return file.length() == 0L
        }
        fun writeSaveStateEntity(saveStateEntity: SaveStateEntity){
            val defaultSaveableObject = DefaultSaveableObject(saveStateEntity.entityId)
            writeToFile(defaultSaveableObject.entityId, Json.encodeToString(defaultSaveableObject))
        }
    }
}