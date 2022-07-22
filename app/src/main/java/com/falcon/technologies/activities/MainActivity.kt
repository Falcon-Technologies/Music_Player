package com.falcon.technologies.activities

import android.os.Bundle
import com.falcon.technologies.R
import com.falcon.technologies.activities.base.SlidingMusicPanelActivity

class MainActivity : SlidingMusicPanelActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}