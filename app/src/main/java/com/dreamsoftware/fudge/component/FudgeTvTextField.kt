package com.dreamsoftware.fudge.component

import androidx.annotation.StringRes
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme

enum class FudgeTvTextFieldTypeEnum {
    NUMBER,
    NUMBER_SECRET,
    TEXT,
    PHONE,
    EMAIL,
    PASSWORD
}

@Composable
fun FudgeTvTextField(
    modifier: Modifier = Modifier,
    type: FudgeTvTextFieldTypeEnum = FudgeTvTextFieldTypeEnum.TEXT,
    value: String,
    enabled: Boolean = true,
    onValueChange: (value: String) -> Unit = {},
    @StringRes labelRes: Int,
    focusedLabelColor: Color? = null,
    unfocusedLabelColor: Color? = null,
    focusedBorderColor: Color? = null,
    unfocusedBorderColor: Color? = null,
    icon: ImageVector,
    imeAction: ImeAction = ImeAction.Next,
    onImeActionCompleted: () -> Unit = {},
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    errorMessage: String? = null
) {
    with(MaterialTheme.colorScheme) {
        val keyboard = LocalSoftwareKeyboardController.current
        val focusManager = LocalFocusManager.current
        val isError = !errorMessage.isNullOrBlank()
        Column {
            OutlinedTextField(
                interactionSource = interactionSource,
                modifier = modifier,
                value = value,
                enabled = enabled,
                singleLine = singleLine,
                maxLines = maxLines,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = focusedLabelColor ?: onPrimary,
                    unfocusedLabelColor = unfocusedLabelColor ?: if(value.isNotBlank()) {
                        onPrimary
                    } else {
                        primary
                    },
                    focusedBorderColor = focusedBorderColor ?: Color.Unspecified,
                    unfocusedBorderColor = unfocusedBorderColor ?: Color.Unspecified
                ),
                visualTransformation = if (type != FudgeTvTextFieldTypeEnum.PASSWORD && type != FudgeTvTextFieldTypeEnum.NUMBER_SECRET)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),
                onValueChange = { text ->
                    onValueChange(text)
                },
                label = {
                    Text(text = stringResource(id = labelRes))
                },
                trailingIcon = {
                    if (isError) {
                        Icon(
                            imageVector = Icons.Filled.Error,
                            contentDescription = "error",
                            tint = error
                        )
                    }
                },
                isError = isError,
                leadingIcon = {
                    Icon(
                        imageVector = icon,
                        tint = if(isError) {
                            error
                        } else {
                            primary
                        },
                        contentDescription = ""
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = when (type) {
                        FudgeTvTextFieldTypeEnum.TEXT -> KeyboardType.Text
                        FudgeTvTextFieldTypeEnum.EMAIL -> KeyboardType.Email
                        FudgeTvTextFieldTypeEnum.NUMBER -> KeyboardType.Number
                        FudgeTvTextFieldTypeEnum.PHONE -> KeyboardType.Phone
                        FudgeTvTextFieldTypeEnum.PASSWORD -> KeyboardType.Password
                        FudgeTvTextFieldTypeEnum.NUMBER_SECRET -> KeyboardType.NumberPassword
                    },
                    imeAction = imeAction
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                        onImeActionCompleted()
                    },
                    onDone = {
                        keyboard?.hide()
                        focusManager.clearFocus(true)
                        onImeActionCompleted()
                    }
                )
            )
            if (isError) {
                FudgeTvText(
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .width(250.dp),
                    type = FudgeTvTextTypeEnum.LABEL_MEDIUM,
                    titleText = errorMessage.orEmpty(),
                    textColor = error
                )
            }
        }
    }
}