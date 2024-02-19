// login variable declaration:
const url = "http://localhost:8080";

const form = document.getElementById("loginForm");
form.addEventListener('submit', (e)=>{
    e.preventDefault();
    loginUser();
})

const button = document.getElementById("loginButton");




// test .onclick function
// function logUser () {

//     let userName = document.getElementById("inputEmail").value;
//     let passWord = document.getElementById("inputPassword").value;
//     let userObj = {
//         userName,
//         passWord
//     };

//     console.log(userObj);
// }

// loginUser
async function loginUser () {

    let userName = document.getElementById("inputEmail").value;
    let passWord = document.getElementById("inputPassword").value;

    const res = await fetch(`${url}/login`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            username: userName,
            password: passWord
        })
    });
    const loginResponse = await res.json();
    console.log(loginResponse);

}
