package repositories

import entities.Todo

interface ITodoRepository {
    fun getAllTodos(): List<Todo>
    fun addTodo(newTodo: Todo)
    fun removeTodo(id: Int): Boolean
    fun updateTodo(id: Int, newTitle: String, finished: Boolean): Boolean
    fun searchTodo(query: String): List<Todo>
    fun getSortedTodos(sortBy: String, isAscending: Boolean): List<Todo>
}