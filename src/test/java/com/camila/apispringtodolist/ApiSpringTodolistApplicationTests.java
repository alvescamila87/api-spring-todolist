package com.camila.apispringtodolist;

import com.camila.apispringtodolist.entity.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiSpringTodolistApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testCreateTodoSuccess() {
		var todo = new Todo("Studying", "About Java mapTree", false, 1);

		webTestClient
				.post()
				.uri("/todos")
				.bodyValue(todo)
				.exchange().expectStatus().isCreated()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(1)
				.jsonPath("$.[0]name").isEqualTo(todo.getName())
				.jsonPath("$.[0]description").isEqualTo(todo.getDescription())
				.jsonPath("$.[0]done").isEqualTo(todo.isDone())
				.jsonPath("$.[0]priority").isEqualTo(todo.getPriority());
	}

	@Test
	void testCreateTodoFailure() {
		webTestClient
				.post()
				.uri("/todos")
				.bodyValue(
						new Todo("","",false, 0)
				)
				.exchange()
				.expectStatus().isBadRequest();
	}

	@Test
	void testListTodosSuccess() {
		webTestClient
				.get()
				.uri("/todos")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectBodyList(Todo.class);

	}

	@Test
	void testListTodosFailure() {
		webTestClient
				.get()
				.uri("/todos")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isNotFound();
	}

	@Test
	void testUpdateTodoSuccess() {
		var updateTodo = new Todo("Update task", "Update description", true, 2);
		String idNotOk = "id";

		webTestClient
				.put()
				.uri("/todos/{id}", idNotOk)
				.bodyValue(updateTodo)
				.exchange().expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(1)
				.jsonPath("$.[0]name").isEqualTo(updateTodo.getName())
				.jsonPath("$.[0]description").isEqualTo(updateTodo.getDescription())
				.jsonPath("$.[0]done").isEqualTo(updateTodo.isDone())
				.jsonPath("$.[0]priority").isEqualTo(updateTodo.getPriority());
	}

	@Test
	void testUpdateTodoFailure() {

		var todoNotOk = new Todo("", "", false, 0);
		String id = "id";

		webTestClient
				.put()
				.uri("/todos/{id}", id)
				.bodyValue(todoNotOk)
				.exchange()
				.expectStatus().isNotFound();
	}

	@Test
	void testDeleteTodoSuccess() {
		String id = "id";

		webTestClient
				.delete()
				.uri("/todos/{id}", id)
				.exchange().expectStatus().isOk();
	}

	@Test
	void testDeleteTodoFailure() {

		String idNotOk = "id";

		webTestClient
				.delete()
				.uri("/todos/{id}", idNotOk)
				.exchange()
				.expectStatus().isNotFound();
	}

}
