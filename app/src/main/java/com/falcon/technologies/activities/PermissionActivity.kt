package com.falcon.technologies.activities

import android.os.Bundle
import com.falcon.technologies.activities.base.MusicServiceActivity
import com.falcon.technologies.databinding.ActivityPermissionBinding

class PermissionActivity : MusicServiceActivity() {
    private lateinit var binding: ActivityPermissionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPermissionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnAllowAccess.setOnClickListener {
            requestPermissions()
        }

        binding.btnLater.setOnClickListener {
            finishAffinity()
        }
    }
}