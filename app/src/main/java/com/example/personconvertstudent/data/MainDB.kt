package com.example.personconvertstudent.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.personconvertstudent.data.models.Person
import com.example.personconvertstudent.data.models.Student

@Database(entities = [Person::class, Student::class],
    version = 1,
    exportSchema = false
)
abstract class MainDB: RoomDatabase() {

}