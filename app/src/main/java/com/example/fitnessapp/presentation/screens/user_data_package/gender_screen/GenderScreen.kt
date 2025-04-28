package com.example.fitnessapp.presentation.screens.user_data_package.gender_screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fitnessapp.R
import com.example.fitnessapp.presentation.components.BackButton
import com.example.fitnessapp.presentation.components.DefaultButton
import com.example.fitnessapp.presentation.components.FailedLoadingScreen
import com.example.fitnessapp.presentation.screens.user_data_package.viewModel.UserDataState
import com.example.fitnessapp.presentation.screens.user_data_package.viewModel.UserDataViewModel


@Composable
fun GenderScreen(onGender: (String) -> Unit) {

    val userDataViewModel = hiltViewModel<UserDataViewModel>()
    val userDataState = userDataViewModel.userDataState.collectAsStateWithLifecycle()

    val isGenderSelected = remember { mutableStateOf("") }

    val items = listOf(R.drawable.male, R.drawable.female)
    val gender = listOf("Male", "Female")
    val selectedIndex = remember { mutableIntStateOf(-1) }
    var showError by remember { mutableStateOf(false) }
    var loadTrigger by remember { mutableStateOf(false) }


    if (loadTrigger) {
        LaunchedEffect(Unit) {
            userDataViewModel.saveDataToFirestore(
                mapOf("gender" to gender[selectedIndex.intValue])
            )
            loadTrigger = false
        }
    }

    when (userDataState.value) {
        is UserDataState.Error -> {
            Log.d("Al-qiran", "Error from screen")
            FailedLoadingScreen()
        }

        UserDataState.Loading -> {
            Log.d("Al-qiran", "Loading from screen")
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }

        UserDataState.Success -> {
            Log.d("Al-qiran", "Success from screen")
            LaunchedEffect(Unit) {
                onGender(gender[selectedIndex.intValue])
                userDataViewModel.resetUserDataState()
            }
        }
        else -> Unit
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {

        Text(
            text = "What's Your Gender ?",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Column(
            Modifier
                .fillMaxSize()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {


            items.forEachIndexed { index, image ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .border(
                                width = 2.dp,
                                color = if (selectedIndex.intValue == index) MaterialTheme.colorScheme.primary else Color.Gray,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .clickable {
                                selectedIndex.intValue = index
                                showError = false
                            }
                            .background(color = MaterialTheme.colorScheme.surface)
                    ) {
                        Image(
                            painter = painterResource(id = image),
                            contentDescription = "Gender Image",
                            modifier = Modifier
                                .padding(15.dp)
                                .fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Text(
                        modifier = Modifier.padding(top = 5.dp),
                        text = gender[index],
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }

            if (showError) {
                isGenderSelected.value = "Please select a gender"
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            DefaultButton(
                onClick = {
                    if (selectedIndex.intValue == -1) {
                        showError = true
                    } else {
                        loadTrigger = true
                    }
                }, message = isGenderSelected.value
            )
            BackButton(
                enabled = false,
            )
        }
    }
}

@Composable
@Preview
fun GenderScreenPreview() {
    GenderScreen(onGender = {})
}