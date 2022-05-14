package com.zzz.sexstatistic.presentation.common

import android.app.TimePickerDialog
import android.widget.TimePicker
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import java.time.LocalTime
import java.util.Calendar
import java.util.Date

@Composable
fun CustomTimePicker(
  time: LocalTime?,
  setTime: (newTime: LocalTime) -> Unit,
) {
  val context = LocalContext.current

  val calendar = Calendar.getInstance()

  val hour = calendar.get(Calendar.HOUR_OF_DAY)
  val minute = calendar.get(Calendar.MINUTE)

  calendar.time = Date()

  val timePickerDialog = TimePickerDialog(
    context,
    { _: TimePicker, newHour: Int, newMinute: Int ->
      setTime(LocalTime.of(newHour, newMinute))
    },
    hour,
    minute,
    true,
  )

  if (time != null) {
    Button(
      onClick = { timePickerDialog.show() },
      colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFF0F9D58))
    ) {
      Text(text = time.toString(), color = Color.White)
    }
  }
}