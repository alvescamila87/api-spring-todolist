

function createTodoItem() {

    const timeoutMilliseconds = 300;
    const timeoutPromise = new Promise((resolve) => setTimeout(resolve, timeoutMilliseconds));

    document.getElementById('spinner').style.display = 'block';

    var todo_name = document.getElementById('todo_name_field').value;
    var todo_description = document.getElementById('todo_description_field').value;
    var todo_done = document.getElementById('todo_done_field').checked;
    var todo_priority = document.getElementById('todo_priority_field').value;

    var todoData = {
        name: todo_name,
        description: todo_description,
        done: todo_done,
        priority: todo_priority
    };

    const apiURLPost = 'http://localhost:8080/todos'; 

    fetch(apiURL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(todoData)
    })
        .then(response => response.json())
        .then(data => itemTodoResult(data))
        .catch(error => console.error('Error to create an item: ', error))
        .finally(() => {
            document.getElementById('spinner').style.display = 'none';
        });

}

function cleanFormTodoItem() {
    document.getElementById('todo_name_field').value = "";
    document.getElementById('todo_description_field').value = "";
    document.getElementById('todo_done_field').checked = false;
    document.getElementById('todo_priority_field').selectedIndex = 0;
}

function cleanID() {
    document.getElementById('todo_search_field').value = "";
}

function itemTodoResult(result) {

    var boxResultTodoCreated = document.querySelector('.box-result-todo-created');
    boxResultTodoCreated.innerHTML = '<p>Todo created: ' + data.name + '</p>';
    
}