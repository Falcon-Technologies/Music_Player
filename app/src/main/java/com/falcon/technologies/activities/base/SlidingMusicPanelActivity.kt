package com.falcon.technologies.activities.base

import android.content.SharedPreferences
import android.os.Bundle
import com.falcon.technologies.R

abstract class SlidingMusicPanelActivity : MusicServiceActivity(),
    SharedPreferences.OnSharedPreferenceChangeListener {
    companion object {
        val TAG: String = SlidingMusicPanelActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sliding_music_panel)
    }
}