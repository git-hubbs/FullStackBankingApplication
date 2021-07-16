
function addRow(){
	
	let xhr = new XMLHttpRequest();
	const url = "customer";

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4){
			// console.log(xhr.status);

			if(xhr.status == 200){
				console.log(xhr.responseText);

				customerList = JSON.parse(xhr.responseText);

				customerList.forEach(account => {
						addAccount(account);
				});
			}
		}
	}
	
	xhr.open("GET", url);
	xhr.send();
};

function addAccount(account){
	console.log("adding account")
	let table = document.getElementById("accounts_table");
	let tableRow = document.createElement("tr");
	
	let account_id = document.createElement("td");
	let account_balance = document.createElement("td");
	
	tableRow.appendChild(account_id);
	tableRow.appendChild(account_balance);
	
	table.appendChild(tableRow);
	
	account_id.innerHTML = account.account_id;
	account_balance.innerHTML = account.balance;
	
};