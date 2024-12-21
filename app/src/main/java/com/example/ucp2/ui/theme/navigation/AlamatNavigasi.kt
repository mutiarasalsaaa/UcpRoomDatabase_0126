package com.example.ucp2.ui.theme.navigation

import com.example.ucp2.ui.theme.navigation.DestinasiDetailMatakuliah.KODE

interface AlamatNavigasi {
    val route: String
}

object DestinasiHomeMenu : AlamatNavigasi {
    override val route = "home_menu"
}

object DestinasiHomeMatakuliah: AlamatNavigasi {
    override val route = "home_matakuliah"
}

object DestinasiHomeDosen : AlamatNavigasi {
    override val route = "home_dosen"
}

object DestinasiDetailDosen : AlamatNavigasi {
    override val route = "detail_dosen"
    const val NIDN = "nidn"
    val routesWithArg = "$route/{$NIDN}"
}

object DestinasiDetailMatakuliah : AlamatNavigasi {
    override val route = "detail_matakuliah"
    const val KODE = "kode"
    val routesWithArg = "$route/{$KODE}"
}

object DestinasiUpdateMatakuliah : AlamatNavigasi {
    override val route = "update"
    const val KODE = "kode"
    val routesWithArg = "$route/{$KODE}"
}
