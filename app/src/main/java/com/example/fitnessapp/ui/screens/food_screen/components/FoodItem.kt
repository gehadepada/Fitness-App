package com.example.fitnessapp.ui.screens.food_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.R
import com.example.fitnessapp.ui.theme.FitnessAppTheme

@Composable
fun FoodItem(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(12.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Card(
            modifier = Modifier
                .padding(start = 23.dp)
                .clickable {
                    println("Clicking Card...")

                }
                .border(2.dp, colorScheme.primary, shapes.large)
                .clip(shapes.large)
                .background(colorScheme.surface)
                .height(100.dp)
                .fillMaxWidth()

        ) {
            Row(
                modifier = Modifier
                    .padding(1.dp)
                    .padding(start = 90.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    Text(
                        text = "Recipe name ",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyMedium,
                        color = colorScheme.onSurface
                    )

                    Text(
                        text = "description description description description description description description ",
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.labelSmall,
                        color = colorScheme.onSurface
                    )
                }

                Image(
                    modifier = Modifier
                        .clickable {

                        }
                        .padding(end = 2.dp)
                        .size(30.dp),
                    painter = painterResource(id = R.drawable.bookmark),
                    colorFilter = ColorFilter.tint(colorScheme.primary),
                    contentDescription = "Food Image",
                )

            }
        }

        Image(
            modifier = Modifier
                .size(110.dp)
                .shadow(3.dp, shape = CircleShape, spotColor = colorScheme.onSurface)
                .clip(CircleShape)
            ,
            painter = painterResource(id = R.drawable.food_ta3mya),
            contentDescription = "Food Image",
        )
    }
}


@Preview
@Composable
private fun Prev() {
    FitnessAppTheme {
        FoodItem()
    }
}