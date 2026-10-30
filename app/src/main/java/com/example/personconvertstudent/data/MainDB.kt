package com.example.personconvertstudent.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Person::class, Student::class],
    version = 1,
    exportSchema = false
)
abstract class MainDB: RoomDatabase() {

}