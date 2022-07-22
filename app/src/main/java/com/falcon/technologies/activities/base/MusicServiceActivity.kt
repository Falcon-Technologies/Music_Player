package com.falcon.technologies.activities.base

import android.os.Bundle
import com.falcon.technologies.helper.MusicPlayerRemote
import com.falcon.technologies.interfaces.IMusicServiceEventListener
import com.falcon.technologies.repository.RealRepository
import org.koin.android.ext.android.inject

abstract class MusicServiceActivity : BaseActivity(), IMusicServiceEventListener {

    private val mMusicServiceEventListener=ArrayList<IMusicServiceEventListener>()
    private val repository:RealRepository by inject()
    private var serviceToken:MusicPlayerRemote.ServiceToken?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


}