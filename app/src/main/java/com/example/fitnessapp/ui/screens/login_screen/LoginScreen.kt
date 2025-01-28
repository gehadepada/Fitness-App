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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessapp.R
import com.example.fitnessapp.ui.components.TopBarWithLogo
import com.example.fitnessapp.ui.theme.DarkGreySurface
import com.example.fitnessapp.ui.theme.FitnessAppTheme
import com.example.fitnessapp.ui.theme.GreenAccent
import com.example.fitnessapp.ui.theme.LightGreySurface


@Composable
fun LoginScreen(onLogin:()-> Unit = {}, goToSignUp:()->Unit = {}) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Logo and App Name at the top-left
            TopBarWithLogo()

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
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Email Field
                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text(text = "Email", color = MaterialTheme.colorScheme.onSurface, style = MaterialTheme.typography.titleSmall) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = DarkGreySurface,
                        unfocusedContainerColor = DarkGreySurface,
                        focusedBorderColor = GreenAccent,
                        unfocusedBorderColor = LightGreySurface
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp), // Add padding if needed
                    shape = RoundedCornerShape(20.dp)
                )


                Spacer(modifier = Modifier.height(16.dp))
                //textFieldColors
                // Password Field
                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text(text = "Password", color = MaterialTheme.colorScheme.onSurface, style = MaterialTheme.typography.titleSmall) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = DarkGreySurface,
                        unfocusedContainerColor = DarkGreySurface,
                        focusedBorderColor = GreenAccent,
                        unfocusedBorderColor = LightGreySurface
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp), // Add padding if needed
                    shape = RoundedCornerShape(20.dp),
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Login Button
                Button(
                    onClick = {
                        // if email and password valid
//                        if() {
//
//                        } else {
//
//                        }
                        onLogin()
                    },
                    modifier = Modifier

                        .height(50.dp)
                        .width(200.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(text = "Login", color = MaterialTheme.colorScheme.onSurface, style = MaterialTheme.typography.bodySmall)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Signup Link
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Don't have an account? ", style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.onBackground)
                    Text(
                        text = "Signup",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.clickable {
                            goToSignUp()
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Prev() {
    FitnessAppTheme {
        LoginScreen()
    }
}