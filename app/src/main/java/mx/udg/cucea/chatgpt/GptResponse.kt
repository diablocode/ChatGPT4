package mx.udg.cucea.chatgpt

import com.google.gson.annotations.SerializedName
import okhttp3.ResponseBody
import retrofit2.Response
import java.util.stream.Stream

data class GptResponse(
    @SerializedName("choices")  val choices: List<Choice>
)
data class Choice(
    @SerializedName("message") val message: Message
)