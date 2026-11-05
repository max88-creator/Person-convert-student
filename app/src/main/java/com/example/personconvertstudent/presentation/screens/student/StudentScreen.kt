package com.example.personconvertstudent.presentation.screens.student

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.personconvertstudent.presentation.view_models.StudentViewModel

@Composable
fun StudentScreen(

    studentViewModel: StudentViewModel = hiltViewModel(),
    onPeopleListNavigate: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val students = studentViewModel.studentsWithPeople.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(55.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Student list",
                    fontSize = 19.sp
                )
            }
            Spacer(modifier = Modifier.height(55.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 9.dp)
            ) {
                items(students.value) { student ->
                    StudentItem(
                        name = student.name,
                        age = student.age,
                        specialization = student.specialization
                    )
                }
            }
        }
    }
}


