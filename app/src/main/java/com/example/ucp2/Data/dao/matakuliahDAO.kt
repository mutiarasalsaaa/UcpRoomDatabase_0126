package com.example.ucp2.Data.dao

import androidx.room.*
import com.example.ucp2.Data.entity.Matakuliah
import kotlinx.coroutines.flow.Flow

@Dao
interface matakuliahDAO {

    @Insert
    suspend fun insertmatakuliahDAO(matakuliah: Matakuliah)

    @Query("SELECT * FROM matakuliah ORDER BY kode ASC")
    fun getAllmatakuliah(): Flow<List<Matakuliah>>

    @Query("SELECT * FROM matakuliah WHERE kode = :kode")
    fun getMataKuliahByKode(kode: String): Flow<Matakuliah>

    @Update
    suspend fun updatematakuliah(matakuliah: Matakuliah)

    @Delete
    suspend fun deletematakuliah(matakuliah: Matakuliah)

}
