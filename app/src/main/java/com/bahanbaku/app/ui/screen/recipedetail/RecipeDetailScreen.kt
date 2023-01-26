package com.bahanbaku.app.ui.screen.recipedetail

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.bahanbaku.app.R
import com.bahanbaku.app.ui.theme.BahanbaKuTheme

@Composable
fun RecipeDetailScreen(
    modifier: Modifier = Modifier,
) {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailContent(
    modifier: Modifier = Modifier,
    title: String,
    onBackPressed: () -> Unit,
    onFavoritePressed: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            ExpandableTopAppBar(
                title = title,
                onBackPressed = onBackPressed,
                onFavoritePressed = onFavoritePressed,
                scrollBehavior = scrollBehavior
            )
        },
    ) { paddingValues ->
        paddingValues
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    onBackPressed: () -> Unit,
    onFavoritePressed: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    MediumTopAppBar(
        modifier = modifier,
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = stringResource(R.string.description_back_button)
                )
            }
        },
        actions = {
            IconButton(onClick = onFavoritePressed) {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = stringResource(R.string.description_favorite_button)
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview()
fun RecipeDetailContentPreview() {
    BahanbaKuTheme {
        RecipeDetailContent(title = "Ayam Goreng", onBackPressed = {}, onFavoritePressed = {

        })
    }
}