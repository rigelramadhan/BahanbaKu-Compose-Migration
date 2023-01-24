package com.bahanbaku.app.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bahanbaku.app.R
import com.bahanbaku.app.core.domain.model.Categories
import com.bahanbaku.app.core.domain.model.Recipe
import com.bahanbaku.app.core.domain.model.RecipeRecommendations
import com.bahanbaku.app.core.domain.usecase.ProfileUseCase
import com.bahanbaku.app.core.domain.usecase.RecipeUseCase
import com.bahanbaku.app.core.utils.categories
import com.bahanbaku.app.ui.common.AuthState
import com.bahanbaku.app.ui.common.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase,
    private val recipeUseCase: RecipeUseCase
) : ViewModel() {

    private val _authState: MutableStateFlow<AuthState> = MutableStateFlow(AuthState.Loading())

    val authState: StateFlow<AuthState> get() = _authState

    private val _uiState: MutableStateFlow<HomeViewState> = MutableStateFlow(HomeViewState())

    val uiState: StateFlow<HomeViewState> get() = _uiState

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
                        loadRecipes(it)
                    } else {
                        _authState.value = AuthState.Unauthorized()
                    }
                }
        }
    }

    private fun loadRecipes(token: String) {
        viewModelScope.launch {
            val allRecipesFlow = recipeUseCase.getNewRecipes(token)
            val topRecommendedRecipesFlow = recipeUseCase.getRecipesByTag(token, "tradisional")
            val recommendationsFlow1 = recipeUseCase.getRecipesByTag(token, "western")
            val recommendationsFlow2 = recipeUseCase.getRecipesByTag(token, "cake")
            val profileFlow = profileUseCase.getProfile(token)
            val categories = categories

            combine(
                profileFlow,
                allRecipesFlow,
                topRecommendedRecipesFlow,
                recommendationsFlow1,
                recommendationsFlow2
            ) { profile, allRecipes, topRecommended, recommendations1, recommendations2 ->
                val name = "${profile.data?.firstName} ${profile.data?.lastName}"

                HomeViewState(
                    name = name,
                    categories = categories,
                    topRecommended = RecipeRecommendations(
                        UiText.StringResource(resId = R.string.top_recommendations),
                        topRecommended.data ?: emptyList()
                    ),
                    recommendations = listOf(
                        RecipeRecommendations(
                            UiText.StringResource(R.string.western),
                            recommendations1.data ?: emptyList()
                        ),
                        RecipeRecommendations(
                            UiText.StringResource(R.string.cake),
                            recommendations2.data ?: emptyList()
                        ),
                    ),
                    allRecipes = allRecipes.data ?: emptyList()
                )
            }.collect { homeViewState ->
                _uiState.value = homeViewState
            }
        }
    }
}

data class HomeViewState(
    val name: String = "",
    val categories: List<Categories> = emptyList(),
    val topRecommended: RecipeRecommendations = RecipeRecommendations(
        UiText.DynamicString(""),
        emptyList()
    ),
    val recommendations: List<RecipeRecommendations> = emptyList(),
    val allRecipes: List<Recipe> = emptyList(),
)
