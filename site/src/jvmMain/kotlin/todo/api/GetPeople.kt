package todo.api

import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.http.setBodyText
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import todo.model.ApiResponse
import todo.model.Person


private val people =
    listOf(Person(name = "Zoran", age = 34), Person(name = "Moeko", age = 29), Person(name = "Nagi", age = 4))

@Api
fun GetPeople(context: ApiContext) {


    try {
        val amountOfPeople = context.req.params.getValue("peopleCount").toInt()
        println("amountOfPeople $amountOfPeople: people $people.size ")

        if (amountOfPeople < 0) {
            context.res.setBodyText(
                Json.encodeToString<ApiResponse>(
                    ApiResponse.Error(errorMessage = "Invalid request: Negative number of people requested.")
                )
            )
        } else if (amountOfPeople > people.size) {
            context.res.setBodyText(
                Json.encodeToString<ApiResponse>(
                    ApiResponse.Error(errorMessage = "The requested amount doesn't exist.")
                )
            )
        } else {
            context.res.setBodyText(
                Json.encodeToString<ApiResponse>(
                    ApiResponse.Success(data = people.take(amountOfPeople))
                )
            )
        }
    } catch (e: Exception) {
        context.res.setBodyText(
            Json.encodeToString<ApiResponse>(
                ApiResponse.Error(errorMessage = e.message.toString())
            )
        )
    }
}
