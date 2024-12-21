package com.example.ucp2.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucp2.ui.theme.view.dosen.DestinasiInsertDosen
import com.example.ucp2.ui.theme.view.dosen.DetailDosenView
import com.example.ucp2.ui.theme.view.dosen.HomeDosenView
import com.example.ucp2.ui.theme.view.dosen.HomeMenuView
import com.example.ucp2.ui.theme.view.dosen.InsertDosenView
import com.example.ucp2.ui.theme.view.matakuliah.DestinasiInsertMatakuliah
import com.example.ucp2.ui.theme.view.matakuliah.DetailMatakuliahView
import com.example.ucp2.ui.theme.view.matakuliah.HomeMatakuliahView
import com.example.ucp2.ui.theme.view.matakuliah.InsertMatakuliahView
import com.example.ucp2.ui.theme.view.matakuliah.UpdateMatakuliahView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHomeMenu.route
    ) {
        composable(
            route = DestinasiHomeMenu.route
        ) {
            HomeMenuView(
                onDosenClick = {
                    navController.navigate(DestinasiHomeDosen.route)
                },
                onMatkulClick = {
                    navController.navigate(DestinasiHomeMatakuliah.route)
                }
            )
        }
        // Layar Home Dosen
        composable(
            route = DestinasiHomeDosen.route
        ) {
            HomeDosenView(
                onDetailClick = { nidn ->
                    navController.navigate("${DestinasiDetailDosen.route}/$nidn")
                },
                onAddDosen = {
                    navController.navigate(DestinasiInsertDosen.route)
                },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiHomeMatakuliah.route
        ) {
            HomeMatakuliahView(
                onDetailClick = { kode ->
                    navController.navigate("${DestinasiDetailMatakuliah.route}/$kode")
                },
                onAddMatakuliah = {
                    navController.navigate(DestinasiInsertMatakuliah.route)
                },
                onBack = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }
        composable (
            route = DestinasiInsertDosen.route
        ) {
            InsertDosenView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }

        composable (
            DestinasiDetailDosen.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailDosen.NIDN) {
                    type = NavType.StringType
                }
            )
        ){
            val nidn = it.arguments?.getString(DestinasiDetailDosen.NIDN)

            nidn?.let { nidn ->
                DetailDosenView(
                    onBack = {
                        navController.popBackStack()
                    },
                )
            }
        }

        composable (
            route =DestinasiInsertMatakuliah.route
        ) {
            InsertMatakuliahView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }

        composable (
            DestinasiDetailMatakuliah.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailMatakuliah.KODE) {
                    type = NavType.StringType
                }
            )
        ){
            val kode = it.arguments?.getString(DestinasiDetailMatakuliah.KODE)

            kode?.let { kode ->
                DetailMatakuliahView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateMatakuliah.route}/$kode")
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(
            DestinasiUpdateMatakuliah.routesWithArg,
            arguments = listOf(
                navArgument (DestinasiUpdateMatakuliah.KODE) {
                    type = NavType.StringType
                }
            )
        ) {
            UpdateMatakuliahView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier,
            )
        }
    }
}