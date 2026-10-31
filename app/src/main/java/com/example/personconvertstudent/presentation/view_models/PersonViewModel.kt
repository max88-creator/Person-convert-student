package com.example.personconvertstudent.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personconvertstudent.data.models.Person
import com.example.personconvertstudent.data.repository.PersonRepository
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
): ViewModel() {


    val getAllPeople: StateFlow<List<Person>> =
        repository.getAllPeople()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(3000),
                initialValue = emptyList()
            )

    fun addPerson(item: Person) {
        viewModelScope.launch {
            repository.insertPerson(item)
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
}


