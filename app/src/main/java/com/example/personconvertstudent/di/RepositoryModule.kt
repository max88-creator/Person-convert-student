package com.example.personconvertstudent.di

import com.example.personconvertstudent.data.repository.PersonRepoImpl
import com.example.personconvertstudent.data.repository.PersonRepository
import com.example.personconvertstudent.data.repository.StudentRepoImpl
import com.example.personconvertstudent.data.repository.StudentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindPersonRepository(personRepositoryImpl: PersonRepoImpl): PersonRepository

    @Binds
    @Singleton
    abstract fun bindStudentRepository(studentRepositoryImpl: StudentRepoImpl): StudentRepository
}


