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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.personconvertstudent.R
import com.example.personconvertstudent.presentation.screens.student.StudentItem

@Composable
fun StudentSwipe(
    name: String,
    age: Int,
    specialization: String,
    onDelete: () -> Unit,
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
            StudentItem(
                name = name,
                age = age,
                specialization = specialization,
                onDecrement = onDecrement,
                onIncrement = onIncrement,
            )
        },
        enableDismissFromStartToEnd = false
    )
}


