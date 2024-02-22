const url = "http://localhost:8080";

const form = document.getElementById("loginForm");
form.addEventListener('submit', (e)=>{
    e.preventDefault();
    loginUser();
})

async function loginUser () {
  
    let userName = document.getElementById("inputUsername").value;
    let passWord = document.getElementById("inputPassword").value;
    let errorMessage = document.getElementById("errorMessage");

    try { 
        const res = await fetch(`${url}/login`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                username: userName,
                password: passWord
            })
        })
        const loginResponse = await res.json();
        console.log(loginResponse);
        sessionStorage.setItem("id", loginResponse.id);
        errorMessage.style.display = "none";
        window.location.href = "userAccount.html";
        

    } catch (e) {
        console.error("wrong username or password: " + e);
        console.error("wrong username or password");
        errorMessage.style.display = "block";
    }

}
