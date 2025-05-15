package com.example.fitnessapp.presentation.screens.dashboared

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.R
import com.example.fitnessapp.presentation.screens.dashboared.components.AddWaterSection
import com.example.fitnessapp.presentation.screens.dashboared.components.CircularProgressIndicator
import com.example.fitnessapp.presentation.screens.dashboared.components.DiscoverSection
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@Composable
fun DashboardScreen(
    onRecipes: () -> Unit,
    onWorkouts: () -> Unit,
    onFoodHistory: () -> Unit,
    onSearch: () -> Unit,
    onHealth: () -> Unit,
    onProfile: () -> Unit,
    onWater: () -> Unit
) {
    val database = FirebaseDatabase.getInstance()
    val userId = FirebaseAuth.getInstance().currentUser?.uid

    var goalCalories by remember { mutableIntStateOf(3000) }
    var consumedCalories by remember { mutableIntStateOf(2000) }
    var exerciseCalories by remember { mutableIntStateOf(500) }

    // Fetch user data from Firebase
    LaunchedEffect(Unit) {
        userId?.let {
            database.reference.child("Users").child(it)
                .get()
                .addOnSuccessListener { dataSnapshot ->
                    if (dataSnapshot.exists()) {
                        goalCalories =
                            dataSnapshot.child("goalCalories").value?.toString()?.toLongOrNull()
                                ?.toInt() ?: 3000
                        consumedCalories =
                            dataSnapshot.child("consumedCalories").value?.toString()?.toLongOrNull()
                                ?.toInt() ?: 2000
                        exerciseCalories =
                            dataSnapshot.child("exerciseCalories").value?.toString()?.toLongOrNull()
                                ?.toInt() ?: 500

                    }
                }
                .addOnFailureListener { e -> println("Error fetching user data: $e") }
        }
    }

    Column(
        modifier = Modifier
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
        }

        Column(
            modifier = Modifier
                .padding(all = 15.dp)
                .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(21.dp))
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            val remainingCalories = goalCalories - consumedCalories + exerciseCalories

            Text(
                text = "Calories",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "Remaining = $goalCalories - $consumedCalories + $exerciseCalories = $remainingCalories",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelLarge,
            )

            Row(
                modifier = Modifier.padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                CircularProgressIndicator(
                    progress = consumedCalories.toFloat() / goalCalories,
                    remainingText = remainingCalories.toString(),
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

        AddWaterSection(
            onWater
        )
        DiscoverSection(
            onRecipes,
            onWorkouts,
            onFoodHistory,
            onSearch,
            onHealth,
            onProfile,
        )
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


@Preview(showBackground = true)
@Composable
fun PreviewAll() {
    DashboardScreen(
        onRecipes = {},
        onWorkouts = {},
        onFoodHistory = {},
        onSearch = {},
        onHealth = {},
        onProfile = {},
        onWater = {},
    )
}
