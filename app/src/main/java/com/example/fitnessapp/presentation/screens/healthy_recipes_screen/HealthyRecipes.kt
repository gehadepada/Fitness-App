package com.example.fitnessapp.presentation.screens.healthy_recipes_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import coil.compose.rememberAsyncImagePainter

@Composable
fun RecipeApp() {
    val navController = rememberNavController()
    val recipeList = remember { sampleRecipes }

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            RecipeGridScreen(recipeList) { selectedRecipe ->
                navController.navigate("detail/${selectedRecipe.id}")
            }
        }
        composable(
            "detail/{recipeId}",
            arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getInt("recipeId") ?: 0
            val recipe = recipeList.first { it.id == recipeId }
            RecipeDetailScreen(recipe)
        }
    }
}

data class Recipe(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val protein: Int,
    val calories: Int,
    val preparationTime: String,
    val ingredients: List<Pair<String, String>>,
    val steps: String
)

val sampleRecipes = listOf(
    Recipe(
        id = 1,
        title = "Grilled Chicken Salad",
        imageUrl = "https://www.themealdb.com/images/media/meals/llcbn01574260722.jpg",
        protein = 35,
        calories = 400,
        preparationTime = "20 min",
        ingredients = listOf(
            "Chicken Breast" to "200g",
            "Lettuce" to "1 cup",
            "Tomatoes" to "1/2 cup",
            "Olive Oil" to "1 tbsp"
        ),
        steps = "1. Grill the chicken.\n\n2. Chop the vegetables.\n\n3. Mix together with olive oil."
    ),
    Recipe(
        id = 2,
        title = "Quinoa & Black Bean Salad",
        imageUrl = "https://www.themealdb.com/images/media/meals/llcbn01574260722.jpg",
        protein = 24,
        calories = 350,
        preparationTime = "25 min",
        ingredients = listOf(
            "Quinoa" to "1/2 cup",
            "Black Beans" to "1/2 cup",
            "Avocado" to "1/2",
            "Corn" to "1/4 cup",
            "Lime" to "1",
            "Cilantro" to "1 tbsp"
        ),
        steps = "1. Cook the quinoa.\n\n2. Combine all ingredients.\n\n3. Squeeze lime and mix well."
    ),
    Recipe(
        id = 3,
        title = "Egg White Omelette with Spinach",
        imageUrl = "https://www.themealdb.com/images/media/meals/llcbn01574260722.jpg",
        protein = 30,
        calories = 250,
        preparationTime = "10 min",
        ingredients = listOf(
            "Egg Whites" to "6",
            "Spinach" to "1 cup",
            "Tomatoes" to "1/2",
            "Olive Oil" to "1 tsp"
        ),
        steps = "1. Whisk the egg whites.\n\n2. Sauté spinach and tomatoes in olive oil.\n\n3. Pour egg whites over and cook until set."
    ),
    Recipe(
        id = 4,
        title = "Salmon with Roasted Vegetables",
        imageUrl = "https://www.themealdb.com/images/media/meals/sytyqs1511639805.jpg",
        protein = 40,
        calories = 500,
        preparationTime = "30 min",
        ingredients = listOf(
            "Salmon Fillet" to "200g",
            "Broccoli" to "1 cup",
            "Carrots" to "1/2 cup",
            "Olive Oil" to "1 tbsp",
            "Lemon" to "1"
        ),
        steps = "1. Roast vegetables with olive oil.\n\n2. Grill salmon and squeeze lemon over it.\n\n3. Serve salmon with roasted vegetables."
    ),
    Recipe(
        id = 5,
        title = "Turkey and Avocado Wrap",
        imageUrl = "https://www.themealdb.com/images/media/meals/urspyx1504371423.jpg",
        protein = 35,
        calories = 400,
        preparationTime = "15 min",
        ingredients = listOf(
            "Turkey Breast" to "150g",
            "Whole Wheat Tortilla" to "1",
            "Avocado" to "1/2",
            "Lettuce" to "1/2 cup",
            "Tomato" to "1/2"
        ),
        steps = "1. Layer the turkey, avocado, and veggies in the tortilla.\n\n2. Roll up and serve."
    ),
    Recipe(
        id = 6,
        title = "Tuna Salad with Chickpeas",
        imageUrl = "https://www.themealdb.com/images/media/meals/qyxwqt1511817857.jpg",
        protein = 40,
        calories = 450,
        preparationTime = "15 min",
        ingredients = listOf(
            "Tuna" to "1 can",
            "Chickpeas" to "1/2 cup",
            "Cucumber" to "1/2",
            "Lettuce" to "1 cup",
            "Olive Oil" to "1 tbsp",
            "Lemon" to "1"
        ),
        steps = "1. Mix tuna, chickpeas, and chopped vegetables.\n\n2. Dress with olive oil and lemon juice."
    ),
    Recipe(
        id = 7,
        title = "Greek Yogurt & Almond Bowl",
        imageUrl = "https://www.themealdb.com/images/media/meals/xyxwrq1511382821.jpg",
        protein = 25,
        calories = 300,
        preparationTime = "5 min",
        ingredients = listOf(
            "Greek Yogurt" to "1 cup",
            "Almonds" to "1/4 cup",
            "Honey" to "1 tsp",
            "Chia Seeds" to "1 tsp"
        ),
        steps = "1. Add yogurt to a bowl.\n\n2. Top with almonds, chia seeds, and honey."
    ),
    Recipe(
        id = 8,
        title = "Chicken and Sweet Potato Bowl",
        imageUrl = "https://www.themealdb.com/images/media/meals/r8qp3m1511463117.jpg",
        protein = 45,
        calories = 500,
        preparationTime = "35 min",
        ingredients = listOf(
            "Chicken Breast" to "200g",
            "Sweet Potato" to "1 medium",
            "Spinach" to "1 cup",
            "Olive Oil" to "1 tbsp"
        ),
        steps = "1. Roast sweet potato and spinach in olive oil.\n\n2. Grill the chicken.\n\n3. Combine and serve."
    ),
    Recipe(
        id = 9,
        title = "Lentil & Chicken Soup",
        imageUrl = "https://www.themealdb.com/images/media/meals/5t10z01586379263.jpg",
        protein = 40,
        calories = 350,
        preparationTime = "40 min",
        ingredients = listOf(
            "Chicken Breast" to "150g",
            "Lentils" to "1/2 cup",
            "Carrots" to "1/2 cup",
            "Celery" to "1/2 cup",
            "Garlic" to "2 cloves"
        ),
        steps = "1. Cook lentils with carrots, celery, and garlic.\n\n2. Add grilled chicken and simmer."
    ),
    Recipe(
        id = 10,
        title = "Tofu Stir-Fry",
        imageUrl = "https://www.themealdb.com/images/media/meals/v7go212693015623.jpg",
        protein = 30,
        calories = 400,
        preparationTime = "25 min",
        ingredients = listOf(
            "Tofu" to "200g",
            "Bell Peppers" to "1 cup",
            "Onions" to "1/2 cup",
            "Soy Sauce" to "1 tbsp",
            "Olive Oil" to "1 tbsp"
        ),
        steps = "1. Stir-fry vegetables in olive oil.\n\n2. Add tofu and soy sauce.\n\n3. Serve hot."
    ),
    Recipe(
        id = 11,
        title = "Grilled Shrimp Salad",
        imageUrl = "https://www.themealdb.com/images/media/meals/ryppsv1506820076.jpg",
        protein = 38,
        calories = 350,
        preparationTime = "20 min",
        ingredients = listOf(
            "Shrimp" to "200g",
            "Mixed Greens" to "1 cup",
            "Cucumber" to "1/2",
            "Tomatoes" to "1/2",
            "Olive Oil" to "1 tbsp",
            "Lemon" to "1"
        ),
        steps = "1. Grill shrimp.\n\n2. Chop vegetables.\n\n3. Mix together and drizzle with olive oil and lemon."
    ),
    Recipe(
        id = 12,
        title = "Beef Stir-Fry with Veggies",
        imageUrl = "https://www.themealdb.com/images/media/meals/wyxwuy1511454976.jpg",
        protein = 40,
        calories = 450,
        preparationTime = "25 min",
        ingredients = listOf(
            "Beef Sirloin" to "200g",
            "Broccoli" to "1 cup",
            "Bell Peppers" to "1 cup",
            "Soy Sauce" to "2 tbsp",
            "Olive Oil" to "1 tbsp"
        ),
        steps = "1. Stir-fry beef in olive oil.\n\n2. Add vegetables and soy sauce.\n\n3. Serve hot."
    ),
    Recipe(
        id = 13,
        title = "Cottage Cheese & Pineapple Bowl",
        imageUrl = "https://www.themealdb.com/images/media/meals/6lkq5c1588139686.jpg",
        protein = 28,
        calories = 250,
        preparationTime = "5 min",
        ingredients = listOf(
            "Cottage Cheese" to "1 cup",
            "Pineapple" to "1/2 cup",
            "Almonds" to "1/4 cup"
        ),
        steps = "1. Combine cottage cheese and pineapple.\n\n2. Top with almonds."
    ),
    Recipe(
        id = 14,
        title = "Chickpea and Spinach Curry",
        imageUrl = "https://www.themealdb.com/images/media/meals/wvpspp1511383582.jpg",
        protein = 22,
        calories = 350,
        preparationTime = "30 min",
        ingredients = listOf(
            "Chickpeas" to "1 cup",
            "Spinach" to "2 cups",
            "Coconut Milk" to "1/2 cup",
            "Curry Powder" to "1 tbsp",
            "Olive Oil" to "1 tbsp"
        ),
        steps = "1. Sauté spinach in olive oil.\n\n2. Add chickpeas and curry powder.\n\n3. Stir in coconut milk and simmer."
    ),
    Recipe(
        id = 15,
        title = "Baked Salmon with Asparagus",
        imageUrl = "https://www.themealdb.com/images/media/meals/xvpxxx1511637295.jpg",
        protein = 45,
        calories = 500,
        preparationTime = "30 min",
        ingredients = listOf(
            "Salmon Fillet" to "200g",
            "Asparagus" to "1 cup",
            "Olive Oil" to "1 tbsp",
            "Lemon" to "1"
        ),
        steps = "1. Bake salmon in olive oil.\n\n2. Roast asparagus with lemon slices.\n\n3. Serve together."
    ),
    Recipe(
        id = 16,
        title = "Chicken & Broccoli Stir Fry",
        imageUrl = "https://www.themealdb.com/images/media/meals/rrtssw1511381582.jpg",
        protein = 38,
        calories = 450,
        preparationTime = "20 min",
        ingredients = listOf(
            "Chicken Breast" to "200g",
            "Broccoli" to "1 cup",
            "Soy Sauce" to "2 tbsp",
            "Olive Oil" to "1 tbsp"
        ),
        steps = "1. Stir-fry chicken and broccoli in olive oil.\n\n2. Add soy sauce and serve."
    ),
    Recipe(
        id = 17,
        title = "Turkey Meatballs with Zucchini Noodles",
        imageUrl = "https://www.themealdb.com/images/media/meals/nxjrwl1511382037.jpg",
        protein = 40,
        calories = 350,
        preparationTime = "35 min",
        ingredients = listOf(
            "Ground Turkey" to "200g",
            "Zucchini" to "2",
            "Egg" to "1",
            "Garlic" to "1 clove",
            "Olive Oil" to "1 tbsp"
        ),
        steps = "1. Make meatballs and bake.\n\n2. Spiralize zucchini into noodles.\n\n3. Sauté zucchini noodles and serve with meatballs."
    ),
    Recipe(
        id = 18,
        title = "Protein Smoothie",
        imageUrl = "https://www.themealdb.com/images/media/meals/uwxvup1511382960.jpg",
        protein = 30,
        calories = 250,
        preparationTime = "5 min",
        ingredients = listOf(
            "Protein Powder" to "1 scoop",
            "Banana" to "1",
            "Spinach" to "1/2 cup",
            "Almond Milk" to "1 cup"
        ),
        steps = "1. Blend all ingredients until smooth."
    ),
    Recipe(
        id = 19,
        title = "Grilled Chicken & Veggie Skewers",
        imageUrl = "https://www.themealdb.com/images/media/meals/rwuyqx1511383211.jpg",
        protein = 35,
        calories = 400,
        preparationTime = "30 min",
        ingredients = listOf(
            "Chicken Breast" to "200g",
            "Bell Peppers" to "1 cup",
            "Onions" to "1/2 cup",
            "Olive Oil" to "1 tbsp"
        ),
        steps = "1. Skewer chicken and vegetables.\n\n2. Grill until cooked through."
    ),
    Recipe(
        id = 20,
        title = "Black Bean & Quinoa Tacos",
        imageUrl = "https://www.themealdb.com/images/media/meals/ayxvqp1511383771.jpg",
        protein = 22,
        calories = 350,
        preparationTime = "15 min",
        ingredients = listOf(
            "Black Beans" to "1/2 cup",
            "Quinoa" to "1/4 cup",
            "Corn Tortillas" to "2",
            "Avocado" to "1/2",
            "Lime" to "1"
        ),
        steps = "1. Cook quinoa and black beans.\n\n2. Assemble tacos with quinoa, beans, and avocado.\n\n3. Squeeze lime over the top."
    ),
    Recipe(
        id = 21,
        title = "Lentil & Turkey Chili",
        imageUrl = "https://www.themealdb.com/images/media/meals/1sfrb41586530761.jpg",
        protein = 38,
        calories = 400,
        preparationTime = "45 min",
        ingredients = listOf(
            "Ground Turkey" to "200g",
            "Lentils" to "1/2 cup",
            "Tomatoes" to "1 can",
            "Onions" to "1/2",
            "Cumin" to "1 tsp"
        ),
        steps = "1. Brown turkey.\n\n2. Add lentils, tomatoes, onions, and spices.\n\n3. Simmer until lentils are soft."
    ),
    Recipe(
        id = 22,
        title = "Salmon with Sweet Potato Mash",
        imageUrl = "https://www.themealdb.com/images/media/meals/rfquvq1587618413.jpg",
        protein = 40,
        calories = 500,
        preparationTime = "35 min",
        ingredients = listOf(
            "Salmon Fillet" to "200g",
            "Sweet Potato" to "1 large",
            "Olive Oil" to "1 tbsp"
        ),
        steps = "1. Roast salmon and sweet potatoes.\n\n2. Mash sweet potatoes and serve with salmon."
    ),
    Recipe(
        id = 23,
        title = "Grilled Tofu & Veggie Bowl",
        imageUrl = "https://www.themealdb.com/images/media/meals/4m60gq1583307093.jpg",
        protein = 30,
        calories = 400,
        preparationTime = "30 min",
        ingredients = listOf(
            "Tofu" to "200g",
            "Broccoli" to "1 cup",
            "Carrots" to "1/2 cup",
            "Soy Sauce" to "1 tbsp",
            "Olive Oil" to "1 tbsp"
        ),
        steps = "1. Grill tofu and vegetables.\n\n2. Drizzle with soy sauce and serve."
    ),
    Recipe(
        id = 24,
        title = "Shrimp & Avocado Salad",
        imageUrl = "https://www.themealdb.com/images/media/meals/52z0r11587666678.jpg",
        protein = 35,
        calories = 380,
        preparationTime = "15 min",
        ingredients = listOf(
            "Shrimp" to "200g",
            "Avocado" to "1",
            "Mixed Greens" to "1 cup",
            "Olive Oil" to "1 tbsp",
            "Lime" to "1"
        ),
        steps = "1. Grill shrimp.\n\n2. Combine with avocado and greens.\n\n3. Drizzle with olive oil and lime."
    ),
)

@Composable
fun RecipeGridScreen(recipes: List<Recipe>, onClick: (Recipe) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        items(recipes) { recipe ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(185.dp), 
                shape = RoundedCornerShape(16.dp),
                onClick = { onClick(recipe) },
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.surface),
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(recipe.imageUrl),
                        contentDescription = recipe.title,
                        modifier = Modifier
                            .height(120.dp) // Fixed height for the image
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = recipe.title,
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(start = 8.dp, end = 8.dp)
                            .fillMaxWidth(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(7.dp))
                    Text(
                        "Protein: ${recipe.protein}g",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(start = 6.dp, end = 6.dp)
                            .fillMaxWidth(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
@Composable
fun RecipeDetailScreen(recipe: Recipe) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.background)
    ) {
        Image(
            painter = rememberAsyncImagePainter(recipe.imageUrl),
            contentDescription = recipe.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 22.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                recipe.title,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.headlineMedium.copy( lineHeight = 30.sp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.FitnessCenter,
                        contentDescription = "Protein",
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Protein: ${recipe.protein}g",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.labelSmall
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.LocalFireDepartment,
                        contentDescription = "Calories",
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Calories: ${recipe.calories} kcal",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Default.Timer,
                    contentDescription = "Time",
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "Time: ${recipe.preparationTime}",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.labelSmall
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.List,
                    contentDescription = "Ingredients",
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "Ingredients:",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodySmall,

                )
            }

            recipe.ingredients.forEach {
                Text(
                    "- ${it.first}: ${it.second}",
                    modifier = Modifier.padding(start = 32.dp, bottom = 7.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.labelMedium.copy(lineHeight = 22.sp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.ShoppingBasket,
                    contentDescription = "Preparation",
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Preparation:",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodySmall)
            }
            Text(
                recipe.steps,
                modifier = Modifier.padding(start = 32.dp),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.labelMedium.copy(lineHeight = 20.sp)
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}