package com.falcon.technologies.repository

import android.content.Context

interface Repository {

}

class RealRepository(
    private val context: Context
    ) {

}