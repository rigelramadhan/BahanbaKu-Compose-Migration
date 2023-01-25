package com.bahanbaku.app.ui.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bahanbaku.app.core.data.Resource
import com.bahanbaku.app.core.domain.model.Recipe
import com.bahanbaku.app.core.domain.usecase.ProfileUseCase
import com.bahanbaku.app.core.domain.usecase.RecipeUseCase
import com.bahanbaku.app.ui.common.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase,
    private val recipeUseCase: RecipeUseCase,
) : ViewModel() {

    private val _authState: MutableStateFlow<AuthState> = MutableStateFlow(AuthState.Loading())

    val authState = _authState.asStateFlow()

    private val _uiState: MutableStateFlow<Resource<List<Recipe>>> =
        MutableStateFlow(Resource.Loading())

    val uiState = _uiState.asStateFlow()

    init {
        checkToken()
    }

    private fun checkToken() {
        viewModelScope.launch {
            profileUseCase.getToken()
                .catch {

                }
                .collect {
                    if (it.length > 6) {
                        _authState.value = AuthState.Authorized(it)
                    } else {
                        _authState.value = AuthState.Unauthorized()
                    }
                }
        }
    }

    fun searchRecipe(query: String) {
        viewModelScope.launch {
            if (authState.value is AuthState.Authorized) {
                val token = authState.value.token

                if (!token.isNullOrEmpty() && token.length > 6) {
                    recipeUseCase.searchRecipe(token, query)
                        .catch {
                            _uiState.value = Resource.Error(it.message.toString())
                        }
                        .collect {
                            _uiState.value = it
                        }
                }
            }
        }
    }
}