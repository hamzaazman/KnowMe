package com.hamzaazman.knowme.ui.screen.personlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamzaazman.knowme.domain.model.Person
import com.hamzaazman.knowme.domain.usecase.GetPersonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class PersonListUiState(
    val persons: List<Person> = emptyList()
)

@HiltViewModel
class PersonListViewModel @Inject constructor(
    private val getPersonsUseCase: GetPersonsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(PersonListUiState())
    val state = _state.asStateFlow()

    init {
        loadPersons()
    }

    private fun loadPersons() {
        viewModelScope.launch {
            getPersonsUseCase().collect { list ->
                _state.update {
                    it.copy(persons = list)
                }
            }
        }
    }
}
