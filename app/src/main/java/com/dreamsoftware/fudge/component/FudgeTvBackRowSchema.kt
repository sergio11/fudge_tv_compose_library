package com.dreamsoftware.fudge.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.Icon
import androidx.tv.material3.IconButton
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.fudge.R
import com.dreamsoftware.fudge.theme.FudgeTvTheme

@Composable
fun FudgeTvBackRowSchema(
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit = {}
) {
    with(MaterialTheme.colorScheme) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            FudgeTvText(
                type = FudgeTvTextTypeEnum.BODY_SMALL,
                textColor = onSurfaceVariant,
                titleRes = R.string.press
            )
            IconButton(
                modifier = Modifier.size(20.dp),
                colors = ButtonDefaults.colors(containerColor = onSurfaceVariant),
                onClick = onClickBack,
                scale = ButtonDefaults.scale(
                    scale = 1f,
                    focusedScale = 1.2f,
                )
            ) {
                Icon(
                    modifier = Modifier.size(12.dp),
                    painter = painterResource(R.drawable.ic_back_arrow),
                    contentDescription = "back icon",
                    tint = background
                )
            }
            FudgeTvText(
                type = FudgeTvTextTypeEnum.BODY_SMALL,
                textColor = onSurfaceVariant,
                titleRes = R.string.to_go_back
            )
        }
    }
}

@Preview
@Composable
private fun CommonBackRowSchemaPreview() {
    FudgeTvTheme {
        FudgeTvBackRowSchema()
    }
}