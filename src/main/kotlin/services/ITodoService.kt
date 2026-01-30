package services

interface ITodoService {
    fun showTodos()
    fun addTodo(title: String)
    fun removeTodo(id: Int)
    fun changeTodo(id: Int, newTitle: String, newStatus: String)
    fun getTodoById(idTodo: Int): entities.Todo?
}
