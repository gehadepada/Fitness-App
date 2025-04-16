package com.example.mealimagecalorieschecker

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current
            var imageBitmap by remember { mutableStateOf<android.graphics.Bitmap?>(null) }
            var isLoading by remember { mutableStateOf(false) }
            var resultText by remember { mutableStateOf<String?>(null) }
            var errorText by remember { mutableStateOf<String?>(null) }

            val scope = rememberCoroutineScope()

            val launcher = rememberLauncherForActivityResult(
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
                            resultText = serverResult?.let {
                                "🍎 Food: ${it.items.joinToString(", ")}\n🔥 Calories: ${it.count} kcal"
                            } ?: "Couldn't recognize any food."
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
                            onClick = { launcher.launch("image/*") },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Select Image")
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
                                        modifier = Modifier
                                            .padding(16.dp),
                                        horizontalAlignment = Alignment.Start
                                    ) {
                                        Text("Analysis Result:", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text(resultText ?: "", fontSize = 16.sp)
                                    }
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
                }
            )
        }
    }
}
