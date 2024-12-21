package com.example.ucp2.Data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ucp2.Data.entity.Dosen
import kotlinx.coroutines.flow.Flow

@Dao
interface dosenDAO {
    @Insert
    suspend fun insertDosen(dosen: Dosen)

    @Query("SELECT * FROM dosen ORDER BY nama ASC")
    fun getAllDosen(): Flow<List<Dosen>>

    @Query("SELECT * FROM dosen WHERE Nidn= :Nidn")
    fun getDosenById(Nidn: Int): Flow<Dosen>
    fun getDosen(nidn: String): Flow<Dosen> {
        TODO("Not yet implemented")
    }
}
