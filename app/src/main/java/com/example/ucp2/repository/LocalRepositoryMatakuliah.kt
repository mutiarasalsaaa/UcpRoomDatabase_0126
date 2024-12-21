package com.example.meet10.repository

import com.example.ucp2.Data.dao.matakuliahDAO
import com.example.ucp2.Data.entity.Matakuliah
import com.example.ucp2.repository.RepositoryMatakuliah
import kotlinx.coroutines.flow.Flow

class LocalRepositoryMatakuliah(
    private val matakuliahDAO: matakuliahDAO) : RepositoryMatakuliah {
    override suspend fun insertMatakuliah(matakuliah: Matakuliah) {
        matakuliahDAO.insertmatakuliahDAO(matakuliah)
    }

    override fun getAllMatakuliah(): Flow<List<Matakuliah>> {
        return matakuliahDAO.getAllmatakuliah()
    }

    override fun getMatakuliah(nim: String): Flow<Matakuliah> {
        return matakuliahDAO.getMataKuliahByKode(nim)
    }

    override suspend fun deleteMatakuliah(matakuliah: Matakuliah) {
        matakuliahDAO.deletematakuliah(matakuliah)
    }

    override suspend fun updateMatakuliah(matakuliah: Matakuliah) {
        matakuliahDAO.updatematakuliah(matakuliah)
    }
}