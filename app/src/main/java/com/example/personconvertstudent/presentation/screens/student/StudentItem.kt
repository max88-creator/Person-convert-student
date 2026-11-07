package com.example.personconvertstudent.presentation.screens.student

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.personconvertstudent.ui.theme.SongName

@Composable
fun StudentItem(
    name: String,
    age: Int,
    specialization: String,
    onDecrement: () -> Unit,
    onIncrement: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(9.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(9.dp)
        ) {
            Text(text = "Student")
            Row(

            ) {
                Text(text = "Name: ")
                Text(text = name)
            }
            Row(

            ) {
                Text(text = "Age: ")
                Text(text = age.toString())
            }
            Row(

            ) {
                Text(text = "Specialization: ")
                Text(text = specialization)
                Spacer(modifier = Modifier.width(55.dp))
                Text(
                    text = "Decr",
                    fontSize = 17.sp,
                    fontWeight = FontWeight(800),
                    color = SongName,
                    modifier = Modifier
                        .clickable {
                            onDecrement()
                        },

                    )
                Spacer(modifier = Modifier.width(11.dp))
                Text(
                    text = "Incr",
                    fontSize = 17.sp,
                    fontWeight = FontWeight(800),
                    color = SongName,
                    modifier = Modifier
                        .clickable {
                            onIncrement()
                        }
                )
            }
        }
    }
}


