package com.example.fitnessapp.presentation.screens.food_calories

import androidx.compose.foundation.border
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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


@Composable
fun Navigation(navController: NavHostController) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(navController = navController)
        }
        composable(
            "details/{foodName}/{calories}",
            arguments = listOf(
                navArgument("foodName") { type = NavType.StringType },
                navArgument("calories") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val foodName = backStackEntry.arguments?.getString("foodName") ?: ""
            val calories = backStackEntry.arguments?.getString("calories") ?: ""
            DetailsScreen(foodName, calories)
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    Column {
        SearchView(textState)
        FoodList(navController = navController, state = textState)
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
            .padding(16.dp)
            .drawBehind {
                val strokeWidth = 1.dp.toPx()
                drawLine(
                    color = Color.Gray,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = strokeWidth
                )
            },
        textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp),
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "") },
        trailingIcon = {
            if (state.value.text.isNotEmpty()) {
                IconButton(onClick = { state.value = TextFieldValue("") }) {
                    Icon(Icons.Default.Close, contentDescription = "")
                }
            }
        },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFF000000),
            unfocusedContainerColor = Color(0xFF000000),
            disabledContainerColor = Color(0xFF000000),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent

        )
    )
}


@Composable
fun FoodList(navController: NavController, state: MutableState<TextFieldValue>) {
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
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
            fontSize = 14.sp,
            color = Color.Gray
        )

        LazyColumn {
            items(filteredFoods) { (foodName, calories) ->
                Row(
                    modifier = Modifier
                        .clickable { navController.navigate("details/$foodName/$calories") }
                        .padding(16.dp)
                        .fillMaxSize()
                ) {
                    Column {
                        Text(text = foodName, fontSize = 18.sp, color = Color.White)
                        Text(text = "Calories: $calories kcal", fontSize = 14.sp, color = Color.Gray)
                    }
                }
            }
        }
    }
}

val foodCaloriesList = listOf(
    "Almonds" to "365",
    "Apples" to "99",
    "Avocados" to "555",
    "Bananas" to "548",
    "Barley" to "605",
    "Beef" to "459",
    "Beets" to "100",
    "Bell Peppers" to "37",
    "Black Beans" to "461",
    "Blueberries" to "95",
    "Broccoli" to "39",
    "Brown Rice" to "454",
    "Cabbage" to "91",
    "Carrots" to "100",
    "Cauliflower" to "64",
    "Celery" to "43",
    "Cheddar Cheese" to "553",
    "Chicken Breast" to "355",
    "Chickpeas" to "534",
    "Coconut" to "600",
    "Cod" to "106",
    "Cottage Cheese" to "189",
    "Cucumber" to "50",
    "Dates" to "422",
    "Dark Chocolate" to "654",
    "Eggplant" to "72",
    "Eggs" to "153",
    "Figs" to "184",
    "Garlic" to "179",
    "Ginger" to "121",
    "Grapes" to "91",
    "Green Beans" to "68",
    "Green Peas" to "137",
    "Ground Beef" to "487",
    "Haddock" to "93",
    "Ham" to "244",
    "Honey" to "304",
    "Kale" to "51",
    "Kidney Beans" to "458",
    "Lamb" to "529",
    "Leeks" to "87",
    "Lentils" to "488",
    "Lettuce" to "42",
    "Mangoes" to "113",
    "Milk" to "61",
    "Mushrooms" to "53",
    "Oatmeal" to "367",
    "Olives" to "115",
    "Onions" to "63",
    "Oranges" to "85",
    "Papaya" to "74",
    "Parsnips" to "118",
    "Peaches" to "88",
    "Peanut Butter" to "589",
    "Peanuts" to "587",
    "Pears" to "77",
    "Peas" to "123",
    "Pineapple" to "97",
    "Pistachios" to "566",
    "Plums" to "75",
    "Pomegranate" to "98",
    "Pork" to "566",
    "Potatoes" to "123",
    "Pumpkin" to "66",
    "Quinoa" to "368",
    "Radishes" to "51",
    "Raspberries" to "63",
    "Red Cabbage" to "56",
    "Red Grapes" to "95",
    "Salmon" to "484",
    "Sardines" to "453",
    "Spinach" to "41",
    "Strawberries" to "67",
    "Sunflower Seeds" to "605",
    "Sweet Corn" to "92",
    "Sweet Potatoes" to "108",
    "Swiss Cheese" to "611",
    "Tomatoes" to "47",
    "Tofu" to "144",
    "Tuna" to "356",
    "Turkey" to "389",
    "Turnips" to "64",
    "Walnuts" to "687",
    "Watermelon" to "42",
    "Wheat Bread" to "277",
    "White Beans" to "475",
    "White Rice" to "364",
    "Yogurt" to "59",
    "Zucchini" to "38",
    "Bok Choy" to "53",
    "Chia Seeds" to "625",
    "Chili Pepper" to "82",
    "Brussels Sprouts" to "72",
    "Cornflakes" to "389",
    "Ghee" to "675",
    "Hazelnuts" to "636",
    "Macadamia Nuts" to "712",
    "Mustard Greens" to "42",
    "Oysters" to "92"
)
