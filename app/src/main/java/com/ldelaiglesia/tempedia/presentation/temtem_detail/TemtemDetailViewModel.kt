package com.ldelaiglesia.tempedia.presentation.temtem_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ldelaiglesia.tempedia.common.Constants.PARAM_TEMTEM_ID
import com.ldelaiglesia.tempedia.common.Resource
import com.ldelaiglesia.tempedia.domain.usecases.GetTemtemDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TemtemDetailViewModel @Inject constructor(
    private val useCase: GetTemtemDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(TemtemDetailState())
    val state: State<TemtemDetailState> = _state

    init {
        savedStateHandle.get<String>(PARAM_TEMTEM_ID)?.let { temtemId ->
            getTemtem(temtemId.toInt())
        }
    }

    private fun getTemtem(number: Int) {
        useCase(number).onEach { result ->
            when (result) {
                is Resource.Success -> _state.value = TemtemDetailState(temtemDetail = result.data)
                is Resource.Loading -> _state.value = TemtemDetailState(isLoading = true)
                is Resource.Error -> _state.value =
                    TemtemDetailState(error = result.message ?: "Error desconocido")
            }
        }.launchIn(viewModelScope)
    }

    fun getStatColor(statValue: Int): Color{
        return when (statValue) {
            in 0..40 -> Color.Red
            in 41..80 -> Color.Yellow
            else -> Color.Green
        }
    }
}