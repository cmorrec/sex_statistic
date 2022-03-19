package com.zzz.sexstatistic.presentation.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zzz.sexstatistic.infrastructure.database.model.Sex
import com.zzz.sexstatistic.infrastructure.database.model.SexPerson
import com.zzz.sexstatistic.presentation.theme.SexStatisticTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun SexCard(sex: Sex) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = sex.startDate.format(DateTimeFormatter.ISO_TIME),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = getDuration(sex.duration),
                fontSize = 18.sp,
            )
            Text(
                text = sex.place,
                fontSize = 18.sp,
            )
        }
        Text(
            text = getRating(sex.rating),
            modifier = Modifier
                .height(60.dp)
                .width(60.dp)
                .padding(0.dp, 8.dp, 0.dp, 0.dp)
//                .background(
//                    shape = CircleShape,
//                    color = MaterialTheme.colors.secondary,
//                )
                .border(border = BorderStroke(1.dp, Color.Black), shape = CircleShape),
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
        )
        Icon(
            imageVector = Icons.Filled.Person,
            contentDescription = sex.id,
            modifier = Modifier.size(64.dp),
        )
    }
}

fun getDuration(minutes: Int): String {
    return "$minutes minutes"
}

fun getRating(rating: Double?): String {
    if (rating == null) return "?"
    return rating.toString()
}

@Preview(showBackground = true)
@Composable
fun DefaultSexCardPreview() {
    SexStatisticTheme {
        SexCard(
            Sex(
                id = "3",
                duration = 75,
                place = "Стол",
                startDate = LocalDateTime.of(2022, 3, 20, 21, 30),
                sexPersons = listOf(
                    SexPerson(person = person1, rating = 10.0),
                    SexPerson(person = person2, rating = 9.5),
                ),
                rating = 9.75,
            )
        )
    }
}
