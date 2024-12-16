package com.example.ucp2.Data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface dosenDAO {
    @Insert
    suspend fun insertdosenDAO(
        dosenDAO: dosenDAO
    )

