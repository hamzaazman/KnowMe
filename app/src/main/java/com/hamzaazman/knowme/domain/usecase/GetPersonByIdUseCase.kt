package com.hamzaazman.knowme.domain.usecase

import com.hamzaazman.knowme.domain.repository.PersonRepository
import javax.inject.Inject


class GetPersonByIdUseCase @Inject constructor(
    private val repository: PersonRepository
) {
    suspend operator fun invoke(id: Int?) = repository.getPersonById(id)

}