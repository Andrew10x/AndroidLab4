package com.andrew10x.audiovideoapp4

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.VideoView
import com.andrew10x.audiovideoapp4.databinding.ActivityMainBinding
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import java.util.regex.Pattern

class MainActivity : YouTubeBaseActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializePlayer(getYoutubeVideoFromUrl("https://www.youtube.com/watch?v=aqP4-dUMkMc&list=RDdf9_tLXj-jc&index=3&ab_channel=BillyTalent")!!)

        binding.button.setOnClickListener {
            val i = Intent(this, AudioPlayer::class.java)
            startActivity(i)
        }
        binding.button2.setOnClickListener {
            val i = Intent(this, VideoPlayer::class.java)
            startActivity(i)
        }
    }

    fun initializePlayer(videoId: String) {
        //var youtubeplayer = findViewById<View>(R.id.youtubeplayer)
        binding.youtubeplayer.initialize(getString(R.string.api_key), object: YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                p1!!.loadVideo(videoId)
                p1.play()
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {

            }

        })
    }

    fun getYoutubeVideoFromUrl(inUrl: String): String? {
        if(inUrl.toLowerCase().contains("youtu.be"))
            return inUrl.substring(inUrl.lastIndexOf("/") + 1)

        val pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*"
        val compiledPattern = Pattern.compile(pattern)
        val matcher = compiledPattern.matcher(inUrl)
        return if(matcher.find()){
            matcher.group()
        }else null
    }
}