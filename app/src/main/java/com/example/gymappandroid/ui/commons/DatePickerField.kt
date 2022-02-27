package com.example.gymappandroid.ui.commons

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun DatePickerField(
    context: Context,
    modifier: Modifier = Modifier,
    onDateChange: (String) -> Unit
) {
    val mYear: Int
    val mMonth: Int
    val mDay: Int
    val now = Calendar.getInstance()
    mYear = now.get(Calendar.YEAR)
    mMonth = now.get(Calendar.MONTH)
    mDay = now.get(Calendar.DAY_OF_MONTH)
    now.time = Date()
    val date = remember { mutableStateOf("Select birthdate(optional)") }
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date.value = formatDate(year, month + 1, dayOfMonth)
        }, mYear, mMonth, mDay
    )
    Box(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = date.value,
            onValueChange = { onDateChange(date.value) },
            leadingIcon = { Icon(imageVector = Icons.Filled.DateRange, contentDescription = null) },
            readOnly = true,
            label = { Text("Birthdate") },
            modifier = Modifier.fillMaxWidth()
        )
        Divider(
            modifier = Modifier
                .clickable { datePickerDialog.show() }
                .padding(vertical = 20.dp)
                .alpha(0f)
        )
    }
}

private fun formatDate(year: Int, month: Int, dayOfMonth: Int): String = "$dayOfMonth/$month/$year"

