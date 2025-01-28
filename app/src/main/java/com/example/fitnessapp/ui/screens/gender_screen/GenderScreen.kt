package com.example.fitnessapp.ui.screens.gender_screen


import androidx.compose.material3.Icon
import com.example.fitnessapp.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessapp.ui.components.TopBarWithLogo
import com.example.fitnessapp.ui.theme.FitnessAppTheme


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun GenderScreen(onGender: (String) -> Unit) {

    var borderColor by remember { mutableStateOf(Color.Gray) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(bottom = 100.dp)
            .background(MaterialTheme.colorScheme.background),

        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        TopBarWithLogo()

        Text(
            text = "What's your gender?",
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .padding(bottom = 16.dp, top = 20.dp)
                .weight(1f)
        )

        Column(modifier = Modifier.weight(4f)) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.mars_solid),
                contentDescription = "Male",
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .clickable {}
                    .pointerInteropFilter { motionEvent ->
                        when (motionEvent.action) {
                            android.view.MotionEvent.ACTION_HOVER_ENTER -> {
                                borderColor = Color.Green
                                true
                            }

                            android.view.MotionEvent.ACTION_HOVER_EXIT -> {
                                borderColor = Color.Gray
                                true
                            }

                            else -> false
                        }
                    }


                    .border(BorderStroke(2.dp, borderColor), RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .padding(20.dp)


            )
            Spacer(modifier = Modifier.height(50.dp))
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.venus_solid),
                contentDescription = "Female",
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .clickable {}
                    .pointerInteropFilter { motionEvent ->
                        when (motionEvent.action) {
                            android.view.MotionEvent.ACTION_HOVER_ENTER -> {
                                borderColor = Color.Green
                                true
                            }

                            android.view.MotionEvent.ACTION_HOVER_EXIT -> {
                                borderColor = Color.Gray
                                true
                            }

                            else -> false
                        }
                    }
                    .border(BorderStroke(2.dp, borderColor), RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(20.dp)
            )
        }
        Spacer(modifier = Modifier.height(32.dp))

        OutlinedButton(modifier = Modifier
            .clickable {}
            .border(
                BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                RoundedCornerShape(20.dp)
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
            onClick = {
                onGender("")
            }
        )

        {
            Text(
                text = "Continue", color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview
@Composable
private fun Prev() {
    FitnessAppTheme {
        GenderScreen({})
    }
}