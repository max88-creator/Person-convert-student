package com.example.personconvertstudent.data.repository

import com.example.personconvertstudent.data.dao.PersonDao
import com.example.personconvertstudent.data.models.Person
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PersonRepoImpl @Inject constructor(
    private val dao: PersonDao
) : PersonRepository {
    override suspend fun insertPerson(item: Person): Long {
        return dao.insertPerson(item)
    }

    override suspend fun deletePerson(id: Int): Int {
        return dao.deletePerson(id)
    }

    override suspend fun incrAge(id: Int) {
        dao.incrementAge(id)
    }

    override suspend fun decrAge(id: Int) {
        dao.decrementAge(id)
    }

    override fun getAllPeople(): Flow<List<Person>> {
        return dao.getAllPeople()
    }

    override suspend fun updateAge(newAge: Int, personId: Int): Int {
        return dao.updateAge(newAge, personId)
    }
}


