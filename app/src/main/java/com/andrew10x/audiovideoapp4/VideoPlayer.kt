package com.andrew10x.audiovideoapp4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import com.andrew10x.audiovideoapp4.databinding.ActivityVideoPlayerBinding

class VideoPlayer : AppCompatActivity() {
    lateinit var binding: ActivityVideoPlayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var path = "android.resource://${packageName}/${R.raw.tfr}"
        var mediaController = MediaController(this)
        mediaController.setAnchorView(binding.videoView)
        binding.videoView.setMediaController(mediaController)
        binding.videoView.setVideoPath(path)
        binding.videoView.start()
    }

}