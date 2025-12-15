package com.hamzaazman.knowme.ui.screen.persondetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamzaazman.knowme.domain.model.Person
import com.hamzaazman.knowme.domain.usecase.GetPersonByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PersonDetailUiState(
    val person: Person? = null
)


@HiltViewModel
class PersonDetailViewModel @Inject constructor(
    private val getPersonByIdUseCase: GetPersonByIdUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(PersonDetailUiState())
    val state = _state.asStateFlow()

    fun load(id: Int) {
        viewModelScope.launch {
            val result = getPersonByIdUseCase(id)
            _state.update {
                it.copy(person = result)
            }
        }
    }
}
