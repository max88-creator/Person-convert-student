package com.example.personconvertstudent.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.personconvertstudent.data.models.Student
import com.example.personconvertstudent.data.models.StudentWithPerson
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(item: Student): Long
    @Query("DELETE FROM students WHERE personId = :id")
    suspend fun deleteStudent(id: Int): Int

    @Query("UPDATE persons SET age = age + 1 WHERE id = :id")
    suspend fun incrementAge(id: Int)

    @Query("UPDATE persons SET age = age - 1 WHERE id = :id")
    suspend fun decrementAge(id: Int)
    @Query(
        "SELECT persons.id AS personId, " +
                "persons.name, persons.age, " +
                "students.specialization " +
                "FROM students INNER JOIN persons\n" +
                "    ON students.personId = persons.id"
    )
    fun getAllStudentsWithPeople(): Flow<List<StudentWithPerson>>
}