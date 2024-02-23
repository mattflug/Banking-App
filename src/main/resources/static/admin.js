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

// search By Tax form
const taxForm = document.getElementById("searchByTaxForm");
taxForm.addEventListener('submit', (e)=>{
    e.preventDefault();
    searchByTaxId();
})

// CREATE Bank Account for User form
const createBankAccountForm = document.getElementById("createBankAccountForm");
createBankAccountForm.addEventListener('submit', (e)=>{
    e.preventDefault();
    createBankAccount();
})

// DELETE Bank Account for User form
const deleteBankAccountForm = document.getElementById("deleteBankAccountForm");
deleteBankAccountForm.addEventListener('submit', (e)=>{
    e.preventDefault();
    deleteBankAccount();
})


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
    let taxId = document.getElementById("inputTaxId").value;

    if(!taxId){
        taxErrorMessage.style.display = "block";
        return;
    }

    try { 
        const res = await fetch(`${url}/users/search?taxId=${taxId}`, {
            method: "Get",
            headers: {
                "Content-Type": "application/json"
            }
        })
        const accounts = await res.json();
        console.log(accounts);
        let stringAccounts = JSON.stringify(accounts);
        seeTaxAccounts.innerHTML = "";
        seeTaxAccounts.innerHTML = stringAccounts;

        taxErrorMessage.style.display = "none";
        
    } catch (e) {
        console.error("Error: " + e);
        console.error("Error!");
        seeTaxAccounts.innerHTML = "";
        taxErrorMessage.style.display = "block";
    }

}

// CREATE Bank Account for User function
async function createBankAccount () {
  
    let accountType = document.getElementById("accountType").value;
    let accountBalance = document.getElementById("accountBalance").value;
    let userErrorMessage = document.getElementById("userErrorMessage");
    let userId = document.getElementById("inputUserId").value;

    if(!userId){
        userErrorMessage.style.display = "block";
        userErrorMessage.innerHTML = "Please enter a User ID.";
        return;
    }

    if(!accountType){
        userErrorMessage.style.display = "block";
        userErrorMessage.innerHTML = "Please enter a Account Type.";
        console.log(accountType);
        return;
    }

    if(!accountBalance){
        userErrorMessage.style.display = "block";
        userErrorMessage.style.color = "red";
        userErrorMessage.innerHTML = "Please enter a Account Balance.";
        return;
    }

    try { 
        const res = await fetch(`${url}/accounts/${userId}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                accountType: accountType,
                currentBalance: accountBalance
            })
        })
        const accounts = await res.json();;
        let stringAccounts = JSON.stringify(accounts);
        console.log(stringAccounts);

        userErrorMessage.style.display = "block";
        userErrorMessage.style.color = "green";
        userErrorMessage.innerHTML = "New Banking Account Created!";
        
    } catch (e) {
        console.error("Error: " + e);
        console.error("Error!");
        userErrorMessage.style.display = "block";
        userErrorMessage.style.color = "red";
        userErrorMessage.innerHTML = "User does not exists";
    }

}

// DELETE Bank Account for User function
async function deleteBankAccount () {
  
    let bankAccountErrorMessage = document.getElementById("bankAccountErrorMessage");
    let accountId = document.getElementById("inputBankAccountId").value;

    if(!accountId){
        bankAccountErrorMessage.style.display = "block";
        bankAccountErrorMessage.style.color = "green";
        bankAccountErrorMessage.innerHTML = "Please enter a bank account ID.";
        return;
    }

    try { 
        const res = await fetch(`${url}/accounts/${accountId}`, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json"
            }
        })
        
        const accounts = await res.json();
        console.log(accounts);
        let stringAccounts = JSON.stringify(accounts);
        console.log(stringAccounts);

        bankAccountErrorMessage.style.display = "block";
        bankAccountErrorMessage.style.color = "green";
        bankAccountErrorMessage.innerHTML = "Banking Account Delete!";
        
    } catch (e) {
        console.log(e.message);
        console.error("Error: " + e);
        console.error("Error!");
        bankAccountErrorMessage.style.display = "block";
        bankAccountErrorMessage.style.color = "red";
        bankAccountErrorMessage.innerHTML = "Error: account not found or account is not at $0.";
    }

}