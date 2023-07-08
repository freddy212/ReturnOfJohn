package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music

object DefaultSoundHandler {
    val music: Music = Gdx.audio.newMusic(Gdx.files.internal("Sound/SoundEffect/fire.mp3"))
}

object MusicLoader {
    val mainAreaMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/Music/MainAreaTheme.mp3"))
    val icelandsAreaMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/Music/IceLandsTheme.mp3"))
    val firelandsAreaMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/Music/FirelandsTheme.mp3"))
    val dummyMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/Music/music.mp3"))
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
                playMusic()
            }
        }
    }

}