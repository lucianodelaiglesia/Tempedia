package com.ldelaiglesia.tempedia.presentation.temtem_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ldelaiglesia.tempedia.common.Constants.PARAM_TEMTEM_ID
import com.ldelaiglesia.tempedia.common.Resource
import com.ldelaiglesia.tempedia.domain.usecases.GetTechniqueDetailUseCase
import com.ldelaiglesia.tempedia.domain.usecases.GetTemtemDetailUseCase
import com.ldelaiglesia.tempedia.presentation.ui.theme.StatHigh
import com.ldelaiglesia.tempedia.presentation.ui.theme.StatLow
import com.ldelaiglesia.tempedia.presentation.ui.theme.StatMid
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TemtemDetailViewModel @Inject constructor(
    private val getTemtemDetailUseCase: GetTemtemDetailUseCase,
    private val getTechniqueDetailUseCase: GetTechniqueDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _temtemDetailState = mutableStateOf(TemtemDetailState())
    val temtemDetailState: State<TemtemDetailState> = _temtemDetailState

    private val _techniqueDetailsState = mutableStateOf(TechniqueDetailState())
    val techniqueDetailState: State<TechniqueDetailState> = _techniqueDetailsState

    private val _portraitUrls = mutableStateOf<Map<Int, String>>(emptyMap())
    val portraitUrls: State<Map<Int, String>> = _portraitUrls

    private val _evolutionTypes = mutableStateOf<List<String>>(emptyList())
    val evolutionTypes: State<List<String>> = _evolutionTypes

    init {
        savedStateHandle.get<String>(PARAM_TEMTEM_ID)?.let { temtemId ->
            getTemtem(temtemId.toInt())
        }
    }

    private fun getTemtem(number: Int) {
        getTemtemDetailUseCase(number).onEach { result ->
            when (result) {
                is Resource.Success -> _temtemDetailState.value =
                    TemtemDetailState(data = result.data)

                is Resource.Loading -> _temtemDetailState.value =
                    TemtemDetailState(isLoading = true)

                is Resource.Error -> _temtemDetailState.value =
                    TemtemDetailState(error = result.message ?: "Error desconocido")
            }
        }.launchIn(viewModelScope)
    }

    fun getStatColor(statValue: Int): Color {
        return when (statValue) {
            in 0..40 -> StatLow
            in 41..80 -> StatMid
            else -> StatHigh
        }
    }

    fun getTechniqueDetails(name: String) {
        viewModelScope.launch {
            getTechniqueDetailUseCase(name).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _techniqueDetailsState.value =
                            TechniqueDetailState(data = result.data)
                    }

                    is Resource.Loading -> {
                        _techniqueDetailsState.value = TechniqueDetailState(isLoading = true)
                    }

                    is Resource.Error -> {
                        _techniqueDetailsState.value = TechniqueDetailState(
                            error = result.message ?: "Unknown error"
                        )
                    }
                }
            }
        }
    }

    fun getTemtemPortrait(number: Int) {
        getTemtemDetailUseCase(number).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    result.data?.let { temtemDetail ->
                        _portraitUrls.value += (temtemDetail.number to temtemDetail.portraitList)
                    }
                }

                is Resource.Loading -> {
                    _temtemDetailState.value = _temtemDetailState.value.copy(
                        portraitLoadingState = true,
                        portraitError = ""
                    )
                }

                is Resource.Error -> {
                    _temtemDetailState.value = _temtemDetailState.value.copy(
                        portraitLoadingState = false,
                        portraitError = result.message ?: "Unknown error"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getEvolutionTypes(number: Int) {
        getTemtemDetailUseCase(number).onEach { result ->
            when (result){
                is Resource.Success -> {
                    result.data?.let { temtemDetail ->
                        _evolutionTypes.value = temtemDetail.types
                    }
                }
                is Resource.Loading -> {
                    _temtemDetailState.value = _temtemDetailState.value.copy(
                        evolutionTypesLoadingState = true,
                        evolutionTypesError = ""
                    )
                }
                is Resource.Error -> {
                    _temtemDetailState.value = _temtemDetailState.value.copy(
                        evolutionTypesLoadingState = false,
                        evolutionTypesError = result.message ?: "Unknown error"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}