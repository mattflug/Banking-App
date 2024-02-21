let accounts = [];

let accountContainer = document.getElementById("IceCreamContainer");
console.log(accountContainer);

function populateFlavors(accounts) {
  accountContainer.innerHTML = "";

  for (account of accounts) {
    let bryDiv = document.createElement("div");

      bryDiv.innerHTML = `
            <form>
            <div class="mb-3">
            <h2 for="savingsAccount" class="form-label">${account.accountType}</h2>
              <label for="savingsAccount" class="form-label">Account Balance:</label>
              <label for="savingsAccount" class="form-label">${account.currentBalance}</label>
            </div>
            <div class="mb-3">
              <input type="number" class="form-control" id="savingsAccount" placeholder="please input amount in $">
            </div>
            <button type="submit" class="btn btn-primary">Withdraw</button>
            <button type="submit" class="btn btn-primary">Deposit</button>

            <br>
            <br>
            <br>
        </form>
            
        `;
    accountContainer.append(bryDiv);
  }
}

// Get to user accounts, need to edit this to take in the user who's
  // logged in
(async () => {
  let data = await fetch("http://localhost:8080/users/1/accounts");
  let res = await data.json(); // Different syntax, same thing as before
  console.log(res);
  accounts = res;
  populateFlavors(accounts);
})();
