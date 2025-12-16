package com.hamzaazman.knowme.domain.repository

import com.hamzaazman.knowme.domain.model.Person
import kotlinx.coroutines.flow.Flow

interface PersonRepository {
    fun getPersons(): Flow<List<Person>>
    suspend fun getPersonById(id: Int?): Person?
    suspend fun insertPerson(person: Person)
    suspend fun deletePerson(person: Person)
}
