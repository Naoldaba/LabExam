# HibretStock Trading Platform

## Overview

HibretStock is a stock trading and analysis platform built using Java and Spring Boot. It provides users with features such as stock trading, market analysis, portfolio management, and account services. The system follows a layered architecture with controllers, services, and data access objects (DAOs) to ensure modularity and scalability.

## Features

- **User Authentication**: Sign up, log in, and account management.
- **Stock Trading**: Buy and sell stocks.
- **Market Analysis**: View stock trends.
- **User Dashboard**: Track transactions.
- **Watchlist Management**: Add/remove stocks to/from a watchlist.
- **Admin Features**: User verification and platform management.
- **Error Handling & Logging**: Centralized error handling and logging.

## Project Structure

```
├── pom.xml  # Maven project configuration
├── src
│   ├── main
│   │   ├── java/com/trading/hibretstock
│   │   │   ├── Application.java  # Main entry point
│   │   │   ├── Configuration
│   │   │   │   └── AppConfiguration.java  # Spring Boot application configuration
│   │   │   ├── Controllers  # Handles HTTP requests
│   │   │   ├── Services  # Business logic layer
│   │   │   ├── Dao  # Data access layer
│   │   │   ├── Models  # Entity definitions
│   │   │   ├── Utils  # Utility classes
│   │   │   └── Validations  # Input validation logic
│   │   └── resources  # Configuration and static files
│   ├── test  # Unit and integration tests
└── target  # Compiled binaries and resources
```

## Installation & Setup

### Prerequisites

- Java 17+
- Maven 3.8+
- MySQL or PostgreSQL (configured in `application.properties`)

### Steps

1. Clone the repository:
   ```sh
   git clone https://github.com/Bisratasaye12/labexam.git
   cd hibretstock
   ```
2. Build the project:
   ```sh
   mvn clean install
   ```
3. Run the application:
   ```sh
   mvn spring-boot:run
   ```
4. Access the platform at `http://localhost:8080`

## API Endpoints

| Endpoint              | Method | Description             |
| --------------------- | ------ | ----------------------- |
| `/api/auth/signup`    | POST   | User registration       |
| `/api/auth/login`     | POST   | User login              |
| `/api/stocks/analyze` | GET    | Stock market analysis   |
| `/api/trade/buy`      | POST   | Buy stocks              |
| `/api/trade/sell`     | POST   | Sell stocks             |
| `/api/user/dashboard` | GET    | Get user dashboard data |

## Configuration

Modify `src/main/resources/application.properties` to configure:

- Database connection
- Server port
- Security settings



# Group Members

1. Naol Daba         UGR/4777/14
2. Bisrat Asaye      UGR/8508/14
3. Abdulazez Zeinu   UGR/1223/14