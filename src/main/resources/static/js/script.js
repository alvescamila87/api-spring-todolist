function toogleSpinnerVisibility(visible) {

    const spinner = document.getElementById('spinner');
    spinner.style.display = visible ? 'block' : 'none';

}

function createTodoItem() {

    const timeoutMilliseconds = 300;
    const timeoutPromise = new Promise((resolve) => setTimeout(resolve, timeoutMilliseconds));

    // Loading toogle during ToDo creation 
    toogleSpinnerVisibility(true);

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

    const apiURL = 'http://localhost:8080/todos'; 

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
            document.querySelector('.box-container-todo').style.display = 'none';
            document.querySelector('.box-container-todo-list').style.display = 'none';
            document.querySelector('.box-result-all-todos').style.display = 'none';
            toogleSpinnerVisibility(false);
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

    if(result && result.id && result.name && result.description && result.done !== undefined && result.priority !== undefined) {

        boxResultTodoCreated.innerHTML = '<h4> ToDo item created: </h4>' + '<p>ID: ' + result.id  + '</p>'
                                            + '<p>Name: ' + result.name + '</p>'
                                            + '<p>Description: ' + result.description + '</p>'
                                            + '<p>Done: ' + result.done + '</p>'
                                            + '<p>Priority: ' + result.priority + '</p>';

    } else {

        console.error('Error to return data from ToDo Item: ', result);
        boxResultTodoCreated.innerHTML = '<p>Error when processing data from item ToDo. </p>';
    }


    
}