package com.bahanbaku.app.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.bahanbaku.app.R
import com.bahanbaku.app.core.utils.currencyIntToString
import com.bahanbaku.app.ui.theme.BahanbaKuTheme
import com.bahanbaku.app.ui.theme.GreenSecondary

@Composable
fun RecipeDetailIngredientItem(
    modifier: Modifier = Modifier,
    title: String,
    amount: Float,
    unit: String,
    price: Int,
    imageUrl: String,
    isSelected: Boolean,
    onIngredientSelect: () -> Unit,
) {
    val cardBorder = if (isSelected) {
        BorderStroke(width = 2.dp, color = GreenSecondary)
    } else {
        null
    }

    Card(
        border = cardBorder,
        shape = RoundedCornerShape(24.dp),
        modifier = modifier
            .clickable { onIngredientSelect() },
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = imageUrl, placeholder = painterResource(
                        id = R.drawable.image_placeholder
                    )
                ),
                contentDescription = stringResource(R.string.description_ingredient_image),
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier
                    .padding(top = 4.dp)
            ) {
                Text(
                    text = title,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = if (amount > 0.0001f) "$amount $unit" else "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp),
                    style = MaterialTheme.typography.caption
                )
                Divider(Modifier.height(1.dp).fillMaxWidth())
                Text(
                    text = stringResource(R.string.format_currency).format(
                        currencyIntToString(price)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    style = MaterialTheme.typography.subtitle2.copy(fontWeight = FontWeight.Normal)
                )
            }
            Surface(
                modifier = Modifier
                    .height(72.dp)
                    .width(2.dp),
                shape = RoundedCornerShape(8.dp),
                color = GreenSecondary,
            ) {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeDetailIngredientItemPreview() {
    BahanbaKuTheme {
        Column(Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            RecipeDetailIngredientItem(
                title = "Random",
                amount = 12f,
                unit = "Butir",
                price = 12000,
                imageUrl = "",
                isSelected = false,
                onIngredientSelect = {}
            )
            RecipeDetailIngredientItem(
                title = "Random awdlawij dwajlawi jdalwdijalw ijdawdli j",
                amount = 12f,
                unit = "Butir",
                price = 12000,
                imageUrl = "",
                isSelected = true,
                onIngredientSelect = {}
            )
        }
    }
}