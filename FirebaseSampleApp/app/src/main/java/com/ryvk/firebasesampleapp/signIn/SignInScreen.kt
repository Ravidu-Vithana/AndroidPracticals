package com.ryvk.firebasesampleapp.signIn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ryvk.firebasesampleapp.R
import com.ryvk.firebasesampleapp.signUp.SignUpViewModel
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun SignInScreen(navController: NavController = rememberNavController(), viewModel: SignInViewModel = SignInViewModel()) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()

    val context = LocalContext.current

    Scaffold (modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background)
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 16.dp,
                    ),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Sign In",
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 16.dp,
                    ),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Email",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    modifier = Modifier
                        .padding(
                            vertical = 8.dp
                        )
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),

                    )
                Text(
                    text = "Password",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    modifier = Modifier
                        .padding(
                            vertical = 8.dp
                        )
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                )
            }
            Row (
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Button(onClick = {
                    //
                }) {
                    Text("Sign In")
                }
            }
            HorizontalDivider(
                modifier = Modifier
                    .padding(
                        vertical = 8.dp
                    ),
                thickness = 1.dp,
            )
            Row (
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                IconButton(onClick = {
                    scope.launch {
                        viewModel.signInWithGoogleButton(context = context)
                    }
                }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_google),
                        contentDescription = "Google Sign In",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .padding(4.dp)
                    )
                }
                Text(
                    text = "or",
                    modifier = Modifier
                        .padding(
                            horizontal = 2.dp
                        )
                )
                IconButton(onClick = {

                }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_microsoft),
                        contentDescription = "Google Sign In",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .padding(4.dp)
                    )
                }
            }
        }
    }
}