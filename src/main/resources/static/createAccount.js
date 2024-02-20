const url = "http://localhost:8080";

const form = document.getElementById("createAccount");
form.addEventListener('submit', (e)=>{
    e.preventDefault();
    createUser();
})

// test on submit function
function createUser () {

    let userName = document.getElementById("inputUsername").value;
    let passWord = document.getElementById("inputPassword").value;
    let firstName = document.getElementById("inputFirstName").value;
    let lastName = document.getElementById("inputLastName").value;
    let taxId = document.getElementById("inputTaxId").value;
    let isAdmin = document.getElementById("isAdmin").value;
    let userObj = {
        userName,
        passWord,
        firstName,
        lastName,
        taxId,
        isAdmin
    };

    console.log(userObj);
}
