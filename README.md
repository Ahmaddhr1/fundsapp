# FundsApp Backend

## Features

- **User Authentication**: Secure JWT-based login and registration system
- **Financial Transactions**: Deposit money and transfer funds between users
- **Account Management**: Check balance and view transaction history
- **Protected Routes**: All endpoints secured with JWT authentication
- **Database Integration**: Connected to Supabase PostgreSQL
- **Docker Deployment**: Containerized deployment on Render cloud platform

## Tech Stack

- **Backend Framework**: Spring Boot 3.x
- **Security**: Spring Security 6 + JWT
- **Database**: PostgreSQL (Supabase)
- **Deployment**: Docker + Render
- **Build Tool**: Maven
- **Language**: Java 17+

## API Endpoints

### Authentication
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - User login

### Transactions
- `POST /api/transactions/deposit` - Deposit money
- `POST /api/transactions/transfer` - Transfer money to another user
- `GET /api/transactions/history` - Get transaction history

### User
- `GET /api/users/get` - Get user's account data  