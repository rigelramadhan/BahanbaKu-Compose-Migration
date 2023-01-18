package com.bahanbaku.app.core.utils

import com.bahanbaku.app.core.domain.model.Categories
import com.bahanbaku.app.core.domain.model.Recipe
import com.bahanbaku.app.core.domain.model.RecipeRecommendations


const val imagePlaceholderUrl =
    "https://images.unsplash.com/photo-1504674900247-0877df9cc836?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80"

data class DummyCategories(
    val name: String,
    val img: String
)

val categories = listOf(
    Categories(
        "Traditional",
        "Indonesian",
        "https://www.indonesia.travel/content/dam/indtravelrevamp/en/trip-ideas/the-ultimate-guide-to-must-try-indonesian-food/1.jpg",
        "tradisional"
    ),
    Categories(
        "Western",
        "Western",
        "https://images.pexels.com/photos/1633578/pexels-photo-1633578.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        "western"
    ),
    Categories(
        "Dessert",
        "Dessert",
        "https://images.unsplash.com/photo-1565958011703-44f9829ba187?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=765&q=80",
        "desert"
    ),
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