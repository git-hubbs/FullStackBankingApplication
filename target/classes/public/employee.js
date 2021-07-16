window.addEventListener('load', 
  function() { 
    addCustomers();
  }, false);

function addCustomers(){
	
	let xhr = new XMLHttpRequest();
	const url = "customers";

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4){
			// console.log(xhr.status);

			if(xhr.status == 200){
				console.log(xhr.responseText);

				customerList = JSON.parse(xhr.responseText);

				customerList.forEach(customer => {
						addAccountRow(customer);
				});
			}
		}
	}
	xhr.open("GET", url);
	xhr.send();
}


function addAccountRow(account){
	console.log("adding account")
	let table = document.getElementById("customer_account_table");
	let tableRow = document.createElement("tr");
	
	let customer_id = document.createElement("td")
	let account_id = document.createElement("td");
	let balance = document.createElement("td");
	let approval = document.createElement("td");
	
	tableRow.appendChild(customer_id);
	tableRow.appendChild(account_id);
	tableRow.appendChild(balance);
	tableRow.appendChild(approval);
	
	table.appendChild(tableRow);
	
	customer_id.innerHTML = account.customer_id;
	account_id.innerHTML = account.account_id;
	balance.innerHTML = account.balance;
	approval.innerHTML = account.approved;
	
};


