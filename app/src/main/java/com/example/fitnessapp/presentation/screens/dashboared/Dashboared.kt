package com.example.fitnessapp.presentation.screens.dashboared

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.R
import com.example.fitnessapp.presentation.screens.dashboared.components.AddHabitSection
import com.example.fitnessapp.presentation.screens.dashboared.components.CircularProgressIndicator
import com.example.fitnessapp.presentation.screens.dashboared.components.DiscoverSection

@Composable
fun ProfileScreen(navController: NavController) {
    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            CustomBottomBar(
                selectedIndex = selectedIndex,
                onItemSelected = { selectedIndex = it }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Today",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .padding(horizontal = 5.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = "Edit",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.headlineMedium,
                    )
                }
            }

            Column(
                modifier = Modifier
                    .padding(all = 15.dp)
                    .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(21.dp))
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Calories",
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "Remaining = Goal - Food + Exercise",
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.labelLarge,
                )

                Row(
                    modifier = Modifier.padding(top = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    CircularProgressIndicator(
                        progress = 2000f / 3000f,
                        remainingText = "300",
                        modifier = Modifier.size(120.dp)
                    )
                    Spacer(modifier = Modifier.width(50.dp))

                    Column {
                        BaseFoodExercise(R.drawable.baseline_flag_24, "Base Goal", "Base Goal")
                        BaseFoodExercise(
                            R.drawable.dinner_icon,
                            "Food",
                            "Food (377)",
                            ColorFilter.tint(MaterialTheme.colorScheme.primary)
                        )
                        BaseFoodExercise(R.drawable.hot_icon, "Exercise", "Exercise (20)")
                    }
                }
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 130.dp)
            ) {
                Arrow()
                Arrow()
                Arrow()
            }

            AddHabitSection(navController)
            DiscoverSection(navController)
        }
    }
}

@Composable
fun BaseFoodExercise(
    painter: Int,
    contentDescription: String,
    text: String,
    colorFilter: ColorFilter? = null
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = painter),
            contentDescription = contentDescription,
            colorFilter = colorFilter,
            modifier = Modifier
                .height(30.dp)
                .width(30.dp)
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun Arrow() {
    Image(
        painter = painterResource(id = R.drawable.baseline_call_received_24),
        contentDescription = "Arrow Icon",
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
        modifier = Modifier
            .height(40.dp)
            .width(40.dp)
    )
}

@Composable
fun CustomBottomBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    val items = listOf(
        Icons.Default.Home,
        Icons.Default.ShoppingBag,
        Icons.Default.Person,
        Icons.Default.AddShoppingCart
    )

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(32.dp),
        color = Color(0xFF1C1C1E),
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, icon ->
                IconButton(onClick = { onItemSelected(index) }) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = if (index == selectedIndex) Color(0xFF7CFC00) else Color.Gray,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAll() {
    ProfileScreen(navController = rememberNavController())
}
