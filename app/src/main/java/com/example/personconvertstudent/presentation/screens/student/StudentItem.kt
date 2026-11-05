package com.example.personconvertstudent.presentation.screens.student

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StudentItem(
    name: String,
    age: Int,
    specialization: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(9.dp)
    ) {
        Column(

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
            }
        }
    }
}
