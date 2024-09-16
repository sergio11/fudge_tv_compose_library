package com.dreamsoftware.fudge.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.fudge.theme.Dimens.DEFAULT_BUTTON_LARGE_HEIGHT
import com.dreamsoftware.fudge.theme.Dimens.DEFAULT_BUTTON_LARGE_WIDTH
import com.dreamsoftware.fudge.theme.Dimens.DEFAULT_BUTTON_MEDIUM_HEIGHT
import com.dreamsoftware.fudge.theme.Dimens.DEFAULT_BUTTON_MEDIUM_WIDTH
import com.dreamsoftware.fudge.theme.Dimens.DEFAULT_BUTTON_SMALL_HEIGHT
import com.dreamsoftware.fudge.theme.Dimens.DEFAULT_BUTTON_SMALL_WIDTH

enum class FudgeTvButtonTypeEnum {
    SMALL,
    MEDIUM,
    LARGE
}

enum class FudgeTvButtonStyleTypeEnum {
    NORMAL,
    INVERSE,
    TRANSPARENT
}

@Composable
fun FudgeTvButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    type: FudgeTvButtonTypeEnum = FudgeTvButtonTypeEnum.MEDIUM,
    style: FudgeTvButtonStyleTypeEnum = FudgeTvButtonStyleTypeEnum.NORMAL,
    enableBorder: Boolean = true,
    buttonShape: Shape = RoundedCornerShape(percent = 20),
    borderColor: Color? = null,
    containerColor: Color? = null,
    contentColor: Color? = null,
    text: String? = null,
    textRes: Int? = null,
    onClick: () -> Unit,
) {
    with(MaterialTheme.colorScheme) {
        Button(
            onClick = onClick,
            modifier = modifier.then(
                Modifier
                    .width(
                        when (type) {
                            FudgeTvButtonTypeEnum.SMALL -> DEFAULT_BUTTON_SMALL_WIDTH
                            FudgeTvButtonTypeEnum.MEDIUM -> DEFAULT_BUTTON_MEDIUM_WIDTH
                            FudgeTvButtonTypeEnum.LARGE -> DEFAULT_BUTTON_LARGE_WIDTH
                        }
                    )
                    .height(
                        when (type) {
                            FudgeTvButtonTypeEnum.SMALL -> DEFAULT_BUTTON_SMALL_HEIGHT
                            FudgeTvButtonTypeEnum.MEDIUM -> DEFAULT_BUTTON_MEDIUM_HEIGHT
                            FudgeTvButtonTypeEnum.LARGE -> DEFAULT_BUTTON_LARGE_HEIGHT
                        }
                    )
                    .clip(buttonShape)
                    .border(
                        width = if (enableBorder) {
                            2.dp
                        } else {
                            0.dp
                        },
                        color = if(enableBorder){
                            borderColor ?: when(style) {
                                FudgeTvButtonStyleTypeEnum.NORMAL -> onPrimaryContainer
                                FudgeTvButtonStyleTypeEnum.INVERSE -> onSecondaryContainer
                                FudgeTvButtonStyleTypeEnum.TRANSPARENT -> onTertiaryContainer
                            }
                        } else {
                            Color.Transparent
                        },
                        shape = buttonShape
                    )
            ),
            enabled = enabled,
            shape = ButtonDefaults.shape(shape = buttonShape),
            colors = ButtonDefaults.colors(
                containerColor = containerColor ?: when(style) {
                    FudgeTvButtonStyleTypeEnum.NORMAL -> if (enableBorder) {
                        primaryContainer.copy(alpha = 0.8f)
                    } else {
                        primaryContainer
                    }
                    FudgeTvButtonStyleTypeEnum.INVERSE -> if (enableBorder) {
                        secondaryContainer.copy(alpha = 0.8f)
                    } else {
                        secondaryContainer
                    }
                    FudgeTvButtonStyleTypeEnum.TRANSPARENT -> Color.Transparent
                },
                contentColor = contentColor ?: when(style) {
                    FudgeTvButtonStyleTypeEnum.NORMAL -> onPrimaryContainer
                    FudgeTvButtonStyleTypeEnum.INVERSE -> onSecondaryContainer
                    FudgeTvButtonStyleTypeEnum.TRANSPARENT -> onTertiaryContainer
                },
                focusedContainerColor = when(style) {
                    FudgeTvButtonStyleTypeEnum.NORMAL -> primary.copy(alpha = 0.8f)
                    FudgeTvButtonStyleTypeEnum.INVERSE ->  primary.copy(alpha = 0.8f)
                    FudgeTvButtonStyleTypeEnum.TRANSPARENT -> primary.copy(alpha = 0.8f)
                }
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                FudgeTvText(
                    type = when(type) {
                        FudgeTvButtonTypeEnum.SMALL -> FudgeTvTextTypeEnum.BODY_SMALL
                        FudgeTvButtonTypeEnum.MEDIUM -> FudgeTvTextTypeEnum.BODY_MEDIUM
                        FudgeTvButtonTypeEnum.LARGE -> FudgeTvTextTypeEnum.BODY_LARGE
                    },
                    titleText = text,
                    titleRes = textRes,
                    textColor = contentColor ?: when(style) {
                        FudgeTvButtonStyleTypeEnum.NORMAL -> onPrimaryContainer
                        FudgeTvButtonStyleTypeEnum.INVERSE -> onSecondaryContainer
                        FudgeTvButtonStyleTypeEnum.TRANSPARENT -> onTertiaryContainer
                    },
                    textAlign = TextAlign.Center,
                    textBold = true
                )
            }
        }
    }
}