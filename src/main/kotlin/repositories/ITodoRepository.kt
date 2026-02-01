package repositories

import entities.Todo

interface ITodoRepository {
    fun getAllTodos(): List<Todo>
    fun addTodo(newTodo: Todo)
    fun removeTodo(id: Int): Boolean
    fun updateTodo(id: Int, title: String, isFinished: Boolean): Boolean
    fun searchTodo(keyword: String): List<Todo>
    fun sortTodo(type: Int, isAscending: Boolean)
}