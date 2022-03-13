package com.example.gymappandroid.ui.commons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun DateTextField(
    labelText: String,
    leadingIcon: ImageVector,
    readOnly: Boolean = false,
    isNumber: Boolean = false,
    onValueChange: (String) -> Unit,
    currentText: String,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(),
) {
    OutlinedTextField(
        colors = colors,
        value = currentText,
        enabled = !readOnly,
        onValueChange = {
            if (it.length <= 8) onValueChange(it)
        },
        label = { Text(labelText) },
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null
            )
        },
        singleLine = true,
        keyboardOptions = if (isNumber) KeyboardOptions(keyboardType = KeyboardType.Number)
        else KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        readOnly = readOnly,
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = DateTransformation()
    )
}

class DateTransformation() : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return dateFilter(text)
    }
}

fun dateFilter(text: AnnotatedString): TransformedText {
    val trimmed = if (text.text.length >= 8) text.text.substring(0..7) else text.text
    var out = ""
    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i % 2 == 1 && i < 4) out += "/"
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 1) return offset
            if (offset <= 3) return offset + 1
            if (offset <= 8) return offset + 2
            return 10
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 2) return offset
            if (offset <= 5) return offset - 1
            if (offset <= 10) return offset - 2
            return 8
        }
    }

    return TransformedText(AnnotatedString(out), numberOffsetTranslator)
}
