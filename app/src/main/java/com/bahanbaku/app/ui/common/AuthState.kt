package com.bahanbaku.app.ui.common

sealed class AuthState(val token: String? = null, val message: String? = null) {
    class Authorized(token: String) : AuthState(token = token)
    class Loading(token: String? = null) : AuthState(token = token)
    class Unauthorized : AuthState()
    class Error(message: String?) : AuthState(message = message)
}