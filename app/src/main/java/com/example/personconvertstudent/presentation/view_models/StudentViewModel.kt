package com.example.personconvertstudent.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personconvertstudent.data.models.Student
import com.example.personconvertstudent.data.models.StudentWithPerson
import com.example.personconvertstudent.data.repository.StudentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentViewModel @Inject constructor(
    private val repository: StudentRepository
): ViewModel() {
    val studentsWithPeople: StateFlow<List<StudentWithPerson>> =
        repository.getAllStudentsWithPeople()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(3000),
                initialValue = emptyList()
            )

    fun addStudent(item: Student) {
        viewModelScope.launch {
            repository.insertStudent(item)
        }
    }

    fun deleteStudent(id: Int) {
        viewModelScope.launch {
            repository.deleteStudent(id)
        }
    }
}

