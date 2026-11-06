package com.example.personconvertstudent.data.repository

import com.example.personconvertstudent.data.models.Person
import kotlinx.coroutines.flow.Flow

interface PersonRepository {
    suspend fun insertPerson(item: Person): Long
    suspend fun deletePerson(id: Int): Int
    suspend fun incrAge(id: Int)
    suspend fun decrAge(id: Int)
    fun getAllPeople(): Flow<List<Person>>
    suspend fun updateAge(newAge: Int, personId: Int): Int
}


