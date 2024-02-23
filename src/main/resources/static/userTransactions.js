const url = "http://localhost:8080";
const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
console.log(urlParams.get("accountId"));

let transactions = [];
let searchButton = document.getElementById("search");
searchButton.addEventListener('click', (e)=>{
    e.preventDefault();
    searchByDate();
})

async function searchByDate() {
    try {
        let fromDate = document.getElementById("fromDate").value;
        let toDate = document.getElementById("toDate").value;
        const response = await fetch(`http://localhost:8080/transactions/${urlParams.get("accountId")}/filter?fromDateStr=${fromDate}&toDateStr=${toDate}`);
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        let res = await response.json();
        console.log(res);
        transactions = res;
        let transactionBody = document.getElementById("transactionBody");
        transactionBody.innerHTML = ''; 
        populateTransactions(transactions);
    } catch (error) {
        console.error('Error fetching transactions:', error);
        
    }
}


function populateTransactions(transactions) {
    let amount = "";
    let transactionBody = document.getElementById("transactionBody"); 
    transactionBody.innerHTML = '';
  for (let transaction of transactions) {

    if (transaction.transactionType === "WITHDRAWAL" || transaction.transactionType === "TRANSFER_OUTGOING") {
        amount = ` -$${transaction.amount}`; 
    } else if (transaction.transactionType === "DEPOSIT" || transaction.transactionType === "TRANSFER_INCOMING") {
        amount = ` +$${transaction.amount}`;
    }
    let tableRows = document.createElement("tr");
    tableRows.innerHTML = `
        <td>${transaction.date}</td>
        <td>${transaction.transactionType}</td>
        <td>${amount}</td>
        <td>$${transaction.balance}</td>`;
    transactionBody.appendChild(tableRows);
  }
}


(async () => {
  let data = await fetch(`http://localhost:8080/transactions/${urlParams.get("accountId")}`);
  let res = await data.json();
  console.log(res);
  transactions = res;
  populateTransactions(transactions);
})();