package com.camila.apispringtodolist.service;

import com.camila.apispringtodolist.entity.Todo;
import com.camila.apispringtodolist.error.TodoNotFoundException;
import com.camila.apispringtodolist.repository.TodoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> listAllTodos() {
        Sort sort = Sort.by("priority").descending().and(Sort.by("name").ascending());
        return todoRepository.findAll(sort);
    }

    public Todo createTodo(Todo todo) {
        Todo createdTodo = todoRepository.save(todo);
        return createdTodo;
    }

    public Todo updateTodoById(Todo todo, UUID id) throws TodoNotFoundException {
        Optional<Todo> optionalTodo = todoRepository.findById(id);

        if(!optionalTodo.isPresent()) {
            throw new TodoNotFoundException("Todo not found, ID: " + id);
        }

        Todo updateTodo = optionalTodo.get();
        updateTodo.setName(todo.getName());
        updateTodo.setDescription(todo.getDescription());
        updateTodo.setDone(todo.isDone());
        updateTodo.setPriority(todo.getPriority());

        return todoRepository.save(updateTodo);
    }

    public List<Todo> deleteTodoById(UUID id)  throws TodoNotFoundException {
        Optional<Todo> optionalTodo = todoRepository.findById(id);

        if(!optionalTodo.isPresent()) {
            throw new TodoNotFoundException("Todo not found, ID: " + id);
        }

        todoRepository.deleteById(id);
        return listAllTodos();
    }

}
