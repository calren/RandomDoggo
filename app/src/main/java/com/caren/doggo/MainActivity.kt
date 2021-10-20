package com.caren.doggo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.core.net.toUri
import coil.load

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.lastApiResponse.observe(this,
            {
                findViewById<ImageView>(R.id.imageView).load(
                    it.message?.toUri()?.buildUpon()?.scheme("https")?.build()
                ) {
                    placeholder(R.drawable.loading_animation)
                }
            })

        findViewById<Button>(R.id.button).setOnClickListener {
            viewModel.getNewDog()
        }
    }
}