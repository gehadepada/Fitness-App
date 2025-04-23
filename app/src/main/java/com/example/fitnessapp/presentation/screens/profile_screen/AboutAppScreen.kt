package com.example.fitnessapp.ui.settings // Adjust package name as needed

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
// import androidx.compose.foundation.Image // Uncomment if you add an Image
// import androidx.compose.ui.res.painterResource // Uncomment for Image resource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack // Keep using AutoMirrored for layout direction handling
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.R
import com.example.fitnessapp.presentation.navigation.Screens
import com.example.fitnessapp.ui.theme.FitnessAppTheme


/**
 * Screen to display information about the application itself.
 * @param onNavigateBack Callback function to navigate back to the previous screen.
 */
@OptIn(ExperimentalMaterial3Api::class) // Required for TopAppBar
@Composable
fun AboutAppScreen(onNavigateBack: () -> Unit) {
    // Retrieve version name and code, e.g., from BuildConfig
    // Example: val appVersion = BuildConfig.VERSION_NAME
    // Example: val buildNumber = BuildConfig.VERSION_CODE.toString()
    val appVersion = "3.37.2" // Example version, replace with dynamic retrieval if possible
    val buildNumber = "1024" // Example build number

    Scaffold(
        topBar = {
            TopAppBar(
                // Title translated to English
                title = { Text("About This App") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors( // Use theme colors
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "App Icon",
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))


            Text(
                text = R.string.app_name.toString(),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(

                text = "Version: $appVersion (Build $buildNumber)",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(24.dp))

            Text(

                text = "An app for tracking fitness and workouts.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))


            TextButton(onClick = {  }) {
                Text("Privacy Policy")
            }
            TextButton(onClick = { }) {
                Text("Terms of Service")
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(

                text = "© ${java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)} Your Company or Developer Nagy Osman. All rights reserved.",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutAppScreenPreview() {
    FitnessAppTheme {
        AboutAppScreen(onNavigateBack = { Screens.ProfileScreen })
    }
}
