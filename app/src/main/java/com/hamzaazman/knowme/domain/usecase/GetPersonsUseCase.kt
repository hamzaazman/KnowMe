package com.hamzaazman.knowme.domain.usecase

import com.hamzaazman.knowme.domain.repository.PersonRepository
import jakarta.inject.Inject

class GetPersonsUseCase @Inject constructor(
    private val repository: PersonRepository
) {
    operator fun invoke() = repository.getPersons()
}
