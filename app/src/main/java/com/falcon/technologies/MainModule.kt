package com.falcon.technologies

import androidx.room.Room
import com.falcon.technologies.db.MIGRATION_0_1
import com.falcon.technologies.db.PlayerDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val mainModule = module {
    single {
        androidContext().contentResolver
    }
}
val roomModule = module {

    single {
        Room.databaseBuilder(androidContext(), PlayerDatabase::class.java, "playlist.db")
            .addMigrations(
                MIGRATION_0_1
            ).build()

    }

}

val appModules = listOf(mainModule, roomModule)
