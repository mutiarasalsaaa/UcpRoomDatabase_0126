package com.example.ucp2.ui.theme.view.matakuliah

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.theme.customwidget.TopAppBar
import com.example.ucp2.ui.theme.viewmodel.matakuliah.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun UpdateMatakuliahView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateMatakuliahViewModel = viewModel(factory = PenyediaMataKuliahViewModel.Factory)
) {
    val uiState = viewModel.updateUIState // State dari ViewModel
    val snackbarHostState = remember { SnackbarHostState() } // State untuk Snackbar
    val coroutineScope = rememberCoroutineScope()

    // Observasi perubahan snackBarMessage
    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Long
                )
                viewModel.resetSnackBarMessage() // Reset pesan snackbar
            }
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                judul = "Edit Mata Kuliah",
                showBackButton = true,
                onBack = onBack,
                modifier = modifier
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            InsertBodyMatkul(
                uiState = uiState,
                onValueChange = {updateEvent ->
                    //viewModel.updateState(updateEvent)
                },
                onClick = {
                    coroutineScope.launch {
                        if (viewModel.validateFields()) { // Validasi input
                            viewModel.updateData() // Update data
                            delay(500) // Delay untuk animasi/snackbar
                            onNavigate() // Navigasi setelah berhasil
                        }
                    }
                }
            )
        }
    }
}
