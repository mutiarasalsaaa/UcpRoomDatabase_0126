package com.example.ucp2.ui.theme.viewmodel.dosen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.Data.entity.Dosen
import com.example.ucp2.repository.RepositoryDosen
import com.example.ucp2.ui.theme.navigation.DestinasiDetailDosen
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class DetailDosenViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryDosen: RepositoryDosen

) : ViewModel() {
    private val nidn: String = checkNotNull(savedStateHandle[DestinasiDetailDosen.NIDN])

    val detailUiState: StateFlow<DetailUiState> = repositoryDosen.getDosen(nidn)
        .filterNotNull()
        .map {
            DetailUiState(
                detailUiEvent = it.toDetailDosenUiEvent(),
                isLoading = false
            )
        }
        .onStart {
            emit(DetailUiState(isLoading = true))
            delay(600)
        }
        .catch {
            emit(
                DetailUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailUiState(
                isLoading = true
            ),
        )

}

data class DetailUiState(
    val detailUiEvent: DosenEvent = DosenEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == DosenEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != DosenEvent()
}

// Data class untuk menampung data yang akan ditampilkan di UI
fun Dosen.toDetailDosenUiEvent(): DosenEvent {
    return DosenEvent(
        nidn = nidn,
        nama = nama,
        jenisKelamin = jenisKelamin
    )
}

data class dosenEvent(
    val nidn: String = "",
    val nama: String = "",
    val jenisKelamin: String = ""
) {
    fun toDosenEntity(): Dosen {
        return Dosen(
            nidn = nidn,
            nama = nama,
            jenisKelamin = jenisKelamin
        )
    }
}
