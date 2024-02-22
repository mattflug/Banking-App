let accounts = [];

let accountContainer = document.getElementById("userContainer");
console.log(accountContainer);

const logoutButton = document.getElementById("logout");
logoutButton.addEventListener('click', logoutFunction);

function populateFlavors(accounts) {
  accountContainer.innerHTML = "";

  for (account of accounts) {
    let bryDiv = document.createElement("div");

      bryDiv.innerHTML = `
            <form>
            <div class="mb-3">
            <h2 for="savingsAccount" class="form-label">${account.accountType} Account, ID: ${account.id}</h2>
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


//on load userAccount page for currently logged in user
if (sessionStorage){
  (async () => {
  let data = await fetch(`http://localhost:8080/users/${sessionStorage.getItem("id")}/accounts`);
  let res = await data.json(); // Different syntax, same thing as before
  console.log(res);
  accounts = res;
  populateFlavors(accounts);
})();
}else{
  window.location.href = "login.html";
}

function logoutFunction(){
  sessionStorage.removeItem("id");
}