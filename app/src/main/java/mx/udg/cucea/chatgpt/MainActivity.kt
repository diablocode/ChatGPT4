package mx.udg.cucea.chatgpt

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mx.udg.cucea.chatgpt.ui.theme.ChatGPTTheme
import okhttp3.Call
import okhttp3.Callback

class MainActivity : ComponentActivity() {
    var a by mutableStateOf("")
    var dato by mutableStateOf("")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatGPTTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {

        val chatViewModel = ChatViewModel()

        Column {
            TextField(
                value = dato,
                onValueChange = { dato = it },
                label = { Text("inserta dato") }
            )
            Button(onClick = {
                chatViewModel.viewModelScope.launch(Dispatchers.IO) {
                    chatViewModel.sendRequest("crea un ensayo corto con los siguientes datos: $dato")
                    Log.i("chat", chatViewModel.answer)
                    a = chatViewModel.answer

                }
            }) {
                Text(text = "Crea un ensayo")
            }
            Text(
                text = "$a",
                modifier = modifier
            )
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        ChatGPTTheme {
            Greeting("Android")
        }
    }
}