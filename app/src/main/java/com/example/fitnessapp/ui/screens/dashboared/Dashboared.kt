package com.example.fitnessapp.ui.screens.dashboared



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessapp.R

@Composable
fun CircularProgressIndicator(
    progress: Float,
    remainingText: String,
    modifier: Modifier = Modifier,
    strokeWidth: Float = 12f,
    color: Color = Color(android.graphics.Color.parseColor("#29E33C"))
) {
    Box(contentAlignment = Alignment.Center, modifier = modifier) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val diameter = size.minDimension
            val radius = diameter / 2
            val angle = 360 * progress

            drawArc(
                color = Color.LightGray,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )

            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = angle,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )
        }

        Text(
            text = remainingText,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


@Composable
fun DiscoverButton(
    icon: Painter,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconColor: Color = Color(android.graphics.Color.parseColor("#29E33C")) // Add iconColor parameter
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .size(110.dp)
            .clickable(onClick = onClick)
            .padding(8.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp))
    ) {
        Image(
            painter = icon,
            contentDescription = label,
            colorFilter = ColorFilter.tint(iconColor), // Apply the color tint
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun DiscoverSection() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Discover",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            DiscoverButton(
                icon = painterResource(id = R.drawable.moon_svgrepo_com),
                label = "Sleep",
                onClick = { /* Handle click */ }
            )
            DiscoverButton(
                icon = painterResource(id = R.drawable.dinner_icon),
                label = "Recipes",
                onClick = { /* Handle click */ }
            )
            DiscoverButton(
                icon = painterResource(id = R.drawable.baseline_fitness_center_24),
                label = "Workouts",
                onClick = { /* Handle click */ }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            DiscoverButton(
                icon = painterResource(id = R.drawable.moon_svgrepo_com),
                label = "Sync up",
                onClick = { /* Handle click */ }
            )
            DiscoverButton(
                icon = painterResource(id = R.drawable.moon_svgrepo_com),
                label = "Friends",
                onClick = { /* Handle click */ }
            )
            DiscoverButton(
                icon = painterResource(id = R.drawable.moon_svgrepo_com),
                label = "Community",
                onClick = { /* Handle click */ }
            )
        }
    }
}






@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_person_24),
                contentDescription = "Header Image",
                modifier = Modifier
                    .height(35.dp)
                    .width(35.dp)
                    .clip(CircleShape)
                    .background(Color(android.graphics.Color.parseColor("#29E33C")))
            )

            Text(
                text = "myfitnesspal",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold,

                )

            Image(
                painter = painterResource(id = R.drawable.baseline_notifications_24),
                contentDescription = "Header Image",
                colorFilter = ColorFilter.tint(Color(android.graphics.Color.parseColor("#29E33C"))),
                modifier = Modifier
                    .height(30.dp)
                    .width(30.dp)

            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Today",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                color = Color.White

            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .border(BorderStroke(1.dp, Color.LightGray))
                    .padding(horizontal = 5.dp, vertical = 3.dp)
                    .background(Color.White)
            ) {
                Text(
                    text = "Edit",
                    color = Color.Black,
                    style = MaterialTheme.typography.headlineMedium,
                    fontSize = 20.sp
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(all = 15.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .fillMaxWidth()
                .border(BorderStroke(2.dp, Color.LightGray))
                .padding(16.dp)
        ) {
            Text(
                text = "Calories",
                color = Color.Black,
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "Remaining = Goal - Food + Exercise",
                color = Color.Black,
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 20.sp,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CircularProgressIndicator(
                    progress = 1000f / 3000f,
                    remainingText = "1,680",
                    modifier = Modifier.size(120.dp)
                )
                Spacer(modifier = Modifier.width(50.dp))
                Column(verticalArrangement = Arrangement.SpaceEvenly) {
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_flag_24),
                            contentDescription = "Header Image",
                            modifier = Modifier
                                .height(30.dp)
                                .width(30.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = "Base Goal",
                            style = MaterialTheme.typography.headlineMedium,
                            fontSize = 17.sp,
                            color = Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.dinner_icon),
                            contentDescription = "Header Image",
                            colorFilter = ColorFilter.tint(Color(android.graphics.Color.parseColor("#29E33C"))),
                            modifier = Modifier
                                .height(30.dp)
                                .width(30.dp)

                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Food (377)",
                            style = MaterialTheme.typography.headlineMedium,
                            fontSize = 17.sp,
                            color = Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row {

                        Image(
                            painter = painterResource(id = R.drawable.hot_icon),
                            contentDescription = "Header Image",
                            modifier = Modifier
                                .height(30.dp)
                                .width(30.dp)
                        )
                        Text(
                            text = "Exercise (20)",
                            style = MaterialTheme.typography.headlineMedium,
                            fontSize = 17.sp,
                            color = Color.Black
                        )
                    }
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center, // Space evenly between items
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 130.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_call_received_24),
                contentDescription = "Header Image",
                colorFilter = ColorFilter.tint(Color(android.graphics.Color.parseColor("#29E33C"))),
                modifier = Modifier
                    .height(40.dp)
                    .width(40.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.baseline_call_received_24),
                contentDescription = "Header Image",
                colorFilter = ColorFilter.tint(Color(android.graphics.Color.parseColor("#29E33C"))),
                modifier = Modifier
                    .height(40.dp)
                    .width(40.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.baseline_call_received_24),
                contentDescription = "Header Image",
                colorFilter = ColorFilter.tint(Color(android.graphics.Color.parseColor("#29E33C"))),
                modifier = Modifier
                    .height(40.dp)
                    .width(40.dp)
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier

                .padding(all = 15.dp)
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .background(Color.White)
                .border(BorderStroke(2.dp, Color.LightGray))
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_add_circle_24),
                contentDescription = "Header Image",
                modifier = Modifier
                    .height(70.dp)
                    .width(70.dp)

            )
            Text(
                text = "Add Hapit",
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 30.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,

                )

        }

        DiscoverSection()
    }



}

@Composable
@Preview
fun PreviewAll() {
    ProfileScreen()
}
