package com.example.ucp2.Data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dosen")
data class dosen(
    @PrimaryKey
    val Nidn: String,
    val nama: String,
    val jenisKelamin: String,
)
