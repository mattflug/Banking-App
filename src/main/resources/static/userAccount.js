const url = "http://localhost:8080";

let accounts = [];

let accountContainer = document.getElementById("userContainer");
console.log(accountContainer);

const logoutButton = document.getElementById("logout");
logoutButton.addEventListener("click", logoutFunction);

// transfer function
async function transfer(fromAccountId, toAccountId) {
  let amount = document.getElementById(`amountInput-${fromAccountId}`).value;
  let amountErrorMessage = document.getElementById(
    `amountInputErrorMessage-${fromAccountId}`
  );

  // Validate amount input
  if (!amount) {
    amountErrorMessage.style.display = "block";
    amountErrorMessage.innerHTML = "Please enter a valid amount";
    return;
  }

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
    const response = await res.json();
    console.log(response);
    let jsonResponse = JSON.stringify(response);
    console.log(jsonResponse);
    amountErrorMessage.style.display = "none";
    window.location.href = "userAccount.html";
  } catch (e) {
    console.log(e);
    amountErrorMessage.style.display = "block";
    amountErrorMessage.innerHTML = "Insufficient funds";
    return;
  }
}

function populateAccounts(accounts) {
  accountContainer.innerHTML = "";

  for (account of accounts) {
    let bryDiv = document.createElement("div");

    bryDiv.innerHTML = `
    <div class="container container-fluid">
      <div class="card px-3 pt-3 pb-5 mb-5">
        <h5>${account.accountType}: ${account.id}</h5>
        <div class="row mb-3">
          <div class="col-md-12">
            <label class="form-label">Account Balance: ${
              account.currentBalance
            }</label>
          </div>
        </div>
        <div class="row mb-2">
          <div class="col-md-12">
            <div class="input-group">
              <input type="number" class="form-control" id="amountInput-${
                account.id
              }" placeholder="Transfer Amount...">
              <button class="btn btn-outline-secondary dropdown-toggle" id="${
                account.id
              }-transfer" type="button" data-bs-toggle="dropdown" aria-expanded="false" style="font-size: 18px;"></button>
              <ul class="dropdown-menu" aria-labelledby="${
                account.id
              }-transfer">
                ${accounts
                  .filter((a) => a.id !== account.id)
                  .map(
                    (a) =>
                      `<button class="dropdown-item" type="button" id="${account.id}-${a.id}">${a.accountType} ${a.id}</button>`
                  )}
              </ul>
            </div>
            <div class="form-text text-danger small mb-2" id="amountInputErrorMessage-${
              account.id
            }"></div>
          </div>
        </div>
        <div class="row mb-2">
          <div class="col-md-12">

          <div class="mb-3">
          <input type="number" class="form-control" id="input-${account.id}" placeholder=" withdraw/deposit amount">
          </div>

            <div class="d-grid gap-2 d-md-block">
              <button type="submit" class="btn btn-sm btn-primary" onclick="withdraw(${account.id})">Withdraw</button>
              <button type="submit" class="btn btn-sm btn-primary" onclick="deposit(${account.id})">Deposit</button>
            </div>
            <br>
            <p id="ErrorMessage-${account.id}" style="color: red; display: none;">Error:</p>
            <br>
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
  bindAccounts(accounts);
}

function bindAccounts(accounts) {
  accounts.forEach((account) => {
    const otherAccounts = accounts.filter((a) => a.id !== account.id);
    otherAccounts.forEach((otherAccount) => {
      const transferButton = document.getElementById(
        `${account.id}-${otherAccount.id}`
      );
      transferButton.addEventListener("click", async (e) => {
        e.preventDefault();
        await transfer(account.id, otherAccount.id);
      });
    });
  });
}

//on load userAccount page for currently logged in user
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
