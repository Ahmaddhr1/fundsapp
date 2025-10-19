# FundsApp Backend


## Get started
1-Create a Supabase account
  a-create a project
  b-click connect
  c-get the JDBC url and paste in the dot env file on DB_URL and the other values as well


2-Create a .env file having the following:
DB_URL=...
DB_USERNAME=....
DB_PASSWORD=....
JWT_SECRET=....

3-add .env to .gitignore

4-Before running make sure you have Java SDK installed on your machine.

### Note if you clone:

git clone <repo-url>
cd <directory>


5-Run the project through the run button or using this command
"./mvnw spring-boot:run"
to access the routes mention down

6-Project will run on localhost:8080 so make your requests on postman like this
GET http://localhost:8080/api/auth/register


## Tech Stack

- **Backend Framework**: Spring Boot
- **Security**: Spring Security + JWT
- **Database**: PostgreSQL (Supabase)
- **Deployment**: Docker + Render
- **Build Tool**: Maven
- **Language**: Java

## Features

- **User Authentication**: Secure JWT-based login and registration system
- **Financial Transactions**: Deposit money and transfer funds between users
- **Account Management**: Check balance and view transaction history
- **Protected Routes**: All endpoints secured with JWT authentication
- **Database Integration**: Connected to Supabase PostgreSQL
- **Docker Deployment**: Containerized deployment on Render cloud platform



## API Endpoints

### Authentication
- `POST /api/auth/register` - Register new user (public) body: username,password
- `POST /api/auth/login` - User login (public)  body: username,password

### Transactions
- `POST /api/transactions/deposit` - Deposit money (protected) body:amount  headers: Authorization Token 
- `POST /api/transactions/transfer` - Transfer money to another user (protected) body:amount,receiverAccountNumber headers: Authorization Token
- `GET /api/transactions/history` - Get transaction history (protected) headers: Authorization Token

### User
- `GET /api/users/get` - Get user's account data (protected) headers: Authorization Token
