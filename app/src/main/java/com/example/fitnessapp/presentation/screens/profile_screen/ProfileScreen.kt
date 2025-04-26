package com.example.fitnessapp.presentation.screens.profile_screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessapp.ui.theme.FitnessAppTheme

@Composable
fun UserProfile() {
    val items = listOf(
        ProfileItem("App settings", Icons.Default.Settings),
        ProfileItem("Third-party data", Icons.Default.Sync),
        ProfileItem("Device permissions", Icons.Default.Security),
        ProfileItem("App permissions", Icons.Default.Lock),
        ProfileItem("Feedback", Icons.Default.Feedback),
        ProfileItem("Version 3.37.2i", Icons.Default.Info),
        ProfileItem("About this app", Icons.Default.Info)
    )

    var selectedIndex by remember { mutableIntStateOf(3) }

    Scaffold(
        bottomBar = {
            CustomBottomBar(
                selectedIndex = selectedIndex,
                onItemSelected = { selectedIndex = it }
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text("6635477650", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text("Male  •  170cm  •  20", color = Color.Gray, fontSize = 14.sp)
                }
                Spacer(modifier = Modifier.weight(1f))
                Icon(Icons.Default.Add, contentDescription = null, tint = Color.White)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.EmojiEvents, contentDescription = null, tint = Color(0xFFFF9800))
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Competition", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Text("Start a competition", color = Color.Gray, fontSize = 13.sp)
                    }
                    Button(onClick = { /* View competition */ }) {
                        Text("View")
                    }
                }
            }

            items.forEach { item ->
                ProfileListItem(title = item.title, icon = item.icon, )
                HorizontalDivider(thickness = 0.5.dp, color = Color.DarkGray)
            }
        }
    }
}

data class ProfileItem(val title: String, val icon: ImageVector)

@Composable
fun ProfileListItem(title: String, icon: ImageVector, onClick:() -> Unit = {}) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(vertical = 12.dp)
    ) {
        Icon(icon, contentDescription = null, tint = Color.White)
        Spacer(modifier = Modifier.width(16.dp))
        Text(title, color = Color.White, fontSize = 16.sp)
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            Icons.Default.Favorite,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.size(16.dp)
        )
    }
}

@Composable
fun CustomBottomBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    val items = listOf(
        Icons.Default.Home to "Home",
        Icons.Default.FitnessCenter to "Workout",
        Icons.Default.ShoppingCart to "Shop",
        Icons.Default.Person to "Profile"
    )

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(32.dp),
        color = Color(0xFF121212),
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, (icon, label) ->
                val animatedSize by animateDpAsState(
                    targetValue = if (index == selectedIndex) 30.dp else 24.dp,
                    label = "iconSize"
                )

                val animatedColor by animateColorAsState(
                    targetValue = if (index == selectedIndex) Color(0xFF7CFC00) else Color.Gray,
                    label = "iconColor"
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .clickable { onItemSelected(index) }
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        tint = animatedColor,
                        modifier = Modifier.size(animatedSize)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = label,
                        fontSize = 11.sp,
                        color = animatedColor,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun UserProfilePreview() {
    FitnessAppTheme {
        UserProfile()
    }
}
