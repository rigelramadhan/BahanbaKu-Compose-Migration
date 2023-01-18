package com.bahanbaku.app.ui.screen.home

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bahanbaku.app.core.data.Resource
import com.bahanbaku.app.core.domain.model.Recipe
import com.bahanbaku.app.core.domain.usecase.ProfileUseCase
import com.bahanbaku.app.core.domain.usecase.RecipeUseCase
import com.bahanbaku.app.ui.common.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase,
    private val recipeUseCase: RecipeUseCase
) : ViewModel() {
    private val _authState: MutableStateFlow<AuthState> = MutableStateFlow(AuthState.Loading())

    val authState: StateFlow<AuthState> get() = _authState

    fun getRecipes(token: String) =
        LiveDataReactiveStreams.fromPublisher(recipeUseCase.getNewRecipes(token))

    fun getRecipesByTag(token: String, tag: String) =
        LiveDataReactiveStreams.fromPublisher(recipeUseCase.getRecipesByTag(token, tag))

    init {
        loadToken()
    }

    private fun loadToken() {
        viewModelScope.launch {
            profileUseCase.getToken()
                .catch {
                    _authState.value = AuthState.Error(it.message.toString())
                }
                .collect { token ->
                    if (token != "null") {
                        _authState.value = AuthState.Authorized(token)
                    } else {
                        _authState.value = AuthState.Unauthorized()
                    }
                }
        }
    }
}