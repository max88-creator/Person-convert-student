package com.example.personconvertstudent.presentation.view_models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personconvertstudent.data.models.Person
import com.example.personconvertstudent.data.models.Student
import com.example.personconvertstudent.data.repository.PersonRepository
import com.example.personconvertstudent.dialog.DialogController
import com.example.personconvertstudent.dialog.DialogEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(
    private val repository: PersonRepository
) : ViewModel(), DialogController {
    override var dialogTitle: MutableState<String> = mutableStateOf("")
        private set
    override val editableText: MutableState<String> = mutableStateOf("")

   override var openDialog = mutableStateOf(false)
        private set
    override var showEditableText: MutableState<Boolean> = mutableStateOf(false)
        private set
    override fun onDialogEvent(event: DialogEvent) {
    }

    var specialization = MutableStateFlow("")
    val getAllPeople: StateFlow<List<Person>> =
        repository.getAllPeople()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(3000),
                initialValue = emptyList()
            )

    fun addPerson(item: Person) {
        viewModelScope.launch {
            val insertedId = repository.insertPerson(item)
            android.util.Log.d("PersonViewModel", "Inserted ID: $insertedId")
        }
    }

    fun deletePerson(id: Int) {
        viewModelScope.launch {
            repository.deletePerson(id)
        }
    }

    fun updateAge(newAge: Int, personId: Int) {
        viewModelScope.launch {
            repository.updateAge(newAge, personId)
        }
    }

    fun onEvent(event: PersonEvent) {
        when (event) {
            is PersonEvent.OnStudentClick -> {
//                selectedPerson = event.person
//                specialization = ""
                openDialog.value = true
            }

            is PersonEvent.OnDismissDialog -> {

            }

            is PersonEvent.OnSpecializationChange -> {
                specialization.value = event.specialization
            }

            is PersonEvent.OnCreateStudent -> {
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
//                    showStudentDialog = false
//                    selectedPerson = null
//                    specialization = ""
//                }
            }
        }
    }
}


