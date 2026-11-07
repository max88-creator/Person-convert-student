package com.example.personconvertstudent.utils.custom_swipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.personconvertstudent.R
import com.example.personconvertstudent.data.models.Person
import com.example.personconvertstudent.presentation.screens.person.PersonItem

@Composable
fun PersonSwipe(
    person: Person,
    onDelete: () -> Unit,
    onStudentClick: () -> Unit,
    onDecrement: () -> Unit,
    onIncrement: () -> Unit
) {
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { value ->
            value == SwipeToDismissBoxValue.EndToStart
        }
    )

    LaunchedEffect(dismissState.currentValue) {
        if (
            dismissState.currentValue ==
            SwipeToDismissBoxValue.EndToStart
            ) {
            onDelete()
        }
    }

    val progress = dismissState.progress


    SwipeToDismissBox(
        state = dismissState,
        modifier = Modifier.fillMaxWidth(),

        backgroundContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color.Red)
                    .padding(12.dp),
                contentAlignment = Alignment.CenterEnd
            ) {

                Icon(
                    painter = painterResource(R.drawable.ic_delete),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .rotate(degrees = progress * 180f)
                )
            }
        },
        content = {
            PersonItem(
                name = person.name,
                age = person.age.toString(),
                onStudentClick = onStudentClick,
                onDecrement = onDecrement,
                onIncrement = onIncrement
            )
        },
        enableDismissFromStartToEnd = false
    )
}



//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(Color.White)
//            ) {
//                Text("Name: ${person.name}")
//                Text("Age: ${person.age}")
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(1.dp)
//                        .background(Color.Gray)
//                )
//            }