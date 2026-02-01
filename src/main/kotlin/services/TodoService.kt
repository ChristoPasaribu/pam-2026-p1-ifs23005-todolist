package services

import entities.Todo
import repositories.ITodoRepository

class TodoService(private val todoRepository: ITodoRepository) : ITodoService {
    override fun showTodos() {
        val todos = todoRepository.getAllTodos()

        println("Daftar Todo:")
        if (todos.isEmpty()) {
            println("- Data todo belum tersedia!")
        } else {
            for (todo in todos) {
                val status = if (todo.finished) "Selesai" else "Belum Selesai"
                println("${todo.id} | ${todo.title} | $status")
            }
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
            return
        }
    }

    override fun updateTodo(id: Int, newTitle: String, finished: Boolean) {
        if (newTitle.isBlank()) {
            println("[!] Judul baru tidak boleh kosong.")
            return
        }
        val success = todoRepository.updateTodo(id, newTitle, finished)
        if (!success) {
            println("[!] Gagal memperbarui todo dengan ID: $id.")
        }
    }

    override fun searchTodo(query: String) {
        val results = todoRepository.searchTodo(query)

        if (results.isEmpty()) {
            println("- Data todo tidak ditemukan!")
        } else {
            results.forEach { todo ->
                val status = if (todo.finished) "Selesai" else "Belum Selesai"
                println("${todo.id} | ${todo.title} | $status")
            }
        }
    }

    override fun showSortedTodos(sortBy: String, isAscending: Boolean) {
        todoRepository.getSortedTodos(sortBy, isAscending)
    }
}