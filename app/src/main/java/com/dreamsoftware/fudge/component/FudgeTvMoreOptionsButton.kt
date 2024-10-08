package com.dreamsoftware.fudge.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.fudge.R
import com.dreamsoftware.fudge.theme.FudgeTvTheme

@Composable
fun FudgeTvMoreOptionsButton(
    modifier: Modifier = Modifier,
    @StringRes textRes: Int,
    @DrawableRes icon: Int,
    focusedContainerColor: Color? = null,
    containerColor: Color? = null,
    focusedContentColor: Color? = null,
    contentColor: Color? = null,
    onClick: () -> Unit = {}
) {
    with(MaterialTheme.colorScheme) {
        val interactionSource = remember { MutableInteractionSource() }
        val isFocused by interactionSource.collectIsFocusedAsState()
        Button(
            modifier = modifier.size(height = 50.dp, width = 292.dp),
            shape = ButtonDefaults.shape(shape = RoundedCornerShape(12.dp)),
            colors = ButtonDefaults.colors(
                focusedContainerColor = focusedContainerColor ?: onSurface,
                containerColor = containerColor ?: surfaceVariant.copy(0.4f),
                focusedContentColor = focusedContentColor ?: inverseOnSurface,
                contentColor = contentColor ?: onSurface
            ),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 15.dp),
            scale = ButtonDefaults.scale(scale = 1f, focusedScale = 1.1f),
            onClick = onClick,
            interactionSource = interactionSource
        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = icon),
                contentDescription = "button icon",
            )
            Spacer(modifier = Modifier.width(12.dp))
            FudgeTvText(
                type = FudgeTvTextTypeEnum.TITLE_MEDIUM,
                titleRes = textRes,
                textColor = if(isFocused) {
                    inverseOnSurface
                } else {
                    onSurface
                }
            )
        }
    }
}

@Preview
@Composable
private fun MoreOptionsButtonPreview() {
    FudgeTvTheme {
        FudgeTvMoreOptionsButton(
            textRes = R.string.retry_button_text,
            icon = R.drawable.ic_back_arrow,
        )
    }
}