package com.dreamsoftware.fudge.component.player

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.ButtonBorder
import androidx.tv.material3.IconButtonDefaults
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.fudge.R
import com.dreamsoftware.fudge.component.FudgeTvFillIconButton
import com.dreamsoftware.fudge.theme.FudgeTvTheme

@Composable
fun FudgeTvPlayerControlsIcon(
    modifier: Modifier = Modifier,
    icon: Int,
    border: ButtonBorder,
    buttonColor: Color,
    focusedButtonColor: Color? = null,
    iconColor: Color = Color.Gray,
    size: Dp = 40.dp,
    interactionSource: MutableInteractionSource? = null,
    onClick: () -> Unit
) {
    FudgeTvFillIconButton(
        modifier = modifier.size(size),
        onClick = onClick,
        icon = icon,
        buttonColor = buttonColor,
        focusedButtonColor = focusedButtonColor,
        iconColor = iconColor,
        interactionSource = interactionSource,
        border = border
    )
}


@Preview(device = Devices.TV_1080p)
@Composable
fun PreviewPlayerControlsIcon() {
    FudgeTvTheme {
        FudgeTvPlayerControlsIcon(
            modifier = Modifier.size(40.dp),
            icon = R.drawable.play_icon,
            border = IconButtonDefaults.border(
                border = Border(
                    border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.border),
                    shape = CircleShape
                )
            ),
            buttonColor = Color.Transparent,
            iconColor = Color.Gray
        ) {}
    }
}