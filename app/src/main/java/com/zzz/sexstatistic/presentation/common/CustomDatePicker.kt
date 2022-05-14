package com.zzz.sexstatistic.presentation.common

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import java.time.LocalDate
import java.util.Calendar
import java.util.Date

@Composable
fun CustomDatePicker(
  date: LocalDate?,
  setDate: (newDate: LocalDate) -> Unit,
) {
  val context = LocalContext.current

  val calendar = Calendar.getInstance()

  val year = calendar.get(Calendar.YEAR)
  val month = calendar.get(Calendar.MONTH)
  val day = calendar.get(Calendar.DAY_OF_MONTH)

  calendar.time = Date()

  val datePickerDialog = DatePickerDialog(
    context,
    { _: DatePicker, newYear: Int, newMonth: Int, newDay: Int ->
      setDate(LocalDate.of(newYear, newMonth, newDay))
    },
    year,
    month,
    day,
  )

  if (date != null) {
    Button(
      onClick = { datePickerDialog.show() },
      colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFF0F9D58))
    ) {
      Text(text = date.toString(), color = Color.White)
    }
  }
}