package com.example.fitnessapp.presentation.screens.mealimagecalorieschecker

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.launch
import java.net.UnknownHostException

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ScanFood() {
    val context = LocalContext.current
    var imageBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    var resultText by remember { mutableStateOf<String?>(null) }
    var errorText by remember { mutableStateOf<String?>(null) }

    var detectedFoodItems by remember { mutableStateOf<List<String>>(emptyList()) }
    var detectedCalories by remember { mutableStateOf(0) }

    val scope = rememberCoroutineScope()

    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            resultText = null
            errorText = null
            isLoading = true

            scope.launch {
                try {
                    val loader = ImageLoader(context)
                    val request = ImageRequest.Builder(context)
                        .data(uri)
                        .build()

                    val result = loader.execute(request)
                    val bitmap = (result as SuccessResult).drawable.toBitmap()
                    imageBitmap = bitmap

                    val base64 = bitmapToBase64(bitmap)
                    val serverResult = detectFoodFromServer(base64)

                    detectedFoodItems = serverResult?.items ?: emptyList()
                    detectedCalories = serverResult?.count ?: 0

                    resultText = if (serverResult != null) {
                        "Detected food items: ${detectedFoodItems.joinToString(", ")}\nCalories: $detectedCalories kcal"
                    } else {
                        "Couldn't recognize any food."
                    }
                } catch (e: UnknownHostException) {
                    errorText = "No internet connection. Please check your network."
                } catch (e: Exception) {
                    errorText = "Error: ${e.message ?: "Something went wrong."}"
                } finally {
                    isLoading = false
                }
            }
        }
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val bitmap = result.data?.extras?.get("data") as? Bitmap
            bitmap?.let {
                resultText = null
                errorText = null
                imageBitmap = it
                isLoading = true

                scope.launch {
                    try {
                        val base64 = bitmapToBase64(it)
                        val serverResult = detectFoodFromServer(base64)

                        detectedFoodItems = serverResult?.items ?: emptyList()
                        detectedCalories = serverResult?.count ?: 0

                        resultText = if (serverResult != null) {
                            "Detected food items: ${detectedFoodItems.joinToString(", ")}\nCalories: $detectedCalories kcal"
                        } else {
                            "Couldn't recognize any food."
                        }
                    } catch (e: UnknownHostException) {
                        errorText = "No internet connection. Please check your network."
                    } catch (e: Exception) {
                        errorText = "Error: ${e.message ?: "Something went wrong."}"
                    } finally {
                        isLoading = false
                    }
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Food Calorie Detector") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Button(
                    onClick = { showBottomSheet = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Choose Image")
                }

                Spacer(modifier = Modifier.height(16.dp))

                imageBitmap?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier
                            .size(240.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                when {
                    isLoading -> {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator()
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Analyzing food...", fontSize = 16.sp)
                        }
                    }

                    resultText != null -> {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant
                            ),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(
                                    text = "Detected Food Items",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )

                                Spacer(modifier = Modifier.height(12.dp))

                                FlowRow(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    detectedFoodItems.forEach { item ->
                                        Text(
                                            text = item,
                                            modifier = Modifier
                                                .border(
                                                    width = 1.dp,
                                                    color = MaterialTheme.colorScheme.primary,
                                                    shape = RoundedCornerShape(20.dp)
                                                )
                                                .background(
                                                    color = MaterialTheme.colorScheme.primary.copy(
                                                        alpha = 0.1f
                                                    ),
                                                    shape = RoundedCornerShape(20.dp)
                                                )
                                                .padding(
                                                    horizontal = 12.dp,
                                                    vertical = 6.dp
                                                ),
                                            fontSize = 14.sp
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = "Calories: $detectedCalories kcal",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                Toast.makeText(
                                    context,
                                    "Data ready to be passed: $detectedFoodItems, $detectedCalories kcal",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Add to My Diary")
                        }
                    }

                    errorText != null -> {
                        Text(
                            text = errorText ?: "",
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }

            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showBottomSheet = false },
                    sheetState = sheetState
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {
                                showBottomSheet = false
                                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                                cameraLauncher.launch(intent)
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Take Photo")
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = {
                                showBottomSheet = false
                                galleryLauncher.launch("image/*")
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Pick from Gallery")
                        }
                    }
                }
            }
        }
    )
}
