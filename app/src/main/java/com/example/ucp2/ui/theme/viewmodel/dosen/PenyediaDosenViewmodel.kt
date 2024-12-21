package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.KrsApp
import com.example.ucp2.ui.theme.viewmodel.dosen.DetailDosenViewModel
import com.example.ucp2.ui.theme.viewmodel.dosen.DosenViewModel
import com.example.ucp2.ui.theme.viewmodel.dosen.HomeDosenViewModel
import com.example.ucp2.ui.theme.viewmodel.matakuliah.HomeMatakuliahViewModel
import com.example.ucp2.ui.theme.viewmodel.matakuliah.MatakuliahViewModel

object PenyediaDosenViewModel{
    val Factory = viewModelFactory {
        initializer {
            DosenViewModel(
                krsApp().containerApp.repositoryDosen
            )
        }
        initializer{
            HomeDosenViewModel(
                krsApp().containerApp.repositoryDosen
            )
        }

        initializer{
            DetailDosenViewModel(
                createSavedStateHandle(),
                krsApp().containerApp.repositoryDosen,
            )
        }
        initializer {
            MatakuliahViewModel(
                krsApp().containerApp.repositoryMatakuliah
            )
        }
        initializer {
            HomeMatakuliahViewModel(
                krsApp().containerApp.repositoryMatakuliah
            )
        }
        initializer {
            HomeDosenViewModel(
                krsApp().containerApp.repositoryDosen

            )
        }

    }
}
fun CreationExtras.krsApp(): KrsApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KrsApp)