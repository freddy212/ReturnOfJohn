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
    val mainAreaMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/Music/mainareatheme.mp3"))
    val icelandsAreaMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/Music/icelandstheme.mp3"))
    val firelandsAreaMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/Music/firelandstheme.mp3"))
    val dummyMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/Music/music.mp3"))
    val sandGhostMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/Music/sandghost.mp3"))
    val iceQueenMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/Music/icequeen.mp3"))
    val SartanMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/Music/sartan.mp3"))
    val HydraMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/Music/hydra.mp3"))
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