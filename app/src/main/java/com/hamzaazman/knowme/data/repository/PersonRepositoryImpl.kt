package com.hamzaazman.knowme.data.repository

import com.hamzaazman.knowme.data.dao.PersonDao
import com.hamzaazman.knowme.data.model.PersonEntity
import com.hamzaazman.knowme.domain.model.Person
import com.hamzaazman.knowme.domain.repository.PersonRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PersonRepositoryImpl @Inject constructor(
    private val dao: PersonDao
) : PersonRepository {

    override fun getPersons(): Flow<List<Person>> =
        dao.getPersons().map { list ->
            list.map { it.toDomain() }
        }

    override suspend fun getPersonById(id: Int): Person? =
        dao.getPersonById(id)?.toDomain()

    override suspend fun insertPerson(person: Person) =
        dao.insertPerson(person.toEntity())

    override suspend fun deletePerson(person: Person) =
        dao.deletePerson(person.toEntity())
}


fun PersonEntity.toDomain() = Person(id, firstName, lastName, notes)
fun Person.toEntity() = PersonEntity(id, firstName, lastName, notes)
