let accounts = [];

let accountContainer = document.getElementById("userContainer");

const logoutButton = document.getElementById("logout");
logoutButton.addEventListener('click', logoutFunction);

function populateAccounts(accounts) {
  accountContainer.innerHTML = "";

  for (account of accounts) {
    let bryDiv = document.createElement("div");

      bryDiv.innerHTML = `
      <a href="userTransactions.html?accountId=${account.id}" class="text-decoration-none">
      <div class="card">
          <div class="card-body">
              <h2 class="card-title">${account.accountType}</h2>
              <p class="card-text">${account.accountNumber}</p>
              <p class="card-text"> Account Balance: ${account.currentBalance}</p>
              <button type="submit" class="btn btn-primary">Withdraw</button>
              <button type="submit" class="btn btn-primary">Deposit</button>
          </div>
      </div>
  </a>      
        `;
    accountContainer.append(bryDiv);
  }
}

// Get to user accounts, need to edit this to take in the user who's
  // logged in
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