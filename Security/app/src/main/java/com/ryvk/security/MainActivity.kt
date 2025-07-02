package com.ryvk.security

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryvk.security.ui.theme.SecurityTheme
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SecurityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UI(innerPadding)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UI(innerPaddingValues: PaddingValues = PaddingValues()) {

    val cryptoManager = CryptoManager()
    val context = LocalContext.current

    var messageToEncrypt by remember {
        mutableStateOf("")
    }
    var messageToDecrypt by remember {
        mutableStateOf("")
    }

    Column (
        modifier = Modifier
            .padding(innerPaddingValues)
            .fillMaxSize()
            .padding(32.dp)
    ){
        TextField(
            value = messageToEncrypt,
            onValueChange = { messageToEncrypt = it },
            placeholder = { Text(text = "Encrypt string") },
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(
            modifier = Modifier
                .height(8.dp)
        )
        Row {
            Button(onClick = {
                val bytes = messageToEncrypt.encodeToByteArray()
                val file = File(context.filesDir, "secret.txt")
                if(!file.exists()){
                    file.createNewFile()
                }
                val fos = FileOutputStream(file)
                messageToDecrypt = cryptoManager.encrypt(
                    bytes = bytes,
                    outputStream = fos
                ).decodeToString()
                messageToEncrypt = ""
            }) {
                Text(text = "Encrypt")
            }
            Spacer(
                modifier = Modifier
                    .width(16.dp)
            )
            Button(onClick = {
                val file = File(context.filesDir, "secret.txt")
                if(file.exists()){
                    messageToEncrypt = cryptoManager.decrypt(
                        inputStream = FileInputStream(file)
                    ).decodeToString()
                }
            }) {
                Text(text = "Decrypt")
            }
        }
        Text(
            text = messageToDecrypt,
        )
    }
}