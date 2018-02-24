package com.mateusz.todo;

import java.util.ArrayList;
import java.util.List;

public class TodoService {
	private List<Todo> todos_undone=new ArrayList<>();
	private List<Todo> todos_done=new ArrayList<>();
	
	public List<Todo> retrieveUndoneTodos(){
		return todos_undone;
	}
	
	public List<Todo> retrieveDoneTodos(){
		return todos_done;
	}
	
	public void addUndoneTodo(Todo todo) {
		todos_undone.add(todo);
	}
	
	public void deleteUndoneTodo(Todo todo) {
		todos_undone.remove(todo);
	}
	
	public void addDoneTodo(Todo todo) {
		todos_done.add(todo);
	}
	
	public void deleteDoneTodo(Todo todo) {
		todos_done.remove(todo);
	}
}
