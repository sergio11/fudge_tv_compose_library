package com.dreamsoftware.fudge.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.tv.material3.MaterialTheme

@Composable
fun FudgeTvLoadingDialog(
    isShowingDialog: Boolean,
    @DrawableRes mainLogoRes: Int,
    @StringRes titleRes: Int,
    @StringRes descriptionRes: Int,
) {
    FudgeTvDialog(
        isVisible = isShowingDialog,
        mainLogoRes = mainLogoRes,
        titleRes = titleRes,
        descriptionRes = descriptionRes
    ) {
        DialogContent()
    }
}

@Composable
private fun DialogContent() {
    with(MaterialTheme.colorScheme) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center),
                color = inverseOnSurface
            )
        }
    }
}