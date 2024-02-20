const url = "http://localhost:8080";

const form = document.getElementById("loginForm");
form.addEventListener('submit', (e)=>{
    e.preventDefault();
    getAllAccount();
})


async function getAllAccount () {
  
    // let userName = document.getElementById("inputUsername").value;
    // let passWord = document.getElementById("inputPassword").value;
    // let errorMessage = document.getElementById("errorMessage");

    try { 
        const res = await fetch(`${url}/users`, {
            method: "Get",
            headers: {
                "Content-Type": "application/json"
            }
        })
        const allAccounts = await res.json();
        console.log(allAccounts);
        // errorMessage.style.display = "none";
        

    } catch (e) {
        console.error("Error: " + e);
        console.error("Error!");
        // errorMessage.style.display = "block";
    }

}