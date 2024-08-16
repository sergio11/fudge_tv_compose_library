package com.dreamsoftware.fudge.component

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.dreamsoftware.fudge.R

private const val DEFAULT_MAX_LINES = Int.MAX_VALUE

enum class FudgeTvTextTypeEnum {
    TITLE_LARGE,
    TITLE_MEDIUM,
    TITLE_SMALL,
    LABEL_LARGE,
    LABEL_MEDIUM,
    LABEL_SMALL,
    BODY_SMALL,
    BODY_MEDIUM,
    BODY_LARGE,
    HEADLINE_SMALL,
    HEADLINE_MEDIUM,
    HEADLINE_LARGE
}

@Composable
fun FudgeTvText(
    modifier: Modifier = Modifier,
    type: FudgeTvTextTypeEnum,
    @StringRes titleRes: Int? = null,
    titleText: String? = null,
    singleLine: Boolean = false,
    textBold: Boolean = false,
    maxLines: Int = DEFAULT_MAX_LINES,
    textColor: Color? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true
) {
    with(MaterialTheme.colorScheme) {
        CommonTextComponent(
            modifier = modifier,
            singleLine = singleLine,
            text = titleRes?.let {
                stringResource(id = it)
            } ?: titleText ?: stringResource(id = R.string.no_text_value),
            maxLines = maxLines,
            textColor = textColor ?: onPrimary,
            textAlign = textAlign,
            textBold = textBold,
            overflow = overflow,
            softWrap = softWrap,
            textStyle = with(MaterialTheme.typography) {
                when (type) {
                    FudgeTvTextTypeEnum.TITLE_LARGE -> titleLarge
                    FudgeTvTextTypeEnum.TITLE_MEDIUM -> titleMedium
                    FudgeTvTextTypeEnum.TITLE_SMALL -> titleSmall
                    FudgeTvTextTypeEnum.LABEL_LARGE -> labelLarge
                    FudgeTvTextTypeEnum.LABEL_MEDIUM -> labelMedium
                    FudgeTvTextTypeEnum.LABEL_SMALL -> labelSmall
                    FudgeTvTextTypeEnum.BODY_SMALL -> bodySmall
                    FudgeTvTextTypeEnum.BODY_MEDIUM -> bodyMedium
                    FudgeTvTextTypeEnum.BODY_LARGE -> bodyLarge
                    FudgeTvTextTypeEnum.HEADLINE_SMALL -> headlineSmall
                    FudgeTvTextTypeEnum.HEADLINE_MEDIUM -> headlineMedium
                    FudgeTvTextTypeEnum.HEADLINE_LARGE -> headlineLarge
                }
            }
        )
    }
}

@Composable
private fun CommonTextComponent(
    modifier: Modifier = Modifier,
    text: String,
    singleLine: Boolean,
    maxLines: Int,
    textColor: Color,
    textBold: Boolean,
    textAlign: TextAlign?,
    textStyle: TextStyle,
    overflow: TextOverflow,
    softWrap: Boolean
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        color = textColor,
        style = textStyle,
        overflow = overflow,
        softWrap = softWrap,
        fontWeight = if (textBold) {
            FontWeight.Bold
        } else {
            null
        },
        maxLines = if (singleLine) {
            1
        } else {
            maxLines
        }
    )
}