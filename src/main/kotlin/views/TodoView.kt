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

            val input = InputUtil.input("Pilih")
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
        println("")
        println("[Memperbarui Todo]")
        val inputId = InputUtil.input("[ID Todo] yang diubah (x Jika Batal)")
        if (inputId.lowercase() == "x") {
            println("[x] Pembaruan todo dibatalkan.")
            return
        }

        val idTodo = inputId.toIntOrNull()
        if (idTodo != null) {
            // 1. Cek pembatalan di input Judul
            val judulBaru = InputUtil.input("Judul Baru (x Jika Batal)")
            if (judulBaru.lowercase() == "x") {
                println("[x] Pembaruan todo dibatalkan.")
                return
            }

            // 2. Cek pembatalan di input Status
            print("Apakah todo sudah selesai?")
            val statusChoice = InputUtil.input(" (y/n)")
            if (statusChoice.lowercase() == "x") {
                return print("[!] Gagal memperbarui todo dengan ID: $idTodo.")

            }

            val statusBaru = (statusChoice.lowercase() == "y")

            // Eksekusi
            val isSuccess = todoService.updateTodo(idTodo, judulBaru, statusBaru)

            if (isSuccess) {
            } else {
                print("[!] Gagal memperbarui todo dengan ID: $idTodo.")
                println("")
            }
        } else {
            println("Input tidak valid!")
        }
    }

    /**
     * Menampilkan view search todo
     */
    fun searchTodo() {
        println("")
        println("[Mencari Todo]")
        val keyword = InputUtil.input("Kata kunci (x Jika Batal)")

        if (keyword.lowercase() == "x") {
            println("[x] Pencarian todo dibatalkan.")
            return

        }

        // Panggil service dan simpan hasilnya di variabel 'hasil'
        val hasil = todoService.searchTodo(keyword)

        if (hasil.isEmpty()) {
            println("- Data todo tidak ditemukan!")
        } else {
            hasil.forEach { println(it) }
        }
    }

    /**
     * Menampilkan view sort todo
     */
    fun sortTodo() {
        println("")
        println("[Mengurutkan Todo]")
        val kriteria = InputUtil.input("Urutkan berdasarkan (id/title/finished) (x Jika Batal)").lowercase()

        if (kriteria == "x") {
            println("[x] Pengurutan todo dibatalkan.")
            return
        }

        val type = when (kriteria) {
            "id" -> 1
            "title" -> 2
            "finished" -> 3
            else -> {
                println("[!] Kriteria tidak dikenal.")
                return
            }
        }

        val jawaban = InputUtil.input("Urutkan secara ascending? (y/n)").lowercase()
        val isAscending = (jawaban == "y")

        todoService.sortTodo(type, isAscending)
    }
}
