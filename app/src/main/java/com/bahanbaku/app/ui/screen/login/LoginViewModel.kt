package com.bahanbaku.app.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bahanbaku.app.core.domain.usecase.ProfileUseCase
import com.bahanbaku.app.ui.common.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<AuthState> = MutableStateFlow(AuthState.Loading())

    val uiState: StateFlow<AuthState> get() = _uiState

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
                        _uiState.value = AuthState.Authorized(it)
                    } else {
                        _uiState.value = AuthState.Unauthorized()
                    }
                }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            profileUseCase.login(email, password)
                .catch {
                    _uiState.value = AuthState.Error(it.message.toString())
                }
                .collect { response ->
                    if (response.data != null) {
                        val token = response.data.token
                        profileUseCase.saveToken(token)
                        if (token.length > 6) {
                            _uiState.value = AuthState.Authorized(token)
                        } else {
                            _uiState.value = AuthState.Error(response.message.toString())
                        }
                    } else {
                        _uiState.value = AuthState.Error(response.message.toString())
                    }
                }
        }
    }
}