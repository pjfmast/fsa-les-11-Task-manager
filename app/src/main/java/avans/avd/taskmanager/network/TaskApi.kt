package avans.avd.taskmanager.network

import avans.avd.taskmanager.model.Task
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.put
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class TaskApi {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                encodeDefaults = true
                isLenient = true
                coerceInputValues = true
                ignoreUnknownKeys = true
            })
        }
        defaultRequest {
            host = "10.0.2.2"
            port = 8080
        }
    }

    suspend fun getAllTasks(): List<Task> = httpClient.get("tasks").body()

    suspend fun removeTask(task: Task) {
        httpClient.delete("tasks/${task.name}")
    }

    suspend fun updateTask(task: Task) {
        httpClient.put("tasks") {
            contentType(ContentType.Application.Json)
            setBody(task)
        }
    }

    suspend fun addTask(task: Task) {
        httpClient.post("tasks") {
            contentType(ContentType.Application.Json)
            setBody(task)
        }
    }
}