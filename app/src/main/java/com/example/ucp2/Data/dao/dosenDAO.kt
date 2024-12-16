package com.example.ucp2.Data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ucp2.Data.entity.dosen
import kotlinx.coroutines.flow.Flow

@Dao
interface dosenDAO {
    @Insert
    suspend fun insertdosenDAO(
        dosen: dosen
    )
    @Query("SELECT * FROM dosen ORDER BY nama ASC")
    fun getAlldosen () : Flow<List<dosen>>

    @Query ("SELECT * FROM dosen WHERE Nidn= :Nidn")
    fun getdosen(Nidn: String) : Flow<dosen>

}


