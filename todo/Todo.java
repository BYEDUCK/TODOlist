package com.mateusz.todo;

public class Todo {
	private String name;
	private int id;
	private String category;
	private boolean done;
	
	public Todo(String name, String category, int id,boolean done) {
		super();
		this.name = name;
		this.id=id;
		this.category=category;
		this.done=done;
	}
	
	public Todo(int id) {
		super();
		this.id=id;
	}
	
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Todo other = (Todo) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Todo [name=%s, category=%s]", name,category);
	}
}
