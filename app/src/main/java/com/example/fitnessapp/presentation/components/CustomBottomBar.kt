package com.example.fitnessapp.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.theme.FitnessAppTheme
import com.example.fitnessapp.R

sealed class BottomBarItem(open val label: String) {
    data class VectorIcon(val icon: ImageVector, override val label: String) : BottomBarItem(label)
    data class DrawableIcon(@DrawableRes val iconRes: Int, override val label: String) : BottomBarItem(label)
}

@Composable
fun CustomBottomBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    val items = listOf(
        BottomBarItem.VectorIcon(Icons.Default.Home, "Home"),
        BottomBarItem.VectorIcon(Icons.Default.FitnessCenter, "Workout"),
        BottomBarItem.DrawableIcon(R.drawable.add_icon, "Add"),
        BottomBarItem.DrawableIcon(R.drawable.food_package, "history"),
        BottomBarItem.VectorIcon(Icons.Default.Person, "Profile")
    )

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(horizontal = 4.dp),
        shape = RoundedCornerShape(32.dp),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, item ->
                IconButton(
                    modifier = Modifier
                        .height(55.dp)
                        .weight(1f),
                    onClick = { onItemSelected(index) }
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        when (item) {
                            is BottomBarItem.VectorIcon -> Icon(
                                imageVector = item.icon,
                                contentDescription = item.label,
                                tint = if (index == selectedIndex) MaterialTheme.colorScheme.primary else Color.Gray,
                                modifier = Modifier.size(28.dp)
                            )
                            is BottomBarItem.DrawableIcon -> Icon(
                                painter = painterResource(id = item.iconRes),
                                contentDescription = item.label,
                                tint = if (index == selectedIndex) MaterialTheme.colorScheme.primary else Color.Gray,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = item.label,
                            style = MaterialTheme.typography.labelSmall,
                            color = if (index == selectedIndex) MaterialTheme.colorScheme.primary else Color.Gray,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun Prev() {
    FitnessAppTheme {
        CustomBottomBar(0, onItemSelected = {})
    }
}
