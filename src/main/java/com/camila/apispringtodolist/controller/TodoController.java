package com.camila.apispringtodolist.controller;

import com.camila.apispringtodolist.entity.Todo;
import com.camila.apispringtodolist.service.TodoService;
import jakarta.validation.Valid;
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
    List<Todo> createTodo(@RequestBody @Valid Todo todo) {
        return todoService.createTodo(todo);
    }

    @PutMapping("/{id}")
    List<Todo> updateTodoById(@PathVariable("id") UUID id, @RequestBody @Valid Todo todo) {
        return todoService.updateTodoById(todo, id);
    }

    @DeleteMapping("/{id}")
    List<Todo> deleteTodoByID(@PathVariable("id") UUID id) {
        return todoService.deleteTodoById(id);
    }
}
