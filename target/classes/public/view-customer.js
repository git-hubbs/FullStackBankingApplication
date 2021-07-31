window.onload = function(){
	document.getElementById("deposit_button").addEventListener("click", depositIntoAccount);
	document.getElementById("withdraw_button").addEventListener("click", withdrawFromAccount);
	document.getElementById("transfer_button").addEventListener("click", transferBetweenAccounts);
	getCustomerAccounts();
}
function getCustomerAccounts(){
	
	let xhr = new XMLHttpRequest();
	const url = "customer";

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4){
			// console.log(xhr.status);

			if(xhr.status == 200){
				console.log(xhr.responseText);

				customerList = JSON.parse(xhr.responseText);

				customerList.forEach(account => {
						addAccountToTable(account);
				});
			}
		}
	}
	
	xhr.open("GET", url);
	xhr.send();
};

function addAccountToTable(account){
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


function depositIntoAccount(event){
	//window.location.replace("http://localhost:9000/customer.html");
	
	const url = "deposit";
	 let xhr = new XMLHttpRequest();
	 let amount = document.getElementById("deposit_amount_field").value;
	 let account_id = document.getElementById("deposit_id_field").value;
	 
	console.log(xhr.status);
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4){

			if(xhr.status == 200){
                window.location.replace("http://localhost:9000/customer.html");
			}
			if(xhr.status == 300){
				alert("Deposit failed, please try again!");
			}
		}
	}
	xhr.open("PUT", url);
	
	
	    let deposit =  {
			account_id : account_id,
			amount : amount
			
	};
    //console.log(JSON.stringify(account));
    xhr.send(JSON.stringify(deposit));
}

function withdrawFromAccount(event){
	//window.location.replace("http://localhost:9000/customer.html");
	
	const url = "withdraw";
	 let xhr = new XMLHttpRequest();
	 let amount = document.getElementById("withdraw_amount_field").value;
	 let account_id = document.getElementById("withdraw_id_field").value;
	 
	console.log(xhr.status);
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4){

			if(xhr.status == 200){
                window.location.replace("http://localhost:9000/customer.html");
			}
			if(xhr.status == 300){
				alert("Withdraw failed, please try again!");
			}
		}
	}
	xhr.open("PUT", url);
	
	
	    let withdraw =  {
			account_id : account_id,
			amount : amount
			
	};
    //console.log(JSON.stringify(account));
    xhr.send(JSON.stringify(withdraw));
}

function transferBetweenAccounts(event){
	//window.location.replace("http://localhost:9000/customer.html");
	
	const url = "transfer";
	 let xhr = new XMLHttpRequest();
	 let amount = document.getElementById("transfer_amount_field").value;
	 let source_account_id = document.getElementById("source_id_field").value;
	 let target_account_id = document.getElementById("target_id_field").value;
	 
	console.log(xhr.status);
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4){

			if(xhr.status == 200){
                window.location.replace("http://localhost:9000/customer.html");
			}
			if(xhr.status == 300){
				alert("Transfer failed, please try again!");
			}
		}
	}
	xhr.open("PUT", url);
	
	
	    let transfer =  {
			sourceAccountID : source_account_id,
			targetAccountID : target_account_id,
			amount : amount
			
	};
    //console.log(JSON.stringify(account));
    xhr.send(JSON.stringify(transfer));
}