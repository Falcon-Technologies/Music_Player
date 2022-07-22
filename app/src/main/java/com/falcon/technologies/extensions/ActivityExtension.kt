package com.falcon.technologies.extensions

import android.app.Activity
import android.view.View
import android.view.ViewGroup


inline val Activity.rootView: View
    get() = findViewById<ViewGroup>(android.R.id.content).getChildAt(
        0
    )