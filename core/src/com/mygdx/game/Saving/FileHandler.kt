package com.mygdx.game.Saving

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.files.FileHandle
import com.mygdx.game.Signal.Signal
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import java.io.BufferedWriter
import java.io.File


class FileHandler {
    companion object{

        val userHome = System.getProperty("user.home")
        val dirPath = userHome + File.separator + "ElenemtalBrawl" + File.separator + "SaveFiles"
        val savePath = "CurrentSave"

        val handle: FileHandle = Gdx.files.absolute(dirPath + File.separator + savePath)
        private val file: File = handle.file()
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
    }
}

