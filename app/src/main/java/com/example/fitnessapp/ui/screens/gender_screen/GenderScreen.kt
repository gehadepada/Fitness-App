package com.example.fitnessapp.ui.screens.gender_screen


import androidx.compose.material3.Icon
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
<<<<<<< HEAD
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessapp.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Gender() {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.app_logo),
                        contentDescription = null,
                        modifier = Modifier.size(35.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            },
=======
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
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessapp.ui.components.DefaultButton
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
            style = MaterialTheme.typography.headlineMedium,
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

>>>>>>> cc38a0b74d3d62590d421b3909a495ae4f9f6142

            colors = TopAppBarDefaults.topAppBarColors(
                MaterialTheme.colorScheme.background
            )
<<<<<<< HEAD
        )
        val items = listOf(
            R.drawable.male,
            R.drawable.female,

        )
        val goals = listOf("Male", "Female")
        val selectedIndex = remember { mutableStateOf(-1) }

        Column(Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(bottom = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(modifier = Modifier
                .padding(bottom = 30.dp),
                text = "Set Your Goals",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.height(10.dp))

            items.forEachIndexed { index, image ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .border(
                                width = 2.dp,
                                color = if (selectedIndex.value == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface ,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .clickable {
                                selectedIndex.value = index
                            }
                            .padding(bottom = 5.dp)
                            .background(color = MaterialTheme.colorScheme.onSurface)
                    ) {
                        Image(
                            painter = painterResource(id = image),
                            contentDescription = "Goal Image",
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Text(modifier = Modifier.padding(top = 5.dp),
                        text = goals[index],
                        fontSize = 12.sp,
                        color =MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
=======
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

//        OutlinedButton(modifier = Modifier
//            .clickable {}
//            .border(
//                BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
//                RoundedCornerShape(20.dp)
//            ),
//            colors = ButtonDefaults.buttonColors(
//                containerColor = MaterialTheme.colorScheme.background,
//                contentColor = MaterialTheme.colorScheme.onSurface
//            ),
//            onClick = {
//                onGender("")
//            }
//        ) {
//            Text(
//                text = "Continue", color = MaterialTheme.colorScheme.onBackground,
//                style = MaterialTheme.typography.displaySmall
//            )
//        }

        DefaultButton(onClick = { onGender("") })
    }
}

@Preview
@Composable
private fun Prev() {
    FitnessAppTheme {
        GenderScreen({})
>>>>>>> cc38a0b74d3d62590d421b3909a495ae4f9f6142
    }
    Button(onClick = {},
        modifier = Modifier
        .fillMaxWidth()
        .height(56.dp)) {
        Text(text = "Continue", fontSize = 18.sp)
    }

}