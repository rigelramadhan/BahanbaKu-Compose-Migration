package com.bahanbaku.app.core.utils

import com.bahanbaku.app.core.domain.model.Recipe
import com.bahanbaku.app.core.domain.model.RecipeRecommendations


const val imagePlaceholderUrl =
    "https://images.unsplash.com/photo-1504674900247-0877df9cc836?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80"

data class DummyCategories(
    val name: String,
    val img: String
)

val categories = listOf(
    DummyCategories("Healthy", "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=880&q=80"),
    DummyCategories("Dessert", "https://images.unsplash.com/photo-1565958011703-44f9829ba187?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=765&q=80"),
    DummyCategories("Meat", "https://images.unsplash.com/photo-1555939594-58d7cb561ad1?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80"),
    DummyCategories("Chicken", "https://images.unsplash.com/photo-1626645738196-c2a7c87a8f58?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80"),
    DummyCategories("Seafood", "https://images.unsplash.com/photo-1576330383200-2bf325cfec52?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=735&q=80"),
    DummyCategories("Asian", "https://images.unsplash.com/photo-1496116218417-1a781b1c416c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80"),
)

val recipe1 = Recipe(
    "",
    "",
    "",
    3,
    4.5,
    "This is the description of the food. Every bit of information is put here so that users can now what describes the food.",
    4,
    "Bakso Buatan Mama yang Paling Enak Sedunia",
    "",
    ""
)
val recipe2 = Recipe(
    "",
    "",
    "",
    3,
    4.5,
    "This is the description of the food.",
    4,
    "Bakso",
    "",
    ""
)
val recipeList = listOf(recipe1, recipe2, recipe2, recipe1, recipe1)

val recipeRecommendations = RecipeRecommendations(
    title = "Recommendation Title",
    recipes = recipeList,
)