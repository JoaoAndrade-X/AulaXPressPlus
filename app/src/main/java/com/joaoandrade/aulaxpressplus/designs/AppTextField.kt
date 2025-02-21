package com.joaoandrade.aulaxpressplus.designs

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.joaoandrade.aulaxpressplus.dimensions.components
import com.joaoandrade.aulaxpressplus.dimensions.spacings
import com.joaoandrade.aulaxpressplus.utils.theme.AppTheme

@Composable
fun AppTextField(
    appTextFieldParams: AppTextFieldParams = AppTextFieldParams(),
    value: String,
    onValueChange: (String) -> Unit,
    placeholderStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    cursorBrush: SolidColor = SolidColor(MaterialTheme.colorScheme.onBackground),
    onFocusChanged: ((FocusState) -> Unit)? = null,
) {
    var textFieldValueState by remember { mutableStateOf(TextFieldValue(text = value)) }

    AppTextField(
        appTextFieldParams = appTextFieldParams,
        value = textFieldValueState.copy(text = value),
        onValueChange = {
            textFieldValueState = it
            onValueChange(it.text)
        },
        placeholderStyle = placeholderStyle,
        textStyle = textStyle,
        cursorBrush = cursorBrush,
        onFocusChanged = onFocusChanged,
    )
}

@Composable
fun AppTextField(
    appTextFieldParams: AppTextFieldParams = AppTextFieldParams(),
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    placeholderStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    cursorBrush: SolidColor = SolidColor(MaterialTheme.colorScheme.onBackground),
    onFocusChanged: ((FocusState) -> Unit)? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    val underlineColor =
        when {
            appTextFieldParams.error -> rossoCorsa
            isFocused -> tuftsBlue
            else -> gainsboro
        }

    Column(
        modifier =
        appTextFieldParams.modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom,
    ) {
        BasicTextField(
            textStyle =
            textStyle.copy(
                textAlign = appTextFieldParams.textAlign,
                color =
                appTextFieldParams.textColor ?: MaterialTheme.colorScheme.onBackground,
            ),
            value = value,
            onValueChange = {
                if (validateInput(it.text, appTextFieldParams)) {
                    onValueChange(it)
                }
            },
            visualTransformation = appTextFieldParams.visualTransformation,
            interactionSource = interactionSource,
            decorationBox = @Composable { innerTextField ->
                AppTextFieldDecorationBox(
                    value = value.text,
                    innerTextField = innerTextField,
                    placeholder = appTextFieldParams.placeholder,
                    placeholderStyle = placeholderStyle.copy(textAlign = appTextFieldParams.textAlign),
                    placeholderMaxLines = appTextFieldParams.placeholderMaxLines,
                    placeholderOverflow = appTextFieldParams.placeholderOverflow,
                    leadingIcon = appTextFieldParams.leadingIcon,
                    trailingIcon = appTextFieldParams.trailingIcon,
                )
            },
            cursorBrush = cursorBrush,
            enabled = appTextFieldParams.enabled,
            keyboardOptions = appTextFieldParams.keyboardOptions,
            keyboardActions = appTextFieldParams.keyboardActions,
            maxLines = appTextFieldParams.maxLines,
            singleLine = appTextFieldParams.singleLine,
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(spacings.xxxSmall)
                .background(Color.Transparent)
                .onFocusChanged { onFocusChanged?.invoke(it) },
        )
        HorizontalDivider(color = underlineColor, thickness = components.dividerThickness)
    }
}

private fun validateInput(
    value: String,
    appTextFieldParams: AppTextFieldParams,
): Boolean {
    val hasValidLength = value.length <= appTextFieldParams.maxLength
    return hasValidLength && appTextFieldParams.acceptanceRegex?.let {
        value.matches(it)
    } ?: true
}

@Composable
private fun AppTextFieldDecorationBox(
    value: String,
    innerTextField: @Composable () -> Unit,
    placeholder: String? = null,
    placeholderStyle: TextStyle,
    placeholderMaxLines: Int = Int.MAX_VALUE,
    placeholderOverflow: TextOverflow = TextOverflow.Clip,
    leadingIcon: @Composable (() -> Unit)?,
    trailingIcon: @Composable (() -> Unit)?,
) {
    Row(
        modifier =
        Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(spacings.xxxSmall),
    ) {
        leadingIcon?.invoke()
        Box(modifier = Modifier.weight(1f)) {
            if (placeholder != null && value.isEmpty()) {
                Text(
                    text = placeholder,
                    style = placeholderStyle,
                    maxLines = placeholderMaxLines,
                    overflow = placeholderOverflow,
                    color = spanishGray,
                )
            }
            innerTextField()
        }
        trailingIcon?.invoke()
    }
}

data class AppTextFieldParams(
    val modifier: Modifier = Modifier,
    val enabled: Boolean = true,
    val error: Boolean = false,
    val placeholder: String? = null,
    val textAlign: TextAlign = TextAlign.Left,
    val placeholderMaxLines: Int = Int.MAX_VALUE,
    val placeholderOverflow: TextOverflow = TextOverflow.Clip,
    val leadingIcon:
    @Composable
    (() -> Unit)? = null,
    val trailingIcon:
    @Composable
    (() -> Unit)? = null,
    val visualTransformation: VisualTransformation = VisualTransformation.None,
    val keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    val keyboardActions: KeyboardActions = KeyboardActions.Default,
    val maxLength: Int = Int.MAX_VALUE,
    val maxLines: Int = Int.MAX_VALUE,
    val singleLine: Boolean = false,
    val acceptanceRegex: Regex? = null,
    val textColor: Color? = null,
) {
    companion object {
        val ONLY_DIGITS_REGEX = "^(\\d+)?\$".toRegex()
    }
}

@Composable
@PreviewLightDark
private fun Preview() {
    AppTheme {
        Column {
            AppTextField(
                value = "",
                onValueChange = {},
                appTextFieldParams = AppTextFieldParams(placeholder = "teste"),
            )
            AppTextField(
                value = "teste",
                onValueChange = {},
                appTextFieldParams = AppTextFieldParams(placeholder = "teste"),
            )
        }
    }
}