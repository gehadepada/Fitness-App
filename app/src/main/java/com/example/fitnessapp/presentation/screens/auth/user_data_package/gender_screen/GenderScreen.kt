package com.example.fitnessapp.presentation.screens.auth.user_data_package.gender_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fitnessapp.R
import com.example.fitnessapp.presentation.components.BackButton
import com.example.fitnessapp.presentation.components.DefaultButton
import com.example.fitnessapp.presentation.components.FailedLoadingScreen
import com.example.fitnessapp.presentation.viewModels.save_userData_viewModel.SaveUserDataState
import com.example.fitnessapp.presentation.viewModels.save_userData_viewModel.SaveUserDataViewModel
import androidx.compose.foundation.rememberScrollState


@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun GenderScreen(onGender: () -> Unit) {
    val isGenderSelected = remember { mutableStateOf("") }

    val items = listOf(R.drawable.male, R.drawable.female)
    val gender = listOf("Male", "Female")
    val selectedIndex = remember { mutableIntStateOf(-1) }
    var showError by remember { mutableStateOf(false) }

    val saveUserDataViewModel = hiltViewModel<SaveUserDataViewModel>()
    val userDataState = saveUserDataViewModel.userDataState.collectAsStateWithLifecycle()

    var loadTrigger by remember { mutableStateOf(false) }

    if (loadTrigger) {
        LaunchedEffect(Unit) {
            saveUserDataViewModel.saveDataToFirestore(
                mapOf("gender" to gender[selectedIndex.intValue])
            )
            loadTrigger = false
        }
    }

    when (userDataState.value) {
        is SaveUserDataState.Error -> {
            FailedLoadingScreen()
            return
        }

        SaveUserDataState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
            return
        }

        SaveUserDataState.Success -> {
            LaunchedEffect(Unit) {
                onGender()
                saveUserDataViewModel.resetUserDataState()
            }
        }

        else -> Unit
    }

    @Suppress("BoxWithConstraintsScopeUnused")
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val maxHeight = maxHeight
        val maxWidth = maxWidth

        val imageSize = maxWidth * 0.40f
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = maxWidth * 0.05f, vertical = maxHeight * 0.03f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(maxHeight * 0.08f))

            Text(
                text = "What's Your Gender ?",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(top=20.dp,bottom=15.dp)
            )
            items.forEachIndexed { index, image ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(bottom = maxHeight * 0.04f)
                ) {
                    Box(
                        modifier = Modifier
                            .size(imageSize)
                            .clip(RoundedCornerShape(16.dp))
                            .border(
                                width = 2.dp,
                                color = if (selectedIndex.intValue == index)
                                    MaterialTheme.colorScheme.primary else Color.Gray,
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
                                .padding(imageSize * 0.1f)
                                .fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Text(
                        modifier = Modifier.padding(top = maxHeight * 0.01f),
                        text = gender[index],
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }

            if (showError) {
                Text(
                    text = "Please select a gender",
                    color = Color.Red,
                    modifier = Modifier.padding(bottom = maxHeight * 0.02f)
                )
            }

            DefaultButton(
                onClick = {
                    if (selectedIndex.intValue == -1) {
                        showError = true
                    } else {
                        loadTrigger = true
                    }
                },
                message = isGenderSelected.value
            )

            BackButton(enabled = false, onclick = {})
        }
    }
}


