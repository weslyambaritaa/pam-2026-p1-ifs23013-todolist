package services

import entities.Todo
import repositories.ITodoRepository

class TodoService(private val todoRepository: ITodoRepository) : ITodoService {

    override fun showTodos() {
        val todos = todoRepository.getAllTodos()
        println("Daftar Todo:")
        var counter = 0
        for (todo in todos) {
            counter++
            println(todo)
        }
        if (counter <= 0) {
            println("- Data todo belum tersedia!")
        }
    }

    override fun addTodo(title: String) {
        val newTodo = Todo(title = title)
        todoRepository.addTodo(newTodo)
    }

    override fun removeTodo(id: Int) {
        val success = todoRepository.removeTodo(id)
        if (!success) {
            println("[!] Gagal menghapus todo dengan ID: $id.")
        }
    }

    override fun updateTodo(id: Int, title: String, isFinished: Boolean): Boolean {
        return todoRepository.updateTodo(id, title, isFinished)
    }

    // Fungsi search yang sudah diperbaiki
    override fun searchTodo(keyword: String): List<Todo> {
        return todoRepository.searchTodo(keyword)
    }


    override fun sortTodo(type: Int, isAscending: Boolean) {
        todoRepository.sortTodo(type, isAscending)
    }
}