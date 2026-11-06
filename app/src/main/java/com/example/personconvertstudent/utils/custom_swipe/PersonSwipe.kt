package com.example.personconvertstudent.utils.custom_swipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            if (value == SwipeToDismissBoxValue.EndToStart) {
                onDelete()
                true
            } else {
                false
            }
        }
    )

    SwipeToDismissBox(
        state = dismissState,
        modifier = Modifier.fillMaxWidth(),

        backgroundContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red)
                    .padding(12.dp),
                contentAlignment = Alignment.CenterEnd
            ) {

                Icon(
                    painter = painterResource(R.drawable.ic_delete),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
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
        },
        enableDismissFromStartToEnd = false
    )
}
