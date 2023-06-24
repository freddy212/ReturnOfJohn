package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music

object DefaultSoundHandler {
    val music: Music = Gdx.audio.newMusic(Gdx.files.internal("Sound/SoundEffect/fire.mp3"))
    val mainAreaMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/Music/music.mp3"))
}