package com.bahanbaku.app.ui.screen.home

import android.widget.Space
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bahanbaku.app.R
import com.bahanbaku.app.core.domain.model.Recipe
import com.bahanbaku.app.core.domain.model.RecipeRecommendations
import com.bahanbaku.app.core.utils.recipeList
import com.bahanbaku.app.core.utils.recipeRecommendations
import com.bahanbaku.app.ui.components.RecipeCardGridItem
import com.bahanbaku.app.ui.components.RecipeCardMediumItem
import com.bahanbaku.app.ui.theme.BahanbaKuTheme

@Composable
fun HomeScreen() {

}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    topRecommended: RecipeRecommendations,
    recommendations: List<RecipeRecommendations>,
    allRecipes: List<Recipe>,
    navigateToDetail: (String) -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .verticalScroll(
                state = scrollState,
                enabled = true
            ),
    ) {
        RecipeRecommendation(
            recipeRecommendations = topRecommended,
            navigateToDetail = navigateToDetail,
            navigateToAll = {}
        )
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecipeGrid(
    recipes: List<Recipe>,
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,
) {
    val verticalArrangement = Arrangement.spacedBy(8.dp)

    Column(modifier = modifier.padding(8.dp)) {
        Text(
            text = recipeRecommendations.title,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(8.dp),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = verticalArrangement
            ) {
                recipes.filterIndexed { index, _ -> index % 2 == 0 }.forEach { data ->
                    RecipeCardGridItem(
                        title = data.title,
                        description = data.description,
                        imageUrl = data.imageUrl,
                        rating = data.rating.toFloat(),
                        modifier = Modifier.clickable { navigateToDetail(data.recipeId) }
                    )
                }
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = verticalArrangement
            ) {
                recipes.filterIndexed { index, _ -> index % 2 != 0 }.forEach { data ->
                    RecipeCardGridItem(
                        title = data.title,
                        description = data.description,
                        imageUrl = data.imageUrl,
                        rating = data.rating.toFloat(),
                        modifier = Modifier.clickable { navigateToDetail(data.recipeId) }
                    )
                }
            }
        }
    }

//    LazyVerticalStaggeredGrid(
//        columns = StaggeredGridCells.Adaptive(145.dp),
//        verticalArrangement = Arrangement.spacedBy(8.dp),
//        horizontalArrangement = Arrangement.spacedBy(8.dp),
//        contentPadding = PaddingValues(8.dp),
//        modifier = modifier
//    ) {
//        item {
//            Text(
//                text = stringResource(R.string.more_recipes),
//                style = MaterialTheme.typography.h6,
//                modifier = Modifier.padding(8.dp),
//            )
//        }
//        items(recipes) { data ->
//            RecipeCardGridItem(
//                title = data.title,
//                description = data.description,
//                imageUrl = data.imageUrl,
//                rating = data.rating.toFloat(),
//                modifier = Modifier.clickable { navigateToDetail(data.recipeId) }
//            )
//        }
//    }

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
                text = recipeRecommendations.title,
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .weight(1f)
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
                RecipeCardMediumItem(
                    title = data.title,
                    description = data.description,
                    imageUrl = data.imageUrl,
                    rating = data.rating.toFloat(),
                    modifier = Modifier.clickable { navigateToDetail(data.recipeId) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
    BahanbaKuTheme {
        HomeContent(
            topRecommended = recipeRecommendations,
            recommendations = listOf(
                recipeRecommendations, recipeRecommendations
            ),
            allRecipes = recipeList,
            navigateToDetail = {}
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
        RecipeRecommendation(
            recipeRecommendations = recipeRecommendations,
            navigateToDetail = {},
            navigateToAll = {}
        )
    }
}
