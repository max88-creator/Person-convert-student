package com.example.personconvertstudent.presentation.screens.person

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.personconvertstudent.data.models.Person
import com.example.personconvertstudent.dialog.DialogEvent
import com.example.personconvertstudent.dialog.MainDialog
import com.example.personconvertstudent.presentation.view_models.PersonViewModel
import com.example.personconvertstudent.presentation.view_models.StudentViewModel

@Composable
fun PersonScreen(
    modifier: Modifier = Modifier,
    onStudentNavigateClick: () -> Unit,
    viewModel: PersonViewModel = hiltViewModel(),
) {
    val people by viewModel.getAllPeople.collectAsState()

    val studentViewModel: StudentViewModel = hiltViewModel()


    var selectedPerson by remember {
        mutableStateOf<Person?>(null)
    }



    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }



    var showStudentDialog by remember {
        mutableStateOf(false)
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Имя") }
        )

        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Возраст") }
        )
        Spacer(modifier = Modifier.height(9.dp))
        Button(
            onClick = {
                val parsedAge = age.toIntOrNull()
                if (name.isNotBlank() && parsedAge != null && parsedAge > 0) {
                    viewModel.addPerson(
                        Person(
                            name = name.trim(),
                            age = parsedAge
                        )
                    )
                    name = ""
                    age = ""
                }
            }) {
            Text("Добавить человека")
        }
        Spacer(modifier = Modifier.height(9.dp))
        Text(
            text = "Люди в базе: ${people.size}"
        )
        Spacer(modifier = Modifier.height(9.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(people)
            { person ->
                PersonItem(
                    name = person.name,
                    age = person.age.toString(),
                    onStudentClick = {
                        viewModel.onEvent(DialogEvent.OnStudentClick(
                            person = person
                        ))
                    }
                )
                Spacer(modifier = Modifier.height(9.dp))
                MainDialog(viewModel)
            }
        }
    }
}




//        Button(
//            onClick = {
//                val person = selectedPerson
//
//                if (person != null && specialization.isNotBlank()) {
//                    studentViewModel.addStudent(
//                        Student(
//                            personId = person.id,
//                            specialization = specialization.trim()
//                        )
//                    )
//
//                    selectedPerson = null
//                    specialization = ""
//                }
//            },
//            enabled = selectedPerson != null && specialization.isNotBlank()
//        ) {
//            Text("Создать студента")
//        }