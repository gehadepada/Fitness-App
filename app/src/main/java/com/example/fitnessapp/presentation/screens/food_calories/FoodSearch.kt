package com.example.fitnessapp.presentation.screens.food_calories

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SearchFoodScreen(onSearchFood:(foodName: String, calories: String) -> Unit) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    Column {
        SearchView(textState)
        FoodList(onSearchFood, state = textState)
    }
}

@Composable
fun SearchView(state: MutableState<TextFieldValue>) {
    TextField(
        value = state.value,
        placeholder = { Text("Search Food", style = TextStyle(color = MaterialTheme.colorScheme.onBackground), fontSize = 17.sp) },
        onValueChange = { value -> state.value = value },
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp),
        textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground, fontSize = 14.sp),
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "") },
        trailingIcon = {
            if (state.value.text.isNotEmpty()) {
                IconButton(onClick = { state.value = TextFieldValue("") }) {
                    Icon(Icons.Default.Close, contentDescription = "")
                }
            }
        },
        singleLine = true,
    )
}


@Composable
fun FoodList(onSearchFood:(foodName: String, calories: String) -> Unit, state: MutableState<TextFieldValue>) {
    val searchedText = state.value.text
    val filteredFoods = if (searchedText.isEmpty()) {
        foodCaloriesList.take(10) // Show only 10 by default
    } else {
        foodCaloriesList.filter { food ->
            food.first.lowercase().contains(searchedText.lowercase())
        }
    }

    Column {
        Text(
            text = "All values are per 100gm",
            modifier = Modifier.padding(start = 16.dp, top = 4.dp, bottom = 8.dp),
            color = Color.Gray,
            style = MaterialTheme.typography.labelMedium
        )

        LazyColumn {
            items(filteredFoods) { (foodName, calories) ->
                Row(
                    modifier = Modifier
                        .clickable {
                            Log.d("Al-qiran from Main screen", "$foodName $calories")
                            onSearchFood(foodName, calories)
                        }
                        .padding(14.dp)
                        .fillMaxSize(),
                    ) {
                    Column {
                        Text(
                            text = foodName,
                            color = Color.White,
                            style = MaterialTheme.typography.labelMedium
                        )
                        Text(
                            text = "Calories: $calories kcal",
                            color = Color.Gray,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
        }


    }
}
