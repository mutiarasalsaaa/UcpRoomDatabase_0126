package com.example.ucp2.Data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2.Data.dao.dosenDAO
import com.example.ucp2.Data.dao.matakuliahDAO
import com.example.ucp2.Data.entity.Dosen
import com.example.ucp2.Data.entity.Matakuliah

@Database(entities = [Dosen::class, Matakuliah::class], version = 2, exportSchema = false)
abstract class krsDatabase : RoomDatabase() {
    abstract fun dosenDao(): dosenDAO
    abstract fun matakuliahDao(): matakuliahDAO

    companion object {
        @Volatile
        private var Instance: krsDatabase? = null

        fun getDatabase(context: Context): krsDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    krsDatabase::class.java,
                    "krsDatabase"
                )
                    .fallbackToDestructiveMigration()
                    .build().also { Instance = it }
            }
        }
    }
}