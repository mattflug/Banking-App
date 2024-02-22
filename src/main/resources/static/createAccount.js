const url = "http://localhost:8080";

const form = document.getElementById("createAccount");
form.addEventListener('submit', (e)=>{
    e.preventDefault();
    createUser();
})

async function createUser () {
  
    let userName = document.getElementById("inputUsername").value;
    let passWord = document.getElementById("inputPassword").value;
    let firstName = document.getElementById("inputFirstName").value;
    let lastName = document.getElementById("inputLastName").value;
    let taxId = document.getElementById("inputTaxId").value;
    let isAdmin = document.getElementById("isAdmin").value;
    let errorMessage = document.getElementById("errorMessage");

    if(!userName){
        errorMessage.style.display = "block";
        errorMessage.style.color = "red";
        errorMessage.innerHTML = "Please enter a Username.";
        return;
    }

    if(!passWord){
        errorMessage.style.display = "block";
        errorMessage.style.color = "red";
        errorMessage.innerHTML = "Please enter a password.";
        return;
    }

    if(!firstName){
        errorMessage.style.display = "block";
        errorMessage.style.color = "red";
        errorMessage.innerHTML = "Please enter a first name.";
        return;
    }

    if(!lastName){
        errorMessage.style.display = "block";
        errorMessage.style.color = "red";
        errorMessage.innerHTML = "Please enter a last name.";
        return;
    }

    if(!taxId){
        errorMessage.style.display = "block";
        errorMessage.style.color = "red";
        errorMessage.innerHTML = "Please enter a taxID number.";
        return;
    }


    try { 
        const res = await fetch(`${url}/register`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                username: userName,
                password: passWord,
                firstName: firstName,
                lastName: lastName,
                taxIdNumber: taxId,
                admin: !!isAdmin
            })
        })
        const createUserResponse = await res.json();
        console.log(createUserResponse);
        errorMessage.innerText = "Account Created!";
        errorMessage.style.color = "green";
        errorMessage.style.display = "block";
    } catch (e) {
        console.error("Error: " + e);
        console.error("Error");
        errorMessage.style.display = "block";
        errorMessage.style.color = "red";
        errorMessage.innerText = "Error: username already exsists.";
    }

}
