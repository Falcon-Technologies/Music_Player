package com.falcon.technologies.activities.base

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.getSystemService
import com.falcon.technologies.extensions.rootView
import com.falcon.technologies.utils.VersionUtils
import com.falcon.technologies.utils.logD
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity : AppCompatActivity() {

    init {
        logD("Init Block")
    }

    companion object {
        const val PERMISSION_REQUEST = 786
    }

    private var hadPermission: Boolean = false
    private lateinit var permission: Array<String>
    private var permissionDeniedMessage: String? = null


    private fun getPermissionToRequest(): Array<String> {
        return arrayOf()
    }

    protected fun setPermissionDeniedMessage(message: String) {
        permissionDeniedMessage = message
    }

    private val snackBarContainer: View get() = rootView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permission = getPermissionToRequest()
        hadPermission = hasPermission()
        permissionDeniedMessage = null
    }

    override fun onResume() {
        super.onResume()
        val hasPermission = hasPermission()
        if (hasPermission != hadPermission) {
            hadPermission = hasPermission
            if (VersionUtils.hasMarshmallow()) {
                onHasPermissionChanged(hasPermission)
            }
        }
    }

    private fun onHasPermissionChanged(hasPermission:Boolean) {
        logD(hasPermission)
    }

    private fun hasPermission(): Boolean {
        for (permission in permission) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }

  protected open fun requestPermissions() {
        ActivityCompat.requestPermissions(this, permission, PERMISSION_REQUEST)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST) {
            for (grantResult in grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(
                            this@BaseActivity,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        ) || ActivityCompat.shouldShowRequestPermissionRationale(
                            this@BaseActivity,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        )
                    ) {
                        //user has deny from permission dialog
                        Snackbar.make(
                            snackBarContainer,
                            permissionDeniedMessage!!,
                            Snackbar.LENGTH_SHORT
                        )
                            .setAction("Grant") { requestPermissions() }
                            .show()
                    } else {
                        // User has deny permission and checked never show permission dialog so you can redirect to Application settings page
                        Snackbar.make(
                            snackBarContainer,
                            permissionDeniedMessage!!,
                            Snackbar.LENGTH_INDEFINITE
                        ).setAction("Setting") {
                            val intent = Intent()
                            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                            val uri = Uri.fromParts(
                                "package",
                                this@BaseActivity.packageName,
                                null
                            )
                            intent.data = uri
                            startActivity(intent)
                        }.show()
                    }
                    return
                }
            }
            hadPermission=true
            onHasPermissionChanged(true)
        }
    }

    // this lets keyboard close when clicked in background
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    getSystemService<InputMethodManager>()?.hideSoftInputFromWindow(
                        v.windowToken,
                        0
                    )
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

}