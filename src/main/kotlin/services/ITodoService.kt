package services

interface ITodoService {
    fun showTodos()
    fun addTodo(title: String)
    fun removeTodo(id: Int)
    fun updateTodo(id: Int, newTitle: String, finished: Boolean)
    fun searchTodo(query: String)
    fun showSortedTodos(sortBy: String, isAscending: Boolean)
}