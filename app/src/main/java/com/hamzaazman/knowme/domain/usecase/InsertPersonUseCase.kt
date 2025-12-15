package com.hamzaazman.knowme.domain.usecase

import com.hamzaazman.knowme.domain.model.Person
import com.hamzaazman.knowme.domain.repository.PersonRepository
import javax.inject.Inject

class InsertPersonUseCase @Inject constructor(
    private val repository: PersonRepository
) {
    suspend operator fun invoke(person: Person) = repository.insertPerson(person)
}
