# Revature Bank

## Description
Revature Bank provides common banking features over a functional web based user and adminstrator interface. Users can create bank accounts with initial deposits and once approved, make withdraws, deposits, and transfers. An administrator may approve, deny, or remove accounts at his discretion.

## Technologies Used
- Java
- PostgreSQL
- Maven
- JDBC
- Javascript
- HTML
- CSS
- Javalin
- Ajax
- JUnit
- Log4j

## Features
- New users can create user accounts
- Users can apply to open new bank accounts with a starting balance
- Users can deposit, withdraw, and make transfers to valid accounts
- Administrators can view customer accounts
- Administrators can approve, reject, or delete bank accounts

## Getting Started
- Clone branch using the command: git clone --single-branch --branch main https://github.com/git-hubbs/FullStackBankingApplication.git
- Create a PostgreSQL database using the script
- If applicable, change the port number and database name fields in ConnectionFactoryImpl.java


## Usage
- Create your initial user account
- Login using your user credentials and apply for an account with an initial deposit
- Logout of your user account
- Login using the Adminstrator account credentials hardcoded in AuthControllerImpl.java
- Approve the account
- Logout, and log back into your user account to use the approved account

## License
- This project uses the following license: Apache License, Version 2.0.
