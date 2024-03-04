function toogleSpinnerVisibility(visible) {

    const spinner = document.getElementById('spinner');
    spinner.style.display = visible ? 'block' : 'none';

}

function toggleSections() {
    const searchByIdSection = document.getElementById('searchByIdSection');
    searchByIdSection.style.display = searchByIdSection.style.display === 'none' ? 'block' : 'none';
}


function createTodoItem() {

    const timeoutMilliseconds = 300;
    const timeoutPromise = new Promise((resolve) => setTimeout(resolve, timeoutMilliseconds));

    // Loading toogle during ToDo creation 
    toogleSpinnerVisibility(true);

    const apiURL = 'http://localhost:8080/todos';

    let todo_name = document.getElementById('todo_name_field').value;
    let todo_description = document.getElementById('todo_description_field').value;
    let todo_done = document.getElementById('todo_done_field').checked;
    let todo_priority = document.getElementById('todo_priority_field').value;

    let todoData = {
        name: todo_name,
        description: todo_description,
        done: todo_done,
        priority: todo_priority
    };    

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
    let boxResultTodoDetails = document.querySelector('.box-result-todo-details');

    boxResultTodoDetails.innerHTML = '';

    if(result && result.id && result.name && result.description && result.done !== undefined && result.priority !== undefined) {

        boxResultTodoDetails.innerHTML += '<h4> ToDo item created: </h4>' + '<p>ID: ' + result.id  + '</p>';
        boxResultTodoDetails.innerHTML += '<p>Name: ' + result.name + '</p>';
        boxResultTodoDetails.innerHTML += '<p>Description: ' + result.description + '</p>';
        boxResultTodoDetails.innerHTML += '<p>Done: ' + result.done + '</p>';
        boxResultTodoDetails.innerHTML += '<p>Priority: ' + result.priority + '</p>';
        boxResultTodoDetails.innerHTML += '<button type="button" class="btn btn-secondary btn-sm" onclick="toggleSections()">>Back 2</button>';     


    } else {

        console.error('Error to return data from ToDo Item: ', result);
        boxResultTodoDetails.innerHTML = '<p>Error when processing data from item ToDo. </p>';
    }
    
}

function searchByID() {

    const timeoutMilliseconds = 300;
    const timeoutPromise = new Promise((resolve) => setTimeout(resolve, timeoutMilliseconds));

    // Loading tootle during search by id
    toogleSpinnerVisibility(true);

    const apiURLById = `http://localhost:8080/todos/${todoId}`;

    let todo_id = document.getElementById('todo_search_field').value;

    fetch(apiURLById, {
        method: 'GET',
    })
        .then(response => {
            if(response.ok) {
                return response.json();                
            } else {
                throw new Error('Failed to fetch todo');
            }
        })
        .then(data => {
            itemTodoResult(data);
        })
        .catch(error => console.error('Error to search ToDo ID: ', error))
        .finally(() => {
            document.querySelector('.box-container-todo').style.display = 'none';
            document.querySelector('.box-container-todo-list').style.display = 'none';
            document.querySelector('.box-result-all-todos').style.display = 'none';
            toogleSpinnerVisibility(false);
        });
}

