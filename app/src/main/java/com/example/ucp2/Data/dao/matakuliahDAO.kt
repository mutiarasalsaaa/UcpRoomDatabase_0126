package com.example.ucp2.Data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucp2.Data.entity.dosen
import com.example.ucp2.Data.entity.matakuliah
import kotlinx.coroutines.flow.Flow

@Dao
interface matakuliahDAO {
    @Insert
    suspend fun insertmatakuliahDAO(
        matakuliah: matakuliah
    )
    @Query("SELECT * FROM matakuliah ORDER BY kode ASC")
    fun getAllmatakuliah () : Flow<List<matakuliah>>

    @Query("SELECT * FROM matakuliah WHERE nama= :nama")
    fun getmatakuliah(nama: String) : Flow<matakuliah>

    @Delete
    suspend fun deletematakuliah(matakuliah: matakuliah)

    @Update
    suspend fun updatematakuliah (matakuliah: matakuliah)

}

}

