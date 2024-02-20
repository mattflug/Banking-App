let accounts = [];

let iceCreamContainer = document.getElementById("IceCreamContainer");
console.log(iceCreamContainer);

// This function is the same from the DOM-manip.js file, just adding in one new line to reset the container everytime we filter
function populateFlavors(accounts) {
  // This is us emptying the container before we attempt to refill it
  iceCreamContainer.innerHTML = "";

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

    iceCreamContainer.append(iDiv);
  }
}

(async () => {
  let data = await fetch("http://localhost:8080/users/1/accounts");
  let res = await data.json(); // Different syntax, same thing as before
console.log(res);
  accounts = res;
  populateFlavors(accounts);
})();


