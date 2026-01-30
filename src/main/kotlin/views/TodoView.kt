package views

import services.ITodoService
import utils.InputUtil

class TodoView(private val todoService: ITodoService) {

    /**
     * Menampilkan view todo
     */
    fun showTodos() {
        while (true) {
            todoService.showTodos()

            println("Menu:")
            println("1. Tambah")
            println("2. Ubah")
            println("3. Cari")
            println("4. Urutkan")
            println("5. Hapus")
            println("x. Keluar")

            val input = InputUtil.input("Pilih").trim()
            val stop = when (input) {
                "1" -> {
                    addTodo()
                    false
                }
                "2" -> {
                    updateTodo()
                    false
                }
                "3" -> {
                    searchTodo()
                    false
                }
                "4" -> {
                    sortTodo()
                    false
                }
                "5" -> {
                    removeTodo()
                    false
                }
                "x" -> true
                else -> {
                    println("[!] Pilihan tidak dimengerti.")
                    false
                }
            }

            if (stop) {
                break
            }

            println()
        }
    }

    /**
     * Menampilkan view add todo
     */
    fun addTodo() {
        println("[Menambah Todo]")
        val title = InputUtil.input("Judul (x Jika Batal)")

        if (title != "x") {
            todoService.addTodo(title)
        }
    }

    /**
     * Menampilkan view remove todo
     */
    fun removeTodo() {
        println("[Menghapus Todo]")

        val strIdTodo = InputUtil.input("[ID Todo] yang dihapus (x Jika Batal)")

        if (strIdTodo != "x") {
            val idTodo = strIdTodo.toInt()
            todoService.removeTodo(idTodo)
        }
    }

    /**
     * Menampilkan view update todo
     */
    fun updateTodo() {
        println("[Mengubah Todo]")

        val strIdTodo = InputUtil.input("[ID Todo] yang diubah (x Jika Batal)")

        if (strIdTodo != "x") {
            val idTodo = strIdTodo.toIntOrNull()
            if (idTodo != null) {
                var newTitle = InputUtil.input("Judul baru (x Jika Batal)")

                if (newTitle == "") {
                    val todoLama = todoService.getTodoById(idTodo)
                    newTitle = todoLama.title
                }

                val newStatus = InputUtil.input("Apakah todo sudah selesai? (y/n)")

                if (newStatus == "y") {
                    todoService.changeTodo(idTodo, newTitle, "true")
                } else if (newStatus == "n") {
                    todoService.changeTodo(idTodo, newTitle, "false")
                } else {

                }

            } else {
                println("[!] ID harus berupa angka.")
            }
        }
    }

    /**
     * Menampilkan view search todo
     */
    fun searchTodo() {

    }

    /**
     * Menampilkan view sort todo
     */
    fun sortTodo() {

    }
}
