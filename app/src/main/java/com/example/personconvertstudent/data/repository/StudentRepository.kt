package com.example.personconvertstudent.data.repository

import com.example.personconvertstudent.data.models.Student
import com.example.personconvertstudent.data.models.StudentWithPerson
import kotlinx.coroutines.flow.Flow

interface StudentRepository {
    suspend fun insertStudent(item: Student): Long
    suspend fun deleteStudent(id: Int): Int
    suspend fun incrAge(id: Int)
    suspend fun decrAge(id: Int)
    fun getAllStudentsWithPeople(): Flow<List<StudentWithPerson>>
}


