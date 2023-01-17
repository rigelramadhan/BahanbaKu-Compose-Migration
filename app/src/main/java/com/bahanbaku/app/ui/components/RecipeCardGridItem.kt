package com.bahanbaku.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.bahanbaku.app.R
import com.bahanbaku.app.ui.theme.BahanbaKuTheme

@Composable
fun RecipeCardGridItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    imageUrl: String,
    rating: Float,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(
                    model = imageUrl,
                    placeholder = painterResource(id = R.drawable.image_placeholder)
                ),
                contentDescription = stringResource(id = R.string.description_recipe_image),
                modifier = Modifier
                    .aspectRatio(1 / 1f),
                contentScale = ContentScale.Crop,
            )

            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.subtitle1
                        .copy(fontWeight = FontWeight.Medium),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.caption,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row {
                    Icon(
                        imageVector = Icons.Rounded.Star,
                        contentDescription = stringResource(R.string.description_recipe_rating),
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = rating.toString())
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun RecipeCardGridItemPreview() {
    BahanbaKuTheme {
        RecipeCardGridItem(
            title = "Bakso Sedap ala Rumah Tangga Perumahan Sembako Blok 2L-5",
            description = "This is the description of a food that is very popular in Indonesia. This food has its own version in every area across Indonesia",
            imageUrl = "",
            rating = 4.5f
        )
    }
}