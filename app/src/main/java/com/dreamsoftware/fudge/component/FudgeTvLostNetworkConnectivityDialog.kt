package com.dreamsoftware.fudge.component

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import com.dreamsoftware.fudge.R

@Composable
fun FudgeTvLostNetworkConnectivityDialog(
    isVisible: Boolean,
    @DrawableRes mainLogoRes: Int,
    onOpenSettings: () -> Unit,
    onRestartAppPressed: () -> Unit
) {
    FudgeTvDialog(
        isVisible = isVisible,
        mainLogoRes = mainLogoRes,
        titleRes = R.string.generic_lost_network_connectivity_dialog_title,
        descriptionRes = R.string.generic_lost_network_connectivity_dialog_description,
        cancelRes = R.string.generic_lost_network_connectivity_dialog_open_settings_button_text,
        successRes = R.string.generic_lost_network_connectivity_dialog_restart_app_button_text,
        onCancelClicked = onOpenSettings,
        onAcceptClicked = onRestartAppPressed
    )
}