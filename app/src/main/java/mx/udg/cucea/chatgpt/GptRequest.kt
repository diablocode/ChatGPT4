package mx.udg.cucea.chatgpt

import com.google.gson.annotations.SerializedName

data class GptRequest(
    //@SerializedName("prompt") val prompt: String,
    //@SerializedName("max_tokens") val max_tokens: Int,
    @SerializedName("model") val model: String,
    @SerializedName("messages") val messages:List<Message>,
    @SerializedName("stream") val stream: Boolean = false,

)