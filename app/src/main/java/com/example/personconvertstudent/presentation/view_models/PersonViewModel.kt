package com.example.personconvertstudent.presentation.view_models

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personconvertstudent.data.models.Person
import com.example.personconvertstudent.data.models.Student
import com.example.personconvertstudent.data.repository.PersonRepository
import com.example.personconvertstudent.data.repository.StudentRepository
import com.example.personconvertstudent.dialog.DialogController
import com.example.personconvertstudent.dialog.DialogEvent
import com.example.personconvertstudent.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(
    private val repository: PersonRepository,
    private val studentRepository: StudentRepository
) : ViewModel(), DialogController {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    private var selectedPerson: Person? = null

    override var dialogTitle: MutableState<String> = mutableStateOf("")
        private set
    override val editableText: MutableState<String> = mutableStateOf("")

    override var openDialog = mutableStateOf(false)
        private set
    override var showEditableText: MutableState<Boolean> = mutableStateOf(false)
        private set


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
            Log.d("PersonViewModel", "Inserted ID: $insertedId")
        }
    }

    fun incrAge(id: Int) {
        viewModelScope.launch {
            repository.incrAge(id)
        }
    }

    fun decrAge(id: Int) {
        viewModelScope.launch {
            repository.decrAge(id)
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

   override fun onDialogEvent(event: DialogEvent) {
        when (event) {
            is DialogEvent.OnStudentClick -> {
                selectedPerson = event.person
                editableText.value = ""
                dialogTitle.value = "Создать студента для ${event.person.name}"
                openDialog.value = true
            }

            is DialogEvent.OnCancel -> {
                openDialog.value = false
            }

            is DialogEvent.OnTextChange -> {
                editableText.value = event.text
            }

            is DialogEvent.OnConfirm -> {
                val person = selectedPerson
                val specialization = editableText.value.trim()
                if (editableText.value.isNotEmpty()) {
                    viewModelScope.launch {
                        studentRepository.insertStudent(
                            item = Student(
                                personId = person?.id ?: 0,
                                specialization = specialization
                            )
                        )
                    }
                }
                selectedPerson = null
                editableText.value = ""
                openDialog.value = false
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}


