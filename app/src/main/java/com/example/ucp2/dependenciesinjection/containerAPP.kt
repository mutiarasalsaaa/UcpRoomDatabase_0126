package com.example.ucp2.dependenciesinjection

import android.content.Context
import com.example.meet10.repository.LocalRepositoryMatakuliah
import com.example.ucp2.Data.database.krsDatabase
import com.example.ucp2.repository.LocalRepositoryDosen
import com.example.ucp2.repository.RepositoryDosen
import com.example.ucp2.repository.RepositoryMatakuliah

interface InterfaceContainerApp {
    val repositoryDosen: RepositoryDosen
    val repositoryMatakuliah:RepositoryMatakuliah
}

class ContainerApp(private val context: Context) : InterfaceContainerApp {
    override val repositoryDosen: LocalRepositoryDosen by lazy {
        LocalRepositoryDosen(krsDatabase.getDatabase(context).dosenDao())
    }
    override val repositoryMatakuliah: LocalRepositoryMatakuliah by lazy {
        LocalRepositoryMatakuliah(krsDatabase.getDatabase(context).matakuliahDao())
    }
}
