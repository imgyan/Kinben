
// Function to register a new employee
function registerEmployee() {

    const form = document.getElementById('registrationForm');
    const employeeName = form.elements['name'].value;
    const phoneNumber = form.elements['contact'].value;
    const email = form.elements['email'].value;
    const address = form.elements['address'].value;
    const education = form.elements['education'].value;
    const totalYearOfExperience = form.elements['experience'].value;


    fetch("http://localhost:8888/employee", {

        method: "POST",
        headers: {
            "content-type": "application/json"
        },
        body: JSON.stringify({

            "employeeName": employeeName,
            "phoneNumber": phoneNumber,
            "email": email,
            "address": address,
            "education": education,
            "totalYearOfExperience": totalYearOfExperience
        })
    }).then(response => {
        if (response.status == 201 | response.status == 200) {
            response.json().then(data => {
                alert("Employee sucessfully registered with Roll: " + data.employeeId)
            });
        } else {
            console.log("qwdfghjkl;")
            response.json().then(data => alert(data.message));

        }
    })


}




// Function to fetch and display all employees

window.onload = f1();
function f1() {

    fetch('http://localhost:8888/getallemployee')
        .then(response => response.json())
        .then(data => {
            const employeeTableBody = document.getElementById('employeeTableBody');
            employeeTableBody.innerHTML = '';

            data.forEach(employee => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${employee.employeeId}</td>
                    <td>${employee.employeeName}</td>
                    <td>${employee.phoneNumber}</td>
                    <td>${employee.email}</td>
                    <td>${employee.address}</td>
                    <td>${employee.education}</td>
                    <td>${employee.totalYearOfExperience}</td>
                    <td>
                      <button onclick="editEmployee(${employee.employeeId})">Edit</button>
                      <button onclick="deleteEmployee(${employee.employeeId})">Delete</button>
                    </td>
                  `;
                employeeTableBody.appendChild(row);

            });

        })
        .catch(error => console.log(error));
}


// Function to delete an employee
function deleteEmployee(employeeId) {
    if (confirm('Are you sure you want to delete this employee?')) {
        fetch(`http://localhost:8888/employee/${employeeId}`, {
            method: 'DELETE'
        })
            .then(response => response.json())
            .then(data => {
                console.log('Employee deleted:', data);
                f1();
            })
            .catch(error => console.log(error));
    }
}




// Function to update an employee
function updateEmployee(employeeId) {
    console.log("Updated")
    const form = document.getElementById('registrationForm');
    const employeeName = form.elements['name'].value;
    const phoneNumber = form.elements['contact'].value;
    const email = form.elements['email'].value;
    const address = form.elements['address'].value;
    const education = form.elements['education'].value;
    const totalYearOfExperience = form.elements['experience'].value;

    fetch("http://localhost:8888/updateemployee", {
        method: 'PATCH',

        headers: {
            "content-type": "application/json"
        },
        body: JSON.stringify({
            "employeeId": employeeId,
            "employeeName": employeeName,
            "phoneNumber": phoneNumber,
            "email": email,
            "address": address,
            "education": education,
            "totalYearOfExperience": totalYearOfExperience
        })
    }).then(response => {

        if (response.status == 201 | response.status == 200) {
            response.json().then(data => {
                alert("Employee updated successfully with Roll: " + data.employeeId)


                f1();
            });
        } else {
            console.log("qwdfghjkl;")
            response.json().then(data => alert(data.message));

        }
    })
}

// Function to populate the form with employee data for editing
function editEmployee(employeeId) {
    fetch(`http://localhost:8888/employee/${employeeId}`)
        .then(response => response.json())
        .then(data => {
            const form = document.getElementById('registrationForm');
            form.elements['name'].value = data.employeeName;
            form.elements['contact'].value = data.phoneNumber;
            form.elements['email'].value = data.email;
            form.elements['address'].value = data.address;
            form.elements['education'].value = data.education;
            form.elements['experience'].value = data.totalYearOfExperience;

            const registerButton = document.querySelector('#registrationForm button[type="submit"]');
            registerButton.textContent = 'Update';
            registerButton.setAttribute('onclick', `updateEmployee(${employeeId})`);
        })
        .catch(error => console.log(error));
}
