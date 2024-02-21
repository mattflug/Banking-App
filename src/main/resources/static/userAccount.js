let accounts = [];

let accountContainer = document.getElementById("IceCreamContainer");
console.log(accountContainer);

function populateFlavors(accounts) {
  accountContainer.innerHTML = "";

  for (account of accounts) {
    let iDiv = document.createElement("div");

    iDiv.innerHTML = `
            <h2> Account Number: ${account.accountNumber} </h2>
            <p> Scoops available: ${account.accountType} </p>
            <p> Dairy Free: ${account.dairyFree ? "Yes" : "No"} </p>
        `;

    iDiv.setAttribute("class", "ice-cream");
    iDiv.setAttribute("id", `${account.flavor}`);

    iDiv.setAttribute("onclick", "updateStyling(event)");

    accountContainer.append(iDiv);
  }
}

  // Get to user accounts
(async () => {
  let data = await fetch("http://localhost:8080/users/1/accounts");
  let res = await data.json(); // Different syntax, same thing as before
  console.log(res);
  accounts = res;
  populateFlavors(accounts);
})();
