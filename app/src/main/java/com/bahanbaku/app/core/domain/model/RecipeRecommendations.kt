package com.bahanbaku.app.core.domain.model

import com.bahanbaku.app.ui.common.UiText

data class RecipeRecommendations(
    val title: UiText,
    val recipes: List<Recipe>,
)
