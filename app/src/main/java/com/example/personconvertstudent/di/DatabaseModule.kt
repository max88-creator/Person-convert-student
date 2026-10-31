package com.example.personconvertstudent.di

import android.app.Application
import androidx.room.Room
import com.example.personconvertstudent.data.MainDB
import com.example.personconvertstudent.data.dao.PersonDao
import com.example.personconvertstudent.data.dao.StudentDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideMainDb(app: Application): MainDB {
        return Room.databaseBuilder(
            context = app,
            klass = MainDB::class.java,
            name = "main_db"
        )
            .build()
    }

    @Provides
    fun providePersonDao(mainDB: MainDB): PersonDao {
        return mainDB.personDao()
    }

    @Provides
    fun provideStudentDao(mainDB: MainDB): StudentDao {
        return mainDB.studentDao()
    }
}