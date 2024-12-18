package com.example.ucp2.Data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2.Data.dao.dosenDAO
import com.example.ucp2.Data.dao.matakuliahDAO
import com.example.ucp2.Data.entity.dosen
import com.example.ucp2.Data.entity.matakuliah

// Mendefinisikan database dengan tabel Dosen dan MataKuliah
@Database(entities = [dosen::class, matakuliah::class], version = 1, exportSchema = false)
abstract class UcpDatabase : RoomDatabase() {

    // Mendefinisikan fungsi untuk mengakses data Dosen dan MataKuliah
    abstract fun dosenDao(): dosenDAO
    abstract fun mataKuliahDao(): matakuliahDAO

    companion object {
        @Volatile // Memastikan bahwa nilai variabel instance selalu sama di semua thread
        private var Instance: UcpDatabase? = null

        fun getDatabase(context: Context): UcpDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    UcpDatabase::class.java, // Class database
                    "UcpDatabase" // Nama database
                )
                    .build().also { Instance = it }
            }
        }
    }
}
