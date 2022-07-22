package com.falcon.technologies.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [SongEntity::class],
    version = 24,
    exportSchema = false
)abstract class PlayerDatabase:RoomDatabase(){
}