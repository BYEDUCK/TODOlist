package com.mateusz.todo;

import java.util.ArrayList;
import java.util.List;

public class TodoService {
	private List<Todo> todos=new ArrayList<Todo>();
	
	public List<Todo> retrieveTodos(){
		return todos;
	}
	
	public void addTodo(Todo todo) {
		todos.add(todo);
	}
	
	public void deleteTodo(Todo todo) {
		todos.remove(todo);
	}
}
