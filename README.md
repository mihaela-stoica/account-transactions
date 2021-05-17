**How to run this app ?** 

Step 1 : mvn clean install spring-boot:repackage
Step 2 : run Application

**Available APIs**

Login :             localhost:8080/login/gigi
Create account :    localhost:8080/account
Create transaction: localhost:8080/account/{accountId}/transaction
Get transactions:   localhost:8080/account/{accountId}/transaction?fromDate="2021-04-04 11:30"&toDate="2021-09-04 11:30"
Close account:      localhost:8080/account/{accountId}

