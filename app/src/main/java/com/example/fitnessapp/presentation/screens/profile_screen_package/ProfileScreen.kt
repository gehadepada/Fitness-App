package com.example.fitnessapp.presentation.screens.profile_screen_package

import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fitnessapp.presentation.components.FailedLoadingScreen
import com.example.fitnessapp.presentation.viewModels.get_userData_viewModel.GetUserDataState
import com.example.fitnessapp.presentation.viewModels.get_userData_viewModel.GetUserDataViewModel

@Composable
fun ProfileScreen(
    onUser: () -> Unit,
    onPermissions: () -> Unit,
    onAbout: () -> Unit,
    onLogout: () -> Unit,
) {
    val items = listOf(
        ProfileItem("Change Theme", Icons.Default.Settings, onClick = {}),
        ProfileItem("Edit Your Details", Icons.Default.Edit, onClick = { onUser() }),
        ProfileItem("Third-party data", Icons.Default.SyncAlt, onClick = { }),
        ProfileItem("App Permissions", Icons.Default.Interests, onClick = { onPermissions() }),
        ProfileItem("About App", Icons.Default.Info, onClick = { onAbout() }),
        ProfileItem("Logout", Icons.Default.Logout, onClick = { onLogout() }),
    )

    val getUserDataViewModel = hiltViewModel<GetUserDataViewModel>()
    val getUserDataState by getUserDataViewModel.getUserDataState.collectAsStateWithLifecycle()

    when (getUserDataState) {
        is GetUserDataState.Error -> {
            FailedLoadingScreen(
                errorMessage = (getUserDataState as GetUserDataState.Error).error,
                onFailed = { getUserDataViewModel.getUserData() })
        }

        GetUserDataState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }

        GetUserDataState.None -> Unit
        is GetUserDataState.Success -> {
            val userInfo = (getUserDataState as GetUserDataState.Success).userInfo

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = Color.DarkGray,
                    modifier = Modifier
                        .fillMaxWidth(),
                    tonalElevation = 2.dp
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
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
                                text = userInfo.userName,
                                color = Color.White,
                                fontSize = 20.sp,
                                style = MaterialTheme.typography.labelLarge
                            )
                            Text(
                                text = "${userInfo.gender}  •  ${userInfo.height}cm  •  ${userInfo.weight}kg",
                                color = Color.Gray,
                                fontSize = 14.sp,
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }


                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onSecondary)
                ) {
                }

                items.forEach { item ->
                    ProfileListItem(title = item.title, icon = item.icon, item.onClick)
                }
            }
        }
    }
}

data class ProfileItem(val title: String, val icon: ImageVector, val onClick: () -> Unit = {})

@Composable
fun ProfileListItem(title: String, icon: ImageVector, onClick: () -> Unit = {}) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        color = Color.DarkGray,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        tonalElevation = 2.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, top = 12.dp, end = 0.dp, bottom = 12.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                color = Color.White
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.onSecondary)
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}