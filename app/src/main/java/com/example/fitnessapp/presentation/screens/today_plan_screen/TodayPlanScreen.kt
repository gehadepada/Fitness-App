package com.example.fitnessapp.presentation.screens.today_plan_screen

import Food
import FoodViewModel
import android.app.DatePickerDialog
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.time.LocalDate
@Composable
fun TodayPlanScreen(viewModel: FoodViewModel = viewModel()) {
    val context = LocalContext.current
    val selectedDate = viewModel.selectedDate
    val foods = viewModel.filteredFoods

    Column(modifier = Modifier.padding(16.dp)) {

        Button(onClick = {
            val datePicker = DatePickerDialog(
                context,
                { _, year, month, day ->
                    viewModel.setDate(LocalDate.of(year, month + 1, day))
                },
                selectedDate.year, selectedDate.monthValue - 1, selectedDate.dayOfMonth
            )
            datePicker.show()
        }) {
            Text("Pick a Date", style = MaterialTheme.typography.titleLarge)
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text("Date: $selectedDate", style = MaterialTheme.typography.titleSmall)

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn {
            items(foods) { food ->
                FoodItem(food)
            }
        }
    }
}

@Composable
fun FoodItem(food: Food) {
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
                IconButton(onClick = {}) {
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
