const url = "http://localhost:8080";

// const form = document.getElementById("loginForm");
// form.addEventListener('submit', (e)=>{
//     e.preventDefault();
//     getAllAccount();
// })

const button = document.getElementById("getAllAccountButton");
button.addEventListener('click', getAllAccount);
const clearButton = document.getElementById("clearAllAccount");
clearButton.addEventListener('click', clearAccountList);

function clearAccountList() {
    let seeAllAccounts = document.getElementById("seeAllAccount");
    seeAllAccounts.innerHTML = "";
}


async function getAllAccount () {
  
    let seeAllAccounts = document.getElementById("seeAllAccount");
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
        let stringAllAccounts = JSON.stringify(allAccounts);
        seeAllAccounts.innerHTML = stringAllAccounts;

        // errorMessage.style.display = "none";
        

    } catch (e) {
        console.error("Error: " + e);
        console.error("Error!");
        // errorMessage.style.display = "block";
    }

}