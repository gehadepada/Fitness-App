import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.ui.components.DefaultButton
import com.example.fitnessapp.ui.components.TopBarWithLogo
import com.example.fitnessapp.ui.screens.signup_screen.components.CustomOutlinedTextField
import com.example.fitnessapp.ui.theme.FitnessAppTheme


@Composable
fun LoginScreen(onLogin: () -> Unit = {}, goToSignUp: () -> Unit = {}) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }


    Column {
        // Logo and App Name at the top-left
        TopBarWithLogo()

        // Login Form at the center
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Login Title
            Text(
                text = "Login",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Email Field
            CustomOutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    if (it.contains("@") && it.contains(".")) emailError = ""
                },
                label = "Email",
                errorMessage = emailError
            )


            Spacer(modifier = Modifier.height(16.dp))
            //textFieldColors
            // Password Field
            CustomOutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    if (it.isNotBlank()) passwordError = ""

                },
                label = "password",
                isPassword = true,
                errorMessage = passwordError
            )

            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                // Login button
                DefaultButton(
                    text = "Login",
                    color = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    onClick = {
                        // Validation
                        emailError =
                            if (!email.contains("@") || !email.contains(".")) "Invalid email format" else ""
                        passwordError =
                            if (password.length < 6) "Password must be at least 6 characters" else ""

                        // If no errors, proceed with signup
                        if (emailError.isEmpty() && passwordError.isEmpty()) {
                            onLogin()
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Signup Link
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Don't have an account?  ",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "Signup",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.clickable {
                        goToSignUp()
                    }
                )
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