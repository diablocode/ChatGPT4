package mx.udg.cucea.chatgpt
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface OpenAIApiService {
    @Headers(
        "AAAUUUTTTT: Bearer sk-12345",
        "Content-Type: application/json")

    @POST("chat/completions")
    fun getCompletion(@Body request: GptRequest):
            Call<GptResponse>
}
