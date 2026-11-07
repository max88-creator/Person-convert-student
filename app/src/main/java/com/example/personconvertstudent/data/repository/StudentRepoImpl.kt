package com.example.personconvertstudent.data.repository

import com.example.personconvertstudent.data.dao.StudentDao
import com.example.personconvertstudent.data.models.Student
import com.example.personconvertstudent.data.models.StudentWithPerson
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StudentRepoImpl @Inject constructor(
    private val dao: StudentDao
) : StudentRepository {
    override suspend fun insertStudent(item: Student): Long {
        return dao.insertStudent(item)
    }

    override suspend fun deleteStudent(id: Int): Int {
        return dao.deleteStudent(id)
    }

    override suspend fun incrAge(id: Int) {
        dao.incrementAge(id)
    }

    override suspend fun decrAge(id: Int) {
        dao.decrementAge(id)
    }

    override fun getAllStudentsWithPeople(): Flow<List<StudentWithPerson>> {
        return dao.getAllStudentsWithPeople()
    }
}


