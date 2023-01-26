package com.bahanbaku.app.ui.screen.search

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bahanbaku.app.R
import com.bahanbaku.app.core.data.Resource
import com.bahanbaku.app.core.domain.model.Recipe
import com.bahanbaku.app.ui.common.AuthState
import com.bahanbaku.app.ui.components.LoadingIndicator
import com.bahanbaku.app.ui.components.RecipeCardGridItem
import com.bahanbaku.app.ui.theme.GrayText

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    navigateToLogin: @Composable (() -> Unit),
) {
    val authState: AuthState by viewModel.authState.collectAsState(initial = AuthState.Loading())

    val uiState: Resource<List<Recipe>> by viewModel.uiState.collectAsState(initial = Resource.Loading())

    var searchText by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(
            TextFieldValue()
        )
    }


    if (authState is AuthState.Unauthorized) {
        navigateToLogin()
    }

    Scaffold(
        topBar = {
            SearchTopBar(
                searchText = searchText,
                onTextChange = { searchText = it },
                closeSearchBar = {},
                onSearch = { text ->
                    viewModel.searchRecipe(text)
                }
            )
        },
        modifier = modifier
    ) { paddingValues ->
        when (uiState) {
            is Resource.Success -> {
                val data = uiState.data
                if (data.isNullOrEmpty()) {
                    Toast.makeText(LocalContext.current, "Empty data", Toast.LENGTH_SHORT).show()
                } else {
                    SearchContent(
                        searchRecipes = data,
                        modifier = Modifier.padding(paddingValues).padding(8.dp),
                    )
                }
            }

            is Resource.Loading -> {
                LoadingIndicator()
            }

            is Resource.Error -> {

            }
        }
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
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
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
    onSearch: (String) -> Unit,
    closeSearchBar: () -> Unit,
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
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = { onSearch(searchText.text) })
        )
    }
}