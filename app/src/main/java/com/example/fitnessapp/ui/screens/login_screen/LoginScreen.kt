import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessapp.R
import com.example.fitnessapp.ui.theme.DarkGreySurface
import com.example.fitnessapp.ui.theme.GreenAccent
import com.example.fitnessapp.ui.theme.LightGreySurface


@Composable
fun LoginScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Logo and App Name at the top-left
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 12.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.app_logo), // Replace with your logo drawable
                        contentDescription = "App Logo",
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Fitness App",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Login Form at the center
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                // Login Title
                Text(
                    text = "Login",
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Email Field
                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text(text = "Email", color = Color.White) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = DarkGreySurface,
                        unfocusedContainerColor = DarkGreySurface,
                        focusedBorderColor = GreenAccent,
                        unfocusedBorderColor = LightGreySurface
                    ),
                    modifier = Modifier
                        .width(380.dp) // Set the width manually to 280dp or any value you prefer
                        .padding(horizontal = 16.dp), // Add padding if needed
                    shape = RoundedCornerShape(20.dp)
                )


                Spacer(modifier = Modifier.height(16.dp))
                //textFieldColors
                // Password Field
                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text(text = "Password", color = Color.White) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = DarkGreySurface,
                        unfocusedContainerColor = DarkGreySurface,
                        focusedBorderColor = GreenAccent,
                        unfocusedBorderColor = LightGreySurface
                    ),
                    modifier = Modifier
                        .width(380.dp) // Set the width manually to 280dp or any value you prefer
                        .padding(horizontal = 16.dp), // Add padding if needed
                    shape = RoundedCornerShape(20.dp),
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Login Button
                Button(
                    onClick = { /* Handle login logic */ },
                    modifier = Modifier

                        .height(50.dp).width(200.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(text = "Login", color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Signup Link
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Don't have an account? ", color = Color.White, fontSize = 14.sp)
                    Text(
                        text = "Signup",
                        color = Color.Green,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable { /* Navigate to signup screen */ }
                    )
                }
            }
        }
    }
}