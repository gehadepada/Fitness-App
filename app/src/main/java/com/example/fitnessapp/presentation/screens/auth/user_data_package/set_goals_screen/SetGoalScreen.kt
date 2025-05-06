package com.example.fitnessapp.presentation.screens.auth.user_data_package.set_goals_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fitnessapp.R
import com.example.fitnessapp.presentation.components.BackButton
import com.example.fitnessapp.presentation.components.DefaultButton
import com.example.fitnessapp.presentation.components.FailedLoadingScreen
import com.example.fitnessapp.presentation.viewModels.userData_viewModel.UserDataState
import com.example.fitnessapp.presentation.viewModels.userData_viewModel.UserDataViewModel
import com.example.fitnessapp.theme.FitnessAppTheme
@Composable
fun SetGoalsScreen(
    onSetGoals: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    val personGoals = remember { mutableStateOf("") }
    val isGoalSelected = remember { mutableStateOf("") }

    var loadTrigger by remember { mutableStateOf(false) }

    val userDataViewModel = hiltViewModel<UserDataViewModel>()
    val userDataState = userDataViewModel.userDataState.collectAsStateWithLifecycle()

    if (loadTrigger) {
        LaunchedEffect(Unit) {
            userDataViewModel.saveDataToFirestore(
                mapOf("goal" to personGoals.value)
            )
            loadTrigger = false
        }
    }

    when (userDataState.value) {
        is UserDataState.Error -> FailedLoadingScreen()

        UserDataState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }

        UserDataState.Success -> {
            LaunchedEffect(Unit) {
                onSetGoals()
                userDataViewModel.resetUserDataState()
            }
        }
        else -> Unit
    }


    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = screenWidth * 0.05f, vertical = screenHeight * 0.03f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val items = listOf(
            R.drawable.loseweight,
            R.drawable.weightscale,
            R.drawable.machine
        )
        val goals = listOf("Lose Weight", "Gain Weight", "Maintain Weight")
        val selectedIndex = remember { mutableIntStateOf(-1) }


        Text(
            text = "Set Your Goals",
            style = MaterialTheme.typography.headlineMedium,
            color = colorScheme.onBackground,
            modifier = Modifier
                .padding(top = screenHeight * 0.02f, bottom = screenHeight * 0.02f)
        )

        items.forEachIndexed { index, image ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(bottom = screenHeight * 0.015f)
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.35f)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(16.dp))
                        .border(
                            width = 2.dp,
                            color = if (selectedIndex.intValue == index) colorScheme.primary else Color.Gray,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clickable {
                            selectedIndex.intValue = index
                            personGoals.value = goals[selectedIndex.intValue]
                        }
                        .background(color = colorScheme.surface)
                ) {
                    Image(
                        painter = painterResource(id = image),
                        contentDescription = "Goal Image",
                        modifier = Modifier
                            .padding(15.dp)
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                Text(
                    text = goals[index],
                    modifier = Modifier.padding(top = screenHeight * 0.01f),
                    style = MaterialTheme.typography.labelMedium,
                    color = colorScheme.onBackground
                )
            }
        }

        DefaultButton(
            onClick = {
                if (personGoals.value.isEmpty()) {
                    isGoalSelected.value = "Please select your goals"
                } else {
                    loadTrigger = true
                }
            },
            message = isGoalSelected.value
        )

        BackButton(onclick = onBack)
    }
}


@Preview
@Composable
private fun Prev() {
    FitnessAppTheme {
        SetGoalsScreen()
    }
}