package com.example.personconvertstudent.presentation.screens.person

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.personconvertstudent.R
import com.example.personconvertstudent.ui.theme.Rusty1
import com.example.personconvertstudent.ui.theme.SongName
import com.example.personconvertstudent.ui.theme.VeryDarkPrimary

@Composable
fun PersonItem(
    name: String,
    age: String,
    onStudentClick: () -> Unit,
    onDecrement: () -> Unit,
    onIncrement: () -> Unit,

) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(25.dp))
            .background(color = Rusty1.copy(alpha = 0.75f))
    ) {
        Column(

        ) {
            Spacer(modifier = Modifier.height(15.dp))
            Row() {
                Spacer(modifier = Modifier.width(19.dp))
                Text(
                    text = "Name: "
                )
                Text(
                    text = name
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(19.dp))
                Text(
                    text = "Age: "
                )
                Text(
                    text = age
                )
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
                Spacer(modifier = Modifier.width(75.dp))
                IconButton(
                    onClick = {
                        onStudentClick()
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_student),
                        contentDescription = "Create student",
                        tint = VeryDarkPrimary
                    )
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}

