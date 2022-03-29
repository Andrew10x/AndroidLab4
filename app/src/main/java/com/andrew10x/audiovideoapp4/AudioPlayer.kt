package com.andrew10x.audiovideoapp4

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.andrew10x.audiovideoapp4.databinding.ActivityAudioPlayerBinding

class AudioPlayer : AppCompatActivity() {
    private lateinit var binding: ActivityAudioPlayerBinding
    private var player: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAudioPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.play.setOnClickListener {
            if(player == null){
                player = MediaPlayer.create(this, R.raw.bmth)
                player!!.setOnCompletionListener(MediaPlayer.OnCompletionListener {
                    fun onCompletion(mp: MediaPlayer) {
                        stopPlayer()
                    }
                })
            }

            player!!.start()
        }

        binding.pause.setOnClickListener {
            if(player != null){
                player!!.pause()
            }
        }

        binding.stop.setOnClickListener {
            stopPlayer()
        }
    }

    private fun stopPlayer() {
        if(player != null) {
            player!!.release()
            player = null
        }
    }

    override fun onStop() {
        super.onStop()
        stopPlayer()
    }
}