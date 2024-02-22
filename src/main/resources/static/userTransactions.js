const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
console.log(urlParams.get("accountId"));

let transactions = [];
let transactionContainer = document.getElementById("transactionContainer");

function populateTransactions(transactions) {
  for (let transaction of transactions) {
    let tableRows = document.createElement("tr");
    tableRows.innerHTML = `
        <td>${transaction.date}</td>
        <td>${transaction.transactionType}</td>
        <td>${transaction.amount}</td>
        <td>${transaction.balance}</td>`;
    transactionContainer.append(tableRows);
  }
}


(async () => {
  let data = await fetch(`http://localhost:8080/transactions/${urlParams.get("accountId")}`);
  let res = await data.json();
  console.log(res);
  transactions = res;
  populateTransactions(transactions);
})();