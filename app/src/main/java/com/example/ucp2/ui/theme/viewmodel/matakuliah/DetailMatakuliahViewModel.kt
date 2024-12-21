

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.Data.entity.Matakuliah
import com.example.ucp2.repository.RepositoryMatakuliah
import com.example.ucp2.ui.theme.navigation.DestinasiDetailMatakuliah
import com.example.ucp2.ui.theme.viewmodel.matakuliah.toMatakuliahEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailMatakuliahViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMatakuliah: RepositoryMatakuliah,

    ) : ViewModel() {
    private val kode: String = checkNotNull(savedStateHandle[DestinasiDetailMatakuliah.KODE])

    val detailUiState: StateFlow<DetailUiState> = repositoryMatakuliah.getMatakuliah(kode)
        .filterNotNull()
        .map {
            DetailUiState(
                detailUiEvent = it.toDetailUiEvent(),
                isLoading = false,
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
                    errorMessage = it.message ?: "Terjadi kesalahan",
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailUiState(
                isLoading = true,
            ),
        )

    fun deleteMatakuliah() {
        detailUiState.value.detailUiEvent.toMatakuliahEntity().let {
            viewModelScope.launch {
                repositoryMatakuliah.deleteMatakuliah(it)
            }
        }
    }
}

data class DetailUiState(
    val detailUiEvent: MatakuliahEvent = MatakuliahEvent (),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == MatakuliahEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != MatakuliahEvent ()
}

/*
Data class untuk menampung data yang akan ditampilkan di UI
*/
//memindahkan data dari entity ke ui
fun Matakuliah.toDetailUiEvent () : MatakuliahEvent {
    return MatakuliahEvent(
        kode = kode,
        nama = nama,
        sks = sks,
        semester = semester,
        jenis = jenis,
        dosenPengampu = dosenPengampu
    )
}

data class MatakuliahEvent(
    val kode: String="",
    val nama: String="",
    val sks: String="",
    val semester: String="",
    val jenis: String="",
    val dosenPengampu: String="",

)