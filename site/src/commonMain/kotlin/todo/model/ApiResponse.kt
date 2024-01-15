package todo.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class ApiResponse {
    @Serializable
    @SerialName("name")
    object Idle : ApiResponse()

    @SerialName("success")
    @Serializable
    data class Success(val data: List<Person>) : ApiResponse()

    @SerialName("error")
    @Serializable
    data class Error(val errorMessage: String) : ApiResponse()
}