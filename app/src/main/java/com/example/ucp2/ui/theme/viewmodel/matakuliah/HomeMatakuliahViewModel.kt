package com.example.ucp2.ui.theme.viewmodel.matakuliah

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.Data.entity.Matakuliah
import com.example.ucp2.repository.RepositoryMatakuliah
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeMatakuliahViewModel(
    private val repositoryMatakuliah: RepositoryMatakuliah
) : ViewModel() {

    private val _homeMatakuliahUiState = MutableStateFlow(HomeMatakuliahUiState())
    val homeMatakuliahUiState: StateFlow<HomeMatakuliahUiState> get() = _homeMatakuliahUiState

    init {
        loadMatakuliah()
    }

    private fun loadMatakuliah() {
        _homeMatakuliahUiState.value = HomeMatakuliahUiState(isLoading = true)
        viewModelScope.launch {
            try {
                val listMatakuliah = repositoryMatakuliah.getAllMatakuliah()
                _homeMatakuliahUiState.value = HomeMatakuliahUiState(
                    listMatakuliah = listMatakuliah,
                    isLoading = false,
                    isError = false
                )
            } catch (e: Exception) {
                _homeMatakuliahUiState.value = HomeMatakuliahUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = "Gagal memuat data mata kuliah."
                )
            }
        }
    }
}

data class HomeMatakuliahUiState(
    val listMatakuliah: List<Matakuliah> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null
)
