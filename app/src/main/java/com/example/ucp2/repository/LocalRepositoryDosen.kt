package com.example.ucp2.repository

import com.example.ucp2.Data.dao.dosenDAO
import com.example.ucp2.Data.entity.Dosen
import kotlinx.coroutines.flow.Flow

class LocalRepositoryDosen(
    private val dosenDAO: dosenDAO
) : RepositoryDosen {
    override suspend fun insertDosen(dosen: Dosen) {
        dosenDAO.insertDosen(dosen)
    }

    override fun getAllDosen(): Flow<List<Dosen>> {
        return dosenDAO.getAllDosen()
    }

    override fun getDosen(nidn: String): Flow<Dosen> {
        return dosenDAO.getDosen(nidn)
    }
}
