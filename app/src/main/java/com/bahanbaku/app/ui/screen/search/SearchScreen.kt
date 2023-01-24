package com.bahanbaku.app.ui.screen.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.bahanbaku.app.R
import com.bahanbaku.app.core.domain.model.Recipe
import com.bahanbaku.app.ui.components.RecipeCardGridItem
import com.bahanbaku.app.ui.theme.GrayText

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,

    ) {

    var searchText by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(
            TextFieldValue()
        )
    }

    Scaffold(
        topBar = {
            SearchTopBar(
                searchText = searchText,
                onTextChange = {},
                closeSearchBar = {}
            )
        },
    ) { paddingValues ->
        SearchContent(searchRecipes = emptyList(), modifier = Modifier.padding(paddingValues))
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchContent(
    modifier: Modifier = Modifier,
    searchRecipes: List<Recipe>
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(140.dp),
        modifier = modifier
    ) {
        items(searchRecipes) { data ->
            RecipeCardGridItem(
                title = data.title,
                description = data.description,
                imageUrl = data.imageUrl,
                rating = data.rating.toFloat()
            )
        }
    }
}

@Composable
fun SearchTopBar(
    modifier: Modifier = Modifier,
    searchText: TextFieldValue,
    onTextChange: (TextFieldValue) -> Unit,
    closeSearchBar: () -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        elevation = AppBarDefaults.TopAppBarElevation
    ) {
        TextField(
            value = searchText,
            onValueChange = onTextChange,
            placeholder = {
                Text(
                    text = stringResource(R.string.enter_recipe_name),
                    color = GrayText,
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = stringResource(R.string.enter_recipe_name)
                )
            },
            trailingIcon = {
                IconButton(onClick = {
                    if (searchText.text.isNotEmpty()) {
                        onTextChange(TextFieldValue(""))
                    } else {
                        closeSearchBar()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = stringResource(R.string.description_close_search)
                    )
                }
            }
        )
    }
}