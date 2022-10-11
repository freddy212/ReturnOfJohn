package com.mygdx.game.Saving

import com.mygdx.game.Signal.Signal
import java.io.BufferedWriter
import java.io.File
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

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
            fileWriter.use { writer -> lines.forEach { writer.write(it)
                                                      writer.newLine()}}
        }
        fun writeSignalToFile(signal: Signal){
            val lines = readFromFile().toMutableList()
            val serializer = serializer(signal::class.java)
            val signalContent = Json.encodeToString(serializer,signal)
            lines.add(signalContent)
            fileWriter = file.bufferedWriter()
            fileWriter.use { writer -> lines.forEach { writer.write(it)
                writer.newLine()}}

        }
        fun readFromFile(): List<String>{
           return file.useLines { it.toList() }
        }
        fun SaveFileEmpty(): Boolean{
            return file.length() == 0L
        }
        /*fun writeSaveStateEntity(saveStateEntity: SaveStateEntity){
            val defaultSaveableObject = DefaultSaveableObject(saveStateEntity.entityId)
            writeToFile(defaultSaveableObject.entityId, Json.encodeToString(defaultSaveableObject))
        }*/
    }
}