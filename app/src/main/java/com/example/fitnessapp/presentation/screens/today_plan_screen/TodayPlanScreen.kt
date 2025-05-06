package com.example.fitnessapp.presentation.screens.today_plan_screen

import FoodViewModel
import android.app.DatePickerDialog
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitnessapp.presentation.components.FailedLoadingScreen
import com.example.fitnessapp.presentation.model.FoodAndCaloriesUIModel
import com.example.fitnessapp.presentation.viewModels.foodAndCalories_viewModel.FoodAndCaloriesState
import com.example.fitnessapp.presentation.viewModels.foodAndCalories_viewModel.FoodAndCaloriesViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun TodayPlanScreen(viewModel: FoodViewModel = viewModel()) {
    val context = LocalContext.current
    val selectedDate = viewModel.selectedDate

    val foodAndCaloriesViewModel = hiltViewModel<FoodAndCaloriesViewModel>()
    val foodAndCaloriesState = foodAndCaloriesViewModel.foodAndCaloriesState.collectAsStateWithLifecycle()
    val foodDelete = remember {mutableStateOf(FoodAndCaloriesUIModel("","", 0,0.0))}

    val endDate = remember { mutableStateOf(SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH).format(Date())) }
    val startDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH).format(Date())

    LaunchedEffect(endDate.value) {
        Log.d("Al-qiran", "$startDate || ${endDate.value}")
        foodAndCaloriesViewModel.getFoodAndCalories(startDate, endDate.value)
    }

    LaunchedEffect(foodDelete.value) {
        if (foodDelete.value != FoodAndCaloriesUIModel("","", 0,0.0)) {
            foodAndCaloriesViewModel.deleteFoodAndCalorie(foodDelete.value.)
        }
    }

    when (foodAndCaloriesState.value) {
        is FoodAndCaloriesState.Error -> {
            FailedLoadingScreen(
                errorMessage = "${(foodAndCaloriesState.value as FoodAndCaloriesState.Error).error}..",
            )
        }

        FoodAndCaloriesState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }

        is FoodAndCaloriesState.SuccessWithData -> {
            Column(modifier = Modifier.padding(16.dp)) {

                Text("Date: $selectedDate", style = MaterialTheme.typography.titleSmall)

                Spacer(modifier = Modifier.height(10.dp))

                val foods =
                    (foodAndCaloriesState.value as FoodAndCaloriesState.SuccessWithData).foodAndCaloriesUIModel
                LazyColumn {
                    items(foods) { food ->
                        FoodItem(food)
                    }
                }
            }
        }

        else -> Unit
    }

    Column {
        Button(onClick = {
            val datePicker = DatePickerDialog(
                context,
                { _, year, month, day ->
                    val formattedMonth = String.format("%02d", month + 1)
                    val formattedDay = String.format("%02d", day)
                    val selectedDate = "$year-$formattedMonth-$formattedDay 23:59:59.999"
                    endDate.value = selectedDate
                },
                selectedDate.year, selectedDate.monthValue - 1, selectedDate.dayOfMonth
            )
            datePicker.show()

        }) {
            Text("Pick a Date", style = MaterialTheme.typography.titleLarge)
        }
        Spacer(modifier = Modifier.height(16.dp))
    }

}

@Composable
fun FoodItem(food: FoodAndCaloriesUIModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = food.foodName,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = {

                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = "Delete Food"
                    )
                }
            }
            Text("Amount: ${food.totalAmount}", style = MaterialTheme.typography.titleSmall)
            Text("Calories: ${food.calories}", style = MaterialTheme.typography.titleSmall)
            Text("Date: ${food.date}", style = MaterialTheme.typography.titleSmall)
        }
    }
}
