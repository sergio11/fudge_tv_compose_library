package com.dreamsoftware.fudge.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.tv.material3.ButtonBorder
import androidx.tv.material3.Icon
import androidx.tv.material3.IconButton
import androidx.tv.material3.IconButtonDefaults
import com.dreamsoftware.fudge.R

@Composable
fun FudgeTvFillIconButton(
    modifier: Modifier = Modifier,
    icon: Int,
    onClick: () -> Unit,
    border: ButtonBorder = IconButtonDefaults.border(),
    buttonColor: Color = Color.White,
    iconColor: Color = Color.Gray,
    interactionSource: MutableInteractionSource? = null,
) {
    IconButton(
        onClick = { onClick() },
        modifier = modifier,
        border = border,
        colors = IconButtonDefaults.colors(
            buttonColor
        ),
        interactionSource = interactionSource
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "icon",
            tint = iconColor
        )
    }
}

@Preview
@Composable
fun PreviewCommonFillIconButton() {
    FudgeTvFillIconButton(icon = R.drawable.ic_back_arrow, onClick = { /*TODO*/ })
}