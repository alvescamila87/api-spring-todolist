package com.camila.apispringtodolist.service;

import com.camila.apispringtodolist.entity.Todo;
import com.camila.apispringtodolist.repository.TodoRepository;
import org.springframework.beans.BeanUtils;
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
        return todoRepository.findAll();
    }

    public List<Todo> createTodo(Todo todo) {
        todoRepository.save(todo);
        return listAllTodos();
    }

    public List<Todo> updateTodoById(Todo todo, UUID id) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);

        if(optionalTodo.isPresent()) {
            Todo updateTodo = optionalTodo.get();
            updateTodo.setName(updateTodo.getName());
            updateTodo.setDescription(updateTodo.getDescription());
            updateTodo.setDone(updateTodo.isDone());
            updateTodo.setPriority(updateTodo.getPriority());
            todoRepository.save(updateTodo);
        }
        return listAllTodos();
    }

    public List<Todo> deleteTodoById(UUID id) {
        todoRepository.deleteById(id);
        return listAllTodos();
    }

}
