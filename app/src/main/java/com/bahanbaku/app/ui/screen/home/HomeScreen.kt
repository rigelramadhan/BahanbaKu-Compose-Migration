package com.bahanbaku.app.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bahanbaku.app.R
import com.bahanbaku.app.core.domain.model.Categories
import com.bahanbaku.app.core.domain.model.Recipe
import com.bahanbaku.app.core.domain.model.RecipeRecommendations
import com.bahanbaku.app.core.utils.categories
import com.bahanbaku.app.core.utils.recipeList
import com.bahanbaku.app.core.utils.recipeRecommendations
import com.bahanbaku.app.ui.common.AuthState
import com.bahanbaku.app.ui.components.CategoryItem
import com.bahanbaku.app.ui.components.LoadingIndicator
import com.bahanbaku.app.ui.components.RecipeCardGridItem
import com.bahanbaku.app.ui.components.RecipeCardMediumItem
import com.bahanbaku.app.ui.theme.BahanbaKuTheme
import com.bahanbaku.app.ui.theme.GrayText
import com.bahanbaku.app.ui.theme.WhiteBackground
import com.bahanbaku.app.ui.theme.WhitePure

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (String) -> Unit,
    navigateToCategory: (String) -> Unit,
    navigateToLogin: @Composable () -> Unit,
    navigateToSearch: () -> Unit,
) {
    val authState: AuthState by viewModel.authState.collectAsState(initial = AuthState.Loading())
    val uiState: HomeViewState by viewModel.uiState.collectAsState(initial = HomeViewState())

    when (authState) {
        is AuthState.Authorized -> {
            HomeContent(
                profileName = uiState.name,
                time = "Morning",
                topRecommended = uiState.topRecommended,
                categories = uiState.categories,
                allRecipes = uiState.allRecipes,
                navigateToDetail = navigateToDetail,
                navigateToCategory = navigateToCategory,
                navigateToSearch = navigateToSearch,
            )
        }

        is AuthState.Unauthorized -> {
            navigateToLogin()
        }

        is AuthState.Loading -> {
            LoadingIndicator()
        }

        is AuthState.Error -> {
            navigateToLogin()
        }
    }
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    profileName: String,
    time: String,
    topRecommended: RecipeRecommendations,
    categories: List<Categories>,
    recommendations: List<RecipeRecommendations> = listOf(),
    allRecipes: List<Recipe>,
    navigateToDetail: (String) -> Unit,
    navigateToCategory: (String) -> Unit,
    navigateToSearch: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier.verticalScroll(
            state = scrollState, enabled = true
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = WhitePure)
                .padding(vertical = 24.dp, horizontal = 16.dp)
        ) {
            Card(
                shape = RoundedCornerShape(32.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navigateToSearch() },
                backgroundColor = WhiteBackground,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = stringResource(R.string.description_search_recipes)
                    )
                    Text(
                        text = stringResource(R.string.search_for_recipes),
                        style = MaterialTheme.typography.body1.copy(color = GrayText)
                    )
                }
            }
        }
        Text(
            text = stringResource(R.string.format_greetings).format(time, profileName),
            style = MaterialTheme.typography.h4,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        RecipeRecommendation(recipeRecommendations = topRecommended,
            navigateToDetail = navigateToDetail,
            navigateToAll = {})
        RecipeCategories(categories = categories, navigateToDetail = navigateToCategory)
        recommendations.forEach { recipeRecommendations ->
            RecipeRecommendation(
                recipeRecommendations = recipeRecommendations,
                navigateToDetail = navigateToDetail,
                navigateToAll = {},
            )
        }
        RecipeGrid(
            recipes = allRecipes,
            navigateToDetail = navigateToDetail,
        )
    }
}

@Composable
fun RecipeGrid(
    recipes: List<Recipe>,
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,
) {
    val verticalArrangement = Arrangement.spacedBy(8.dp)

    Column(modifier = modifier.padding(8.dp)) {
        Text(
            text = stringResource(R.string.discover_others),
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(8.dp),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f), verticalArrangement = verticalArrangement
            ) {
                recipes.filterIndexed { index, _ -> index % 2 == 0 }.forEach { data ->
                    RecipeCardGridItem(title = data.title,
                        description = data.description,
                        imageUrl = data.imageUrl,
                        rating = data.rating.toFloat(),
                        modifier = Modifier.clickable { navigateToDetail(data.recipeId) })
                }
            }
            Column(
                modifier = Modifier.weight(1f), verticalArrangement = verticalArrangement
            ) {
                recipes.filterIndexed { index, _ -> index % 2 != 0 }.forEach { data ->
                    RecipeCardGridItem(title = data.title,
                        description = data.description,
                        imageUrl = data.imageUrl,
                        rating = data.rating.toFloat(),
                        modifier = Modifier.clickable { navigateToDetail(data.recipeId) })
                }
            }
        }
    }
}

@Composable
fun RecipeRecommendation(
    recipeRecommendations: RecipeRecommendations,
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,
    navigateToAll: (RecipeRecommendations) -> Unit,
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.padding(start = 16.dp, end = 8.dp, top = 8.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = recipeRecommendations.title.asString(),
                style = MaterialTheme.typography.h6,
                modifier = Modifier.weight(1f)
            )
            TextButton(
                onClick = { navigateToAll(recipeRecommendations) },
            ) {
                Text(text = stringResource(R.string.all))
            }
        }
        LazyRow(
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(recipeRecommendations.recipes) { data ->
                RecipeCardMediumItem(title = data.title,
                    description = data.description,
                    imageUrl = data.imageUrl,
                    rating = data.rating.toFloat(),
                    modifier = Modifier.clickable { navigateToDetail(data.recipeId) })
            }
        }
    }
}

@Composable
fun RecipeCategories(
    categories: List<Categories>,
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.padding(start = 16.dp, end = 8.dp, top = 8.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.categories),
                style = MaterialTheme.typography.h6,
                modifier = Modifier.fillMaxWidth()
            )
        }
        LazyRow(
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(categories) { data ->
                CategoryItem(
                    title = data.title, imageUrl = data.imageUrl, modifier = Modifier.width(120.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
    BahanbaKuTheme {
        HomeContent(profileName = "Rigel",
            time = "Morning",
            topRecommended = recipeRecommendations,
            categories = categories,
            recommendations = listOf(
                recipeRecommendations, recipeRecommendations
            ),
            allRecipes = recipeList,
            navigateToDetail = {},
            navigateToCategory = {},
            navigateToSearch = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeGridPreview() {
    BahanbaKuTheme {
        RecipeGrid(
            recipes = recipeList,
            navigateToDetail = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeRecommendationPreview() {
    BahanbaKuTheme {
        RecipeRecommendation(recipeRecommendations = recipeRecommendations,
            navigateToDetail = {},
            navigateToAll = {})
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeCategoriesPreview() {
    BahanbaKuTheme {
        RecipeCategories(categories = categories, navigateToDetail = {})
    }
}
