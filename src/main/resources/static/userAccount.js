const url = "http://localhost:8080";

let accounts = [];

let accountContainer = document.getElementById("userContainer");
console.log(accountContainer);

const logoutButton = document.getElementById("logout");
logoutButton.addEventListener("click", logoutFunction);

// transfer function
async function transfer(fromAccountId, toAccountId) {
  let amount = document.getElementById("amountInput").value;
  let amountErrorMessage = document.getElementById("amountInputErrorMessage");

  // Validate amount input
  if (!amount) {
    amountErrorMessage.style.display = "block";
    amountErrorMessage.innerHTML = "Please enter a valid amount";
    return;
  }

  // Need to validate ac

  try {
    const res = await fetch(`${url}/accounts/${fromAccountId}/transfer`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        toAccountId,
        amount,
      }),
    });
  } catch (e) {
    console.error("Error: " + e);
    console.error("Error!");
  }
}

function populateAccounts(accounts) {
  accountContainer.innerHTML = "";

  for (account of accounts) {
    let bryDiv = document.createElement("div");

    bryDiv.innerHTML = `
    <div class="container container-fluid">
      <div class="card px-5 pt-3 pb-5 mb-5">
        <h5>${account.accountType}: ${account.id}</h5>
        <div class="row mb-3">
          <div class="col-md-12">
            <label class="form-label">Account Balance: ${
              account.currentBalance
            }</label>
          </div>
        </div>
        <div class="row mb-3">
          <div class="col-md-12">
            <input type="number" class="form-control form-control-sm" id="amountInput" placeholder="Please input amount in $">
            <p id="amountInputErrorMessage" style="color: red; display: none;"></p>
          </div>
        </div>
        <div class="row mb-1">
          <div class="col-md-12">
            <div class="d-grid gap-2 d-md-block">
              <button type="submit" class="btn btn-sm btn-primary" id="withdraw">Withdraw</button>
              <button type="submit" class="btn btn-sm btn-primary" id="deposit">Deposit</button>
            </div>
          </div>
        </div>
        <div class="row mb-3">
          <div class="col-md-12">
            <div class="dropdown">
              <button class="btn btn-sm btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Transfer To
              </button>
              <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                ${accounts
                  .filter((a) => a.id !== account.id)
                  .map(
                    (a) =>
                      `<button class="dropdown-item" type="button" id="${a.id}">${a.accountType} ${a.id}</button>`
                  )}
              </div>
           </div>
          </div>
        </div>
        <div class="mb-3">
          <a href="userTransactions.html?accountId=${
            account.id
          }" class="text-decoration-none">
              View Transactions
          </a>
        </div>
    </div>
</div> 
        `;
    accountContainer.append(bryDiv);
  }
}

//on load userAccount page for currently logged in user
// if (sessionStorage){
// }
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
async function withdraw(id) {
  console.log(id);

  let amount = document.getElementById("input-" + id).value;

  console.log(amount);

  let errorMessage = document.getElementById("ErrorMessage-" + id);

  if (!amount) {
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
        amount: amount,
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
async function deposit(id) {
  console.log(id);

  let amount = document.getElementById("input-" + id).value;

  console.log(amount);

  let errorMessage = document.getElementById("ErrorMessage-" + id);

  if (!amount) {
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
        amount: amount,
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
