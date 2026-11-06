package com.example.personconvertstudent.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.personconvertstudent.data.models.Person
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertPerson(item: Person): Long
    @Query("DELETE FROM persons WHERE id = :id")
    suspend fun deletePerson(id: Int): Int
    @Query("SELECT * FROM persons")
    fun getAllPeople(): Flow<List<Person>>

    @Query("UPDATE persons SET age = age + 1 WHERE id = :id")
    suspend fun incrementAge(id: Int)

    @Query("UPDATE persons SET age = age - 1 WHERE id = :id")
    suspend fun decrementAge(id: Int)

    @Query("UPDATE persons SET age = :newAge WHERE id = :personId")
    suspend fun updateAge(newAge: Int, personId: Int): Int
}


