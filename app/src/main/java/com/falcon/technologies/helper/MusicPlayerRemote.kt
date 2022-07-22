package com.falcon.technologies.helper

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.ServiceConnection
import androidx.core.content.ContextCompat
import com.falcon.technologies.service.MusicService
import org.koin.core.component.KoinComponent
import java.lang.IllegalStateException

object MusicPlayerRemote : KoinComponent {
    val TAG: String = MusicPlayerRemote::class.java.simpleName

    fun bindToServices(context:Context,callback:ServiceConnection):ServiceToken?{
        val realActivity=(context as Activity).parent?:context
        val contextWrapper=ContextWrapper(realActivity)
        val intent=Intent(contextWrapper, MusicService::class.java)
        try {
            contextWrapper.startService(intent)
        }
        catch (ignored:IllegalStateException){
           runCatching {
                ContextCompat.startForegroundService(context,intent)
            }
        }
    }

    class ServiceToken internal constructor(internal var mWrapperContext: ContextWrapper)
}