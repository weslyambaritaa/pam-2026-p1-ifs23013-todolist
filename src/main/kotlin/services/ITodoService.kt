package services

import entities.Todo
import javax.print.attribute.standard.Finishings

interface ITodoService {
    fun showTodos()
    fun addTodo(title: String)
    fun removeTodo(id: Int)
    fun updateTodo(id: Int, title: String, isFinished: Boolean): Boolean
    fun searchTodo(keyword: String): List<Todo>
    fun sortTodo(type: Int, isAscending: Boolean)
}
