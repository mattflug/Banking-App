const url = "http://localhost:8080";

let accounts = [];

let accountContainer = document.getElementById("userContainer");
console.log(accountContainer);

const logoutButton = document.getElementById("logout");
logoutButton.addEventListener('click', logoutFunction);

function populateAccounts(accounts) {
  accountContainer.innerHTML = "";

  for (account of accounts) {
    let bryDiv = document.createElement("div");

      bryDiv.innerHTML = `
            <div>
            <div class="mb-3">
            <h2 for="savingsAccount" class="form-label">${account.accountType} Account, ID: ${account.id}</h2>
              <label for="savingsAccount" class="form-label">Account Balance:</label>
              <label for="savingsAccount" class="form-label">${account.currentBalance}</label>
            </div>
            <div class="mb-3">
              <input type="number" class="form-control" id="input-${account.id}" placeholder="please input amount in $">
            </div>
            <button type="submit" class="btn btn-primary" onclick="withdraw(${account.id})">Withdraw</button>
            <button type="submit" class="btn btn-primary" onclick="deposit(${account.id})">Deposit</button>
            <br>
            <p id="ErrorMessage-${account.id}" style="color: red; display: none;">Error:</p>
            <br>

            <br>
            <br>
            <br>
        </div>
            
        `;
    accountContainer.append(bryDiv);
  }
}


//on load userAccount page for currently logged in user
if (sessionStorage){
  (async () => {
  let data = await fetch(`http://localhost:8080/users/${sessionStorage.getItem("id")}/accounts`);
  let res = await data.json(); // Different syntax, same thing as before
  console.log(res);
  accounts = res;
  populateAccounts(accounts);
})();
}else{
  window.location.href = "login.html";
}

function logoutFunction(){
  sessionStorage.removeItem("id");
}

// Withdraw function
async function withdraw (id) {

    console.log(id);
  
    let amount = document.getElementById("input-" + id).value;

    console.log(amount);

    let errorMessage = document.getElementById("ErrorMessage-" + id);


    if(!amount){
        errorMessage.style.display = "block";
        errorMessage.innerHTML = "Please enter an amount.";
        return;
    }

    try { 
        const res = await fetch(`${url}/accounts/${id}/withdraw`, {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            amount: amount
          }),
        });
        const response = await res.json();
        console.log(response);
        let jsonResponse = JSON.stringify(response);
        console.log(jsonResponse);
        errorMessage.style.display = "none";
        window.location.href = "userAccount.html";
        
    } catch (e) {
        console.error("Error: " + e);
        console.error("Error!");
        errorMessage.style.display = "block";
        errorMessage.innerHTML = "Insufficient funds";
    }

}

// Deposit function
async function deposit (id) {

  console.log(id);

  let amount = document.getElementById("input-" + id).value;

  console.log(amount);

  let errorMessage = document.getElementById("ErrorMessage-" + id);


  if(!amount){
      errorMessage.style.display = "block";
      errorMessage.innerHTML = "Please enter an amount.";
      return;
  }

  try { 
      const res = await fetch(`${url}/accounts/${id}/deposit`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          amount: amount
        }),
      });
      const response = await res.json();
      console.log(response);
      let jsonResponse = JSON.stringify(response);
      console.log(jsonResponse);
      errorMessage.style.display = "none";
      window.location.href = "userAccount.html";
      
  } catch (e) {
      console.error("Error: " + e);
      console.error("Error!");
      errorMessage.style.display = "block";
      errorMessage.innerHTML = "Insufficient funds";
  }

}