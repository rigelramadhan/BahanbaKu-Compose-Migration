package com.bahanbaku.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.bahanbaku.app.R
import com.bahanbaku.app.ui.theme.BahanbaKuTheme

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    title: String,
    imageUrl: String,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(32.dp),
    ) {
        Column(
            modifier = Modifier,
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = imageUrl,
                    placeholder = painterResource(id = R.drawable.image_placeholder)
                ),
                contentDescription = stringResource(
                    R.string.description_category_image
                ),
                contentScale = ContentScale.Crop,
                modifier = Modifier.aspectRatio(1 / 1f)
            )
            Text(
                text = title,
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp, bottom = 16.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryItemPreview() {
    BahanbaKuTheme {
        CategoryItem(title = "Chicken", imageUrl = "")
    }
}