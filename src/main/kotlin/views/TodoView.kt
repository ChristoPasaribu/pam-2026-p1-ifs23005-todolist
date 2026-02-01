package views

import services.ITodoService
import utils.InputUtil

class TodoView(private val todoService: ITodoService) {

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
                "1" -> { addTodo(); false }
                "2" -> { updateTodo(); false }
                "3" -> { searchTodo(); false }
                "4" -> { sortTodo(); false }
                "5" -> { removeTodo(); false }
                "x" -> true
                else -> {
                    println("[!] Pilihan tidak dimengerti.")
                    false
                }
            }
            if (stop) break
            println()
        }
    }

    fun addTodo() {
        println("[Menambah Todo]")
        val title = InputUtil.input("Judul (x Jika Batal)")
        if (title != "x") {
            todoService.addTodo(title)
        }
    }

    fun removeTodo() {
        println("[Menghapus Todo]")
        val strIdTodo = InputUtil.input("[ID Todo] yang dihapus (x Jika Batal)")
        if (strIdTodo == "x") {
            return
        }
        val idTodo = strIdTodo.toIntOrNull()
        if (idTodo != null) {
            todoService.removeTodo(idTodo)
        } else {
            println("[!] ID harus berupa angka.")
        }
    }

    fun updateTodo() {
        println("\n[Memperbarui Todo]")
        val strId = InputUtil.input("[ID Todo] yang diubah (x Jika Batal)")

        if (strId == "x") {
            println("[x] Pembaruan todo dibatalkan.")
            return
        }

        val id = strId.toIntOrNull()
        if (id == null) {
            println("[!] ID harus berupa angka.")
            return
        }

        val newTitle = InputUtil.input("Judul Baru (x Jika Batal)")

        if (newTitle == "x") {
            println("[x] Pembaruan todo dibatalkan.")
            return
        }

        val statusStr = InputUtil.input("Apakah todo sudah selesai? (y/n)")
        if (statusStr == "x") {
            println("[x] Pembaruan todo dibatalkan.")
            return
        }
        val isFinished = statusStr.lowercase() == "y"

        if (newTitle.isNotBlank()) {
            todoService.updateTodo(id, newTitle, isFinished)
        } else {
            println("[!] Judul tidak boleh kosong.")
        }
    }

    fun searchTodo() {
        println("\n[Mencari Todo]")
        val query = InputUtil.input("Kata kunci (x Jika Batal)")

        if (query == "x") {
            println("[x] Pencarian todo dibatalkan.")
            return
        }

        if (query.isNotBlank()) {
            todoService.searchTodo(query)
        } else {
            println("[!] Kata kunci tidak boleh kosong.")
        }
    }

    fun sortTodo() {
        println("\n[Mengurutkan Todo]")
        val input = InputUtil.input("Urutkan berdasarkan (id/title/finished) (x Jika Batal)")

        if (input == "x") {
            println("[x] Pengurutan todo dibatalkan.")
            return
        }

        val arah = InputUtil.input("Urutkan secara ascending? (y/n)")
        val isAscending = arah.lowercase() == "y"

        val kriteria = input.lowercase()
        if (kriteria == "id" || kriteria == "title" || kriteria == "finished") {
            todoService.showSortedTodos(kriteria, isAscending)
        } else {
            println("[!] Kriteria tidak dikenali. Gunakan 'id' atau 'title'.")
        }
    }
}