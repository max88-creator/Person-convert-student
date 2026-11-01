package com.example.personconvertstudent.presentation.screens.person

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.example.personconvertstudent.data.models.Student
import com.example.personconvertstudent.presentation.view_models.PersonEvent
import com.example.personconvertstudent.presentation.view_models.PersonViewModel
import com.example.personconvertstudent.presentation.view_models.StudentViewModel

@Composable
fun PersonScreen(
    modifier: Modifier = Modifier,
    viewModel: PersonViewModel = hiltViewModel(),
) {
    val people by viewModel.getAllPeople.collectAsState()

    val studentViewModel: StudentViewModel = hiltViewModel()


    var selectedPerson by remember {
        mutableStateOf<Person?>(null)
    }

    var specialization by remember {
        mutableStateOf("")
    }

    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }



    var showStudentDialog by remember {
        mutableStateOf(false)
    }

    fun onEvent(event: PersonEvent) {
        when (event) {
            is PersonEvent.OnStudentClick -> {
                selectedPerson = event.person
                specialization = ""
                showStudentDialog = true
            }

            is PersonEvent.OnDismissDialog -> {
                showStudentDialog = false
            }

            is PersonEvent.OnSpecializationChange -> {
                specialization = event.specialization
            }

            is PersonEvent.OnCreateStudent -> {
                val person = selectedPerson

                if (person != null && specialization.isNotBlank()) {
                    studentViewModel.addStudent(
                        Student(
                            personId = person.id,
                            specialization = specialization.trim()
                        )
                    )

                    showStudentDialog = false
                    selectedPerson = null
                    specialization = ""
                }
            }
        }
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
                        onEvent(PersonEvent.OnStudentClick(person))
                    }
                )
                Spacer(modifier = Modifier.height(9.dp))
            }
        }
    }
    if (showStudentDialog) {
        AlertDialog(
            onDismissRequest = {
                onEvent(PersonEvent.OnDismissDialog)
            },
            title = {
                Text("Создать студента")
            },
            text = {
                Column {
                    Text("Человек: ${selectedPerson?.name.orEmpty()}")

                    OutlinedTextField(
                        value = specialization,
                        onValueChange = {
                            onEvent(
                                PersonEvent.OnSpecializationChange(it)
                            )
                        },
                        label = {
                            Text("Специализация")
                        }
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onEvent(PersonEvent.OnCreateStudent)
                    },
                    enabled = selectedPerson != null &&
                            specialization.isNotBlank()
                ) {
                    Text("Создать")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onEvent(PersonEvent.OnDismissDialog)
                    }
                ) {
                    Text("Отмена")
                }
            }
        )
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