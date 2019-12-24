package com.landicho.alphacrusher

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import java.util.*
import kotlin.random.Random

class AlphabetCrusher : ApplicationAdapter() {
    lateinit internal var batch: SpriteBatch
    internal var randomNumber= (1..23).shuffled().first()

    //    lateinit internal var img: Texture
    val letters  = arrayOfNulls<Texture>(24)
    val sounds = arrayOfNulls<Music>(26)

    var windowWidth: Float = 0f
    var windowHeight: Float = 0f
    var letterWidth: Float = 0f
    var letterHeight: Float = 0f
    var letterChanged: Boolean = false

    override fun create() {
        windowWidth = (Gdx.graphics.width/ 2).toFloat()
        windowHeight = (Gdx.graphics.height / 2).toFloat()

        for(i in letters.indices){
            letters[i] = Texture((i + 1).toString() + ".png")
        }
        for(i in sounds.indices){
            sounds[i] = Gdx.audio.newMusic(Gdx.files.internal("sound/" + (i + 1).toString() +".ogg"))
        }
        batch = SpriteBatch()
    }

    override fun render() {

        if (Gdx.input.justTouched()) {
//            if(sounds[randomNumber]!!.isPlaying){
//                sounds[randomNumber]!!.pause()
//            }
            randomNumber= (1..23).shuffled().first()

            if(!sounds[randomNumber]!!.isPlaying){
                sounds[randomNumber]!!.play()
            }
            letterChanged = true
        }

//        Gdx.app.log("RANDOM NUMBER", "RANDOM NUMBER: " + randomNumber)

        letterWidth = (letters[randomNumber]!!.width / 2).toFloat()
        letterHeight = (letters[randomNumber]!!.height / 2).toFloat()

        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch.begin()
        batch.draw(letters[randomNumber], windowWidth - letterWidth, windowHeight - letterHeight)

        if(!sounds[randomNumber]!!.isPlaying && !letterChanged){
            sounds[randomNumber]!!.play()
            letterChanged = true
        }

        batch.end()
    }

    override fun dispose() {

//        Gdx.app.log("DISPOSE", "DISPOSE")
        batch.dispose()

        for(i in letters.indices){
            letters[i]?.dispose()
        }

        for(i in sounds.indices){
            sounds[i]?.dispose()
        }
    }
}
