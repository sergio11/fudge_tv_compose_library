package com.dreamsoftware.fudge.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.fudge.R

@Composable
fun FudgeTvDialog(
    isVisible: Boolean,
    modifier: Modifier = Modifier,
    @DrawableRes mainLogoRes: Int,
    @StringRes titleRes: Int? = null,
    @StringRes descriptionRes: Int? = null,
    title: String? = null,
    description: String? = null,
    @StringRes cancelRes: Int = R.string.dialog_generic_cancel_button,
    @StringRes successRes: Int = R.string.dialog_generic_accept_button,
    containerColor: Color? = null,
    contentColor: Color? = null,
    onCancelClicked: (() -> Unit)? = null,
    onAcceptClicked: (() -> Unit)? = null,
    isAcceptEnabled: Boolean = true,
    customContent: @Composable ColumnScope.() -> Unit = {}
) {
    if(isVisible) {
        Dialog(
            onDismissRequest = onCancelClicked ?: {},
            properties = DialogProperties(
                decorFitsSystemWindows = false
            )
        ) {
            CommonDialogUI(
                modifier = modifier,
                mainLogoRes = mainLogoRes,
                title = titleRes?.let { stringResource(id = it) } ?: title,
                description = descriptionRes?.let { stringResource(id = it) } ?: description,
                cancelRes = cancelRes,
                successRes = successRes,
                onCancelClicked = onCancelClicked,
                onAcceptClicked = onAcceptClicked,
                containerColor = containerColor,
                contentColor = contentColor,
                isAcceptEnabled = isAcceptEnabled,
                customContent = customContent
            )
        }
    }
}

@Composable
private fun CommonDialogUI(
    modifier: Modifier = Modifier,
    @DrawableRes mainLogoRes: Int,
    title: String? = null,
    description: String? = null,
    @StringRes cancelRes: Int,
    @StringRes successRes: Int,
    containerColor: Color? = null,
    contentColor: Color? = null,
    onCancelClicked: (() -> Unit)? = null,
    onAcceptClicked: (() -> Unit)? = null,
    isAcceptEnabled: Boolean = true,
    customContent: @Composable ColumnScope.() -> Unit = {}
) {
    with(MaterialTheme.colorScheme) {
        val isKeyboardOpen by fudgeTvKeyboardAsState()
        Surface(
            modifier = modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Transparent)
                .border(2.dp, Color.Gray, RoundedCornerShape(16.dp)),
        ) {
            Column(
                modifier = modifier.background(containerColor ?: inverseSurface),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                if(!isKeyboardOpen) {
                    Image(
                        painter = painterResource(id = mainLogoRes),
                        contentDescription = null, // decorative
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .size(150.dp)
                    )
                }
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    if(!isKeyboardOpen) {
                        FudgeTvText(
                            type = FudgeTvTextTypeEnum.TITLE_LARGE,
                            titleText = title,
                            modifier = Modifier
                                .fillMaxWidth(),
                            maxLines = 2,
                            textColor = contentColor ?: inverseOnSurface,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    FudgeTvText(
                        modifier = Modifier
                            .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                            .fillMaxWidth(),
                        type = FudgeTvTextTypeEnum.BODY_MEDIUM,
                        titleText = description,
                        textColor = contentColor ?: inverseOnSurface,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                customContent()
                Spacer(modifier = Modifier.height(30.dp))
                //.......................................................................
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    onCancelClicked?.let {
                        FudgeTvButton(
                            type = FudgeTvButtonTypeEnum.MEDIUM,
                            onClick = it,
                            textRes = cancelRes,
                            style = FudgeTvButtonStyleTypeEnum.INVERSE
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    onAcceptClicked?.let {
                        FudgeTvButton(
                            type = FudgeTvButtonTypeEnum.MEDIUM,
                            onClick = it,
                            enabled = isAcceptEnabled,
                            textRes = successRes
                        )
                    }
                }
            }
        }
    }
}