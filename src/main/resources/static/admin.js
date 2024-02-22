const url = "http://localhost:8080";

// const form = document.getElementById("loginForm");
// form.addEventListener('submit', (e)=>{
//     e.preventDefault();
//     getAllAccount();
// })

// getAllAccountButton
const button = document.getElementById("getAllAccountButton");
button.addEventListener('click', getAllAccount);

// clearAllAccountButton
const clearButton = document.getElementById("clearAllAccount");
clearButton.addEventListener('click', clearAccountList);

// searchByTaxButton
const searchByTaxButton = document.getElementById("searchByTaxButton");
searchByTaxButton.addEventListener('click', searchByTaxId);


// clear account view function
function clearAccountList () {
    let seeAllAccounts = document.getElementById("seeAllAccount");
    seeAllAccounts.innerHTML = "";
    console.log("function fired")
}

// get all account function
async function getAllAccount () {
  
    let seeAllAccounts = document.getElementById("seeAllAccount");

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

        

    } catch (e) {
        console.error("Error: " + e);
        console.error("Error!");
    }

}

// search by Tax ID function
async function searchByTaxId () {
  
    let seeTaxAccounts = document.getElementById("seeTaxAccount");
    let taxErrorMessage = document.getElementById("taxErrorMessage");

    try { 
        const res = await fetch(`${url}/users/search`, {
            method: "Get",
            headers: {
                "Content-Type": "application/json"
            }
        })
        const accounts = await res.json();
        console.log(accounts);
        let stringAccounts = JSON.stringify(accounts);
        seeTaxAccounts.innerHTML = stringAccounts;

        taxErrorMessage.style.display = "none";
        

    } catch (e) {
        console.error("Error: " + e);
        console.error("Error!");
        errorMessage.style.display = "block";
    }

}