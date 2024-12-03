package avans.avd.taskmanager.data

import avans.avd.taskmanager.model.Task
import avans.avd.taskmanager.network.TaskApi

class TaskRepository(private val taskApi: TaskApi) {
    suspend fun getAllTasks(): List<Task> = taskApi.getAllTasks()
    suspend fun removeTask(Task: Task) = taskApi.removeTask(Task)
    suspend fun updateTask(Task: Task) = taskApi.updateTask(Task)
    suspend fun addTask(Task: Task) = taskApi.addTask(Task)
}