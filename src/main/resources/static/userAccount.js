const url = "http://localhost:8080";

let accounts = [];

let accountContainer = document.getElementById("userContainer");
console.log(accountContainer);

const logoutButton = document.getElementById("logout");
logoutButton.addEventListener("click", logoutFunction);

function populateAccounts(accounts) {
  accountContainer.innerHTML = "";

  for (account of accounts) {
    let bryDiv = document.createElement("div");

    bryDiv.innerHTML = `
    <form>
    <div class="card p-5 mb-5">
        <h2>${account.accountType}</h2>
        <div>
            <label class="form-label">Account Balance:</label>
            <span class="form-label">${account.currentBalance}</span>
        </div>
        <div class="mb-3">
            <input type="number" class="form-control" id="amountInput" placeholder="Please input amount in $">
        </div>
        <div class = "w-50">
        <button type="submit" class="btn btn-primary mb-2" id="withdraw">Withdraw</button>
        <button type="submit" class="btn btn-primary" id="deposit">Deposit</button>
        <div>
        <br><br>
        <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Transfer To
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <a class="dropdown-item" href="#">Action</a>
                <a class="dropdown-item" href="#">Another action</a>
                <a class="dropdown-item" href="#">Something else here</a>
            </div>
        </div>
        <div class = "mt-5">
        <a href="userTransactions.html?accountId=${account.id}" class="text-decoration-none">
            View Transactions
        </a>
        </div>
    </div>
</form> 
        `;
    accountContainer.append(bryDiv);
  }
}

// Get to user accounts, need to edit this to take in the user who's
// logged in
if (sessionStorage) {
  (async () => {

    let data = await fetch(
      `http://localhost:8080/users/${sessionStorage.getItem("id")}/accounts`
    );
    let res = await data.json(); // Different syntax, same thing as before
    console.log(res);
    accounts = res;
    populateAccounts(accounts);
  })();
} else {

  window.location.href = "login.html";
}

function logoutFunction() {
  sessionStorage.removeItem("id");
}

// Withdraw function
async function withdraw() {
  // let seeTaxAccounts = document.getElementById("seeTaxAccount");
  // let userErrorMessage = document.getElementById("userErrorMessage");
  // let userId = document.getElementById("inputUserId").value;

  // if(!userId){
  //     userErrorMessage.style.display = "block";
  //     userErrorMessage.innerHTML = "Please enter a User ID.";
  //     return;
  // }

  try {
    const res = await fetch(`${url}/accounts/${account.id}/withdraw`, {
      method: "Put",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        accountType: accountType,
        currentBalance: accountBalance,
      }),
    });
    const accounts = await res.json();
    console.log(accounts);
    let stringAccounts = JSON.stringify(accounts);
    // seeTaxAccounts.innerHTML = "";
    // seeTaxAccounts.innerHTML = stringAccounts;

    userErrorMessage.style.display = "none";
  } catch (e) {
    console.error("Error: " + e);
    console.error("Error!");
    // seeTaxAccounts.innerHTML = "";
    userErrorMessage.style.display = "block";
    userErrorMessage.innerHTML = "User does not exists";
  }
}
