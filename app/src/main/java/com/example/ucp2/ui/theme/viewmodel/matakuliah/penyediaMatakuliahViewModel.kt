package com.example.ucp2.ui.theme.viewmodel.matakuliah

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.KrsApp
import com.example.ucp2.ui.viewmodel.krsApp

object PenyediaMataKuliahViewModel {
    val Factory = viewModelFactory {
        initializer{
            MatakuliahViewModel(
                krsApp().containerApp.repositoryMatakuliah,
            )
        }

        initializer {
            HomeMatakuliahViewModel(
                krsApp().containerApp.repositoryMatakuliah
            )
        }

        initializer {
            HomeMatakuliahViewModel(
                krsApp().containerApp.repositoryMatakuliah
            )
        }
    }
}

fun CreationExtras.krsApp(): KrsApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KrsApp)
