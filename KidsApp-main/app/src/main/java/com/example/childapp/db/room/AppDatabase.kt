package com.example.childapp.db.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.childapp.common.Constants.DATABASE_NAME
import com.example.childapp.function.keylog.KeyLog

@Database(
    entities = [App::class,Child::class, Code::class, KeyLog::class, SendDate::class, ErrorApp::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}