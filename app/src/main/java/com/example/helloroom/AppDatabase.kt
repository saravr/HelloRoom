package com.example.helloroom

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

abstract class AppDatabase: RoomDatabase() {
    abstract fun documentsDao(): DocumentsDao
    abstract fun savedDao(): SavedDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        private const val DB_FILE_NAME = "app.db"

        fun getDatabase(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DB_FILE_NAME,
                ).build().also {
                    instance = it
                }
            }
        }

        fun getDatabaseSize(context: Context): Long {
            val folder = context.filesDir.absolutePath.replace("files", "databases")
            val filename = File("$folder/$DB_FILE_NAME")

            return if (filename.exists()) filename.length() else -1
        }
    }
}
