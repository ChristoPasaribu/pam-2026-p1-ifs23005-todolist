package repositories

import entities.Todo

class TodoRepository : ITodoRepository {
    val data = ArrayList<Todo>()
    private var idCounter = 1

    override fun getAllTodos(): ArrayList<Todo> {
        return data
    }

    override fun addTodo(newTodo: Todo) {
        newTodo.id = idCounter
        idCounter++
        data.add(newTodo)
    }

    override fun removeTodo(id: Int): Boolean {
        val targetTodo = data.find { it.id == id }
        if (targetTodo == null) {
            return false
        }
        data.remove(targetTodo)
        return true
    }

    override fun updateTodo(id: Int, newTitle: String, finished: Boolean): Boolean {
        val targetTodo = data.find { it.id == id }
        if (targetTodo != null) {
            targetTodo.title = newTitle
            targetTodo.finished = finished
            return true
        }
        return false
    }

    override fun searchTodo(query: String): List<Todo> {
        return data.filter { it.title.contains(query, ignoreCase = true) }
    }

    override fun getSortedTodos(sortBy: String, isAscending: Boolean): List<Todo> {
        when (sortBy) {
            "title" -> {
                data.sortBy { it.title }
                if (!isAscending) data.reverse()
            }
            "id" -> {
                data.sortBy { it.id }
                if (!isAscending) data.reverse()
            }
            "finished" -> {
                data.sortBy { it.finished }
                if (!isAscending) data.reverse()
            }
        }
        return data
    }
}