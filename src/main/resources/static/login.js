// login variable declaration:

const button = document.getElementById("loginButton");
button.onclick = loginUser;
// button.onclick = logUser;


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

    const res = await fetch("/login", {
        method: "POST",
        body: JSON.stringify({
            username: userName,
            password: passWord
        })
    });
    const loginResponse = await res.json();
    console.log(loginResponse);

}

// fetch("/login", {
//   method: "POST",
//   body: JSON.stringify({
//     userName: userName,
//     passWord: passWord
//   })
// });