package repositories

import entities.Todo

class TodoRepository : ITodoRepository {
    val data = ArrayList<Todo>()


    override fun getAllTodos(): List<Todo> {
        return data
    }

    override fun addTodo(newTodo: Todo) {
        data.add(newTodo)
    }

    override fun removeTodo(id: Int): Boolean {
        val targetTodo = data.find { it.id == id } ?: return false
        data.remove(targetTodo)
        return true
    }

    override fun updateTodo(id: Int, title: String, isFinished: Boolean): Boolean {
        val targetTodo = data.find { it.id == id } ?: return false
        targetTodo.title = title
        targetTodo.isFinished = isFinished
        return true
    }

    override fun searchTodo(keyword: String): List<Todo> {
        return data.filter { it.title.contains(keyword, ignoreCase = true) }
    }


    override fun sortTodo(type: Int, isAscending: Boolean) {
        when (type) {
            1 -> if (isAscending) data.sortBy { it.id } else data.sortByDescending { it.id }
            2 -> if (isAscending) data.sortBy { it.title } else data.sortByDescending { it.title }
            3 -> {
                // Urutkan berdasarkan status (Belum Selesai -> Selesai)
                data.sortBy { it.isFinished }

                // Jika user minta Descending (n), langsung balik total urutannya
                if (!isAscending) {
                    data.reverse()
                }
            }
        }
    }
}