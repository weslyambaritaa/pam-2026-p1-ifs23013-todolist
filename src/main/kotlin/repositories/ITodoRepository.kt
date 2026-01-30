package repositories

import entities.Todo
import jdk.internal.org.jline.utils.Status

interface ITodoRepository {
    fun getAllTodos(): List<Todo>
    fun addTodo(newTodo: Todo)
    fun removeTodo(id: Int): Boolean
    fun changeTodo(id: Int, newTitle: String, newStatus: String): Boolean
}