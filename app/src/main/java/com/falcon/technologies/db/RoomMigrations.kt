package com.falcon.technologies.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_0_1 = object : Migration(23, 24) {
    override fun migrate(database: SupportSQLiteDatabase) {
    }
}