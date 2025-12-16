package com.hamzaazman.knowme.ui.screen.personedit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamzaazman.knowme.domain.model.Person
import com.hamzaazman.knowme.domain.usecase.GetPersonByIdUseCase
import com.hamzaazman.knowme.domain.usecase.InsertPersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PersonEditUiState(
    val id: Int? = null,
    val firstName: String = "",
    val lastName: String = "",
    val notes: String = "",
    val isLoading: Boolean = false
)


@HiltViewModel
class PersonEditViewModel @Inject constructor(
    private val getPersonByIdUseCase: GetPersonByIdUseCase,
    private val insertPersonUseCase: InsertPersonUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(PersonEditUiState())
    val state = _state.asStateFlow()

    fun load(id: Int?) {

        id.let {
            viewModelScope.launch {
                getPersonByIdUseCase(it)?.let { person ->
                    _state.update { state ->
                        state.copy(
                            firstName = person.firstName,
                            lastName = person.lastName,
                            notes = person.notes
                        )
                    }
                }
            }
        }
    }

    fun setFirstName(value: String) {
        _state.update { it.copy(firstName = value) }
    }

    fun setLastName(value: String) {
        _state.update { it.copy(lastName = value) }
    }

    fun setNotes(value: String) {
        _state.update { it.copy(notes = value) }
    }

    fun reset() {
        _state.value = PersonEditUiState()
    }

    fun save() {
        viewModelScope.launch {
            val s = state.value

            val person = Person(
                id = s.id ?: 0,
                firstName = s.firstName,
                lastName = s.lastName,
                notes = s.notes
            )

            insertPersonUseCase(person)
        }
    }
}
