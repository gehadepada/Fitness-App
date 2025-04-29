package com.example.fitnessapp.presentation.screens.profile_screen

import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessapp.ui.theme.FitnessAppTheme

@Composable
fun ProfileScreen(
    onUser: () -> Unit,
    onPermissions: () -> Unit,
    onVersion: () -> Unit,
    onAbout: () -> Unit
) {
    val items = listOf(
        ProfileItem("App settings", Icons.Default.Settings, onClick = {  }),
        ProfileItem("Third-party data", Icons.Default.Sync, onClick = { }),
        ProfileItem("Device permissions", Icons.Default.Security, onClick = {}),
        ProfileItem("App permissions", Icons.Default.Lock, onClick = { onPermissions() }),
        ProfileItem("Feedback", Icons.Default.Feedback, onClick = { }),
        ProfileItem("Version 3.37.2i", Icons.Default.Info, onClick = { onVersion() }),
        ProfileItem("About this app", Icons.Default.Info, onClick = { onAbout() })
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onUser()
                }
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    "6635477650",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
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
                    Text(
                        "Competition",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text("Start a competition", color = Color.Gray, fontSize = 13.sp)
                }
                Button(onClick = { /* View competition */ }) {
                    Text("View")
                }
            }
        }

        items.forEach { item ->
            ProfileListItem(title = item.title, icon = item.icon, item.onClick)
            HorizontalDivider(thickness = 0.5.dp, color = Color.DarkGray)
        }
    }
}


data class ProfileItem(val title: String, val icon: ImageVector, val onClick: () -> Unit = {})

@Composable
fun ProfileListItem(title: String, icon: ImageVector, onClick: () -> Unit = {}) {
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
@Preview(showBackground = true, showSystemUi = true)
fun UserProfilePreview() {
    FitnessAppTheme {
        ProfileScreen({},{},{},{})
    }
}
