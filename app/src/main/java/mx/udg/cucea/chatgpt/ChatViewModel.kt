package mx.udg.cucea.chatgpt

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class ChatViewModel: ViewModel() {
    var answer by mutableStateOf("")


    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openai.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(OpenAIApiService::class.java)

    fun sendRequest(question: String) {
        val request = GptRequest(
            //prompt = question,
            //max_tokens = 100,
            model = "gpt-3.5-turbo",
            messages = listOf(
                Message("system", question),
            ),
            stream = false

        )

        //viewModelScope.launch(Dispatchers.IO) {
//            isLoading = true
        val call = api.getCompletion(request)
        try {
            val response = call.execute()
//            isLoading = false

            if (response.isSuccessful) {

                val ch = response.body()?.choices

                val choice = ch?.get(0)
                //viewModelScope.launch(Dispatchers.Main) {
                choice?.message?.let {
                    answer = it.content //+ " ->" + it.role
                }
            } else {
                //viewModelScope.launch(Dispatchers.Main) {
                answer = "Error: ${response.code()} - ${response.errorBody()?.string()}"
                Log.d("mensaje", answer)
                //}
            }
        } catch (exception: Exception){
            answer = exception?.message.toString()
        }
        //}
        Log.d("chat2", answer)
    }

}