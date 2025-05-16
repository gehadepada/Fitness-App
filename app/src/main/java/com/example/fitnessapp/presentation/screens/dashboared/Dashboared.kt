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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnessapp.R
import com.example.fitnessapp.presentation.screens.dashboared.components.AddWaterSection
import com.example.fitnessapp.presentation.screens.dashboared.components.CircularProgressIndicator
import com.example.fitnessapp.presentation.screens.dashboared.components.DiscoverSection
import com.example.fitnessapp.presentation.screens.dashboared.viewModel.DashboardViewModel
import kotlin.math.roundToInt


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

    val viewModel = hiltViewModel<DashboardViewModel>()
    // User calorie data from Firestore
    val userInfoState = viewModel.userInfoState.collectAsState()
    val goalCalories = viewModel.goalCalories.collectAsState()
    val consumedCalories = viewModel.consumedCalories.collectAsState()
    val exerciseCalories = viewModel.exerciseCalories.collectAsState()

    // Fetch user data from Firestore
    LaunchedEffect(Unit) {
        viewModel.getUserData()
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 0.dp,
                bottom = 10.dp
            )
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Today",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(5.dp))

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(21.dp))
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            val remaining = goalCalories.value - consumedCalories.value + exerciseCalories.value

            Text(
                text = "Calories",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelLarge,
            )
            Text(
                text = "Remaining = ${goalCalories.value} - ${consumedCalories.value}  = $remaining",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelLarge,
            )

            Row(
                modifier = Modifier.padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                CircularProgressIndicator(
                    strokeWidth = 20f,
                    progress = if (goalCalories.value > 0) consumedCalories.value.toFloat() / goalCalories.value else 0f,
                    remainingText = remaining.roundToInt().toString(),
                    modifier = Modifier.size(100.dp)
                )
                Spacer(modifier = Modifier.width(50.dp))

                Column {
                    BaseFoodExercise(R.drawable.baseline_flag_24,
                        "Base Goal", "Goal (${goalCalories.value})"
                     ,  ColorFilter.tint(MaterialTheme.colorScheme.primary)
                    )
                    BaseFoodExercise(
                        R.drawable.dinner_icon,
                        "Food",
                        "Food (${consumedCalories.value})",
                        ColorFilter.tint(MaterialTheme.colorScheme.primary)
                    )
                }
            }

            userInfoState.value?.let { userInfo ->
                Spacer(modifier = Modifier.height(10.dp))
                Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f))
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    UserStatItem(label = "Weight", value = "${userInfo.weight} kg")
                    UserStatItem(label = "Height", value = "${userInfo.height} cm")
                    UserStatItem(label = "Goal for Weight", value = userInfo.goal.substringBefore(" "))
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        AddWaterSection(
            onWater
        )
        Spacer(modifier = Modifier.height(10.dp))
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
fun UserStatItem(label: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
        Text(
            text = value,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.labelMedium,
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
