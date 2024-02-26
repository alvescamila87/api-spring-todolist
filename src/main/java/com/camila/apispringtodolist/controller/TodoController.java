package com.camila.apispringtodolist.controller;

import com.camila.apispringtodolist.entity.Todo;
import com.camila.apispringtodolist.error.TodoNotFoundException;
import com.camila.apispringtodolist.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/todos")
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    List<Todo> getAllTodos(Todo todo) {
        return todoService.listAllTodos();
    }

    @PostMapping
    Todo createTodo(@RequestBody @Valid Todo todo) {
        return todoService.createTodo(todo);
    }

    @PutMapping("/{id}")
    Todo updateTodoById(@PathVariable("id") UUID id, @RequestBody @Valid Todo todo) throws TodoNotFoundException {
        return todoService.updateTodoById(todo, id);
    }

    @DeleteMapping("/{id}")
    List<Todo> deleteTodoByID(@PathVariable("id") UUID id) throws TodoNotFoundException {
        return todoService.deleteTodoById(id);
    }
}
