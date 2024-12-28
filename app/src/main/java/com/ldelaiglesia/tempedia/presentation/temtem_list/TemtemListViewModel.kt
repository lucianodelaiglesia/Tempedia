package com.ldelaiglesia.tempedia.presentation.temtem_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ldelaiglesia.tempedia.common.Resource
import com.ldelaiglesia.tempedia.domain.usecases.GetTemtemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TemtemListViewModel @Inject constructor(
    private val useCase: GetTemtemUseCase
) : ViewModel() {
    private val _state = mutableStateOf(TemtemListState())
    val state: State<TemtemListState> = _state

    init {
        getTemtemList()
    }

    private fun getTemtemList() {
        useCase().onEach { result ->
            when (result) {
                is Resource.Success -> _state.value =
                    TemtemListState(temtemList = result.data ?: emptyList())

                is Resource.Loading -> _state.value = TemtemListState(isLoading = true)
                is Resource.Error -> _state.value =
                    TemtemListState(error = result.message ?: "Error desconocido")
            }
        }.launchIn(viewModelScope)
    }
}