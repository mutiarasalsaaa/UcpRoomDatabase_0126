package com.example.ucp2.repository

import com.example.ucp2.Data.entity.Matakuliah
import kotlinx.coroutines.flow.Flow

interface RepositoryMatakuliah {
    suspend fun insertMatakuliah(matakuliah: Matakuliah)
    fun getAllMatakuliah(): Flow<List<Matakuliah>>

    fun getMatakuliah(nidn: String): Flow<Matakuliah>

    suspend fun deleteMatakuliah(Matakuliah: Matakuliah)
    suspend fun updateMatakuliah(matakuliah: Matakuliah)
}
