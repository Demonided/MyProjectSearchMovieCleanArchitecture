package com.katoklizm.myprojectsearchmoviecleanarchitecture.ui.poster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.katoklizm.myprojectsearchmoviecleanarchitecture.R
import com.katoklizm.myprojectsearchmoviecleanarchitecture.util.Creator

class PosterActivity : AppCompatActivity() {

    private val providePosterController = Creator.providePosterController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poster)
        providePosterController.onCreate()
    }
}