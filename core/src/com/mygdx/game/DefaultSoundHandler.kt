package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound

object DefaultSoundHandler {
    val music: Music = Gdx.audio.newMusic(Gdx.files.internal("Sound/SoundEffect/fire.mp3"))

    fun playSound(sound: Sound, volume: Float = 0.1f){
        sound.play(volume)
    }
}

object MusicLoader {
    val mainAreaMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/Music/MainAreaTheme.mp3"))
    val icelandsAreaMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/Music/IceLandsTheme.mp3"))
    val firelandsAreaMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/Music/FirelandsTheme.mp3"))
    val dummyMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/Music/music.mp3"))
    val sandGhostMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/Music/SandGhost.mp3"))
    val iceQueenMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/Music/IceQueen.mp3"))
    val SartanMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/Music/Sartan.mp3"))
    val HydraMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/Music/Hydra.mp3"))
}

class DefaultMusicHandler{
    companion object {
        var currentlyTrack : Music = MusicLoader.dummyMusic

        private fun stopTrack(){
            currentlyTrack.stop()
        }

        private fun changeTrack(music: Music){
            currentlyTrack = music
        }

        private fun playMusic(){
            currentlyTrack.isLooping = true
            currentlyTrack.play()
        }

        fun changeAndPlay(music: Music){
            if(music != currentlyTrack){
                stopTrack()
                changeTrack(music)
                currentlyTrack.volume = 0.1f
                playMusic()
            }
        }
    }

}