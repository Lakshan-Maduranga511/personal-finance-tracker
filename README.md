# Personal Finance Tracker

A robust Java-based personal finance management application that enables users to track accounts, manage transactions, categorize spending, and generate comprehensive financial reports. Built with modern software engineering principles including design patterns, reflection, functional programming, and comprehensive unit testing.

## 📋 Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Architecture & Design Patterns](#architecture--design-patterns)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Running Tests](#running-tests)
- [Code Quality](#code-quality)
- [Contributing](#contributing)
- [License](#license)
- [Author](#author)

## ✨ Features

- **Account Management**: Create, edit, and manage multiple account types (Cash, Bank, Credit Card)
- **Transaction Tracking**: Record income and expense transactions with detailed categorization
- **Category Management**: Organize spending with custom categories and budget limits
- **Financial Reports**: Generate comprehensive reports with income/expense summaries and budget analysis
- **User Authentication**: Secure login system with file-based user credential storage
- **Data Persistence**: File-based storage system for accounts, transactions, and categories

## 🛠 Technologies Used

- **Language**: Java 21 (LTS)
- **IDE**: NetBeans
- **Build System**: Apache Ant
- **Testing Framework**: JUnit 4
- **Design Patterns**: Factory, Singleton, Observer
- **Advanced Concepts**: 
  - Java Reflection API
  - Functional Programming (Streams, Lambdas, Method References)
  - Clean Code Principles
  - SOLID Principles

## 📁 Project Structure

```
personal-finance-tracker/
│
├── src/                          # Source code
│   ├── Main.java                 # Application entry point
│   ├── factory/                  # Factory pattern implementations
│   │   └── AccountFactory.java
│   ├── models/                   # Domain models
│   │   ├── Account.java
│   │   ├── Category.java
│   │   └── Transaction.java
│   ├── services/                 # Business logic services
│   │   ├── AccountService.java
│   │   ├── CategoryService.java
│   │   ├── LoginService.java
│   │   ├── ReportService.java
│   │   └── TransactionService.java
│   └── util/                     # Utility classes
│       ├── FileUtil.java
│       ├── InputUtil.java
│       └── ReflectionUtil.java
│
├── test/                         # Unit tests
│   └── test/
│       ├── AccountServiceTest.java
│       ├── AccountTest.java
│       ├── CategoryServiceTest.java
│       ├── LoginServiceTest.java
│       ├── MainRoutingTest.java
│       ├── ReportServiceHelperTest.java
│       ├── ReportServiceTest.java
│       └── TransactionServiceTest.java
│
├── data/                         # Data storage files
│   ├── accounts.txt
│   ├── users.txt
│   └── categories.txt
│
├── build/                        # Compiled classes (generated)
├── nbproject/                    # NetBeans project files
├── build.xml                     # Ant build configuration
└── README.md
```

## 🏗 Architecture & Design Patterns

### Design Patterns Implemented

1. **Factory Pattern** (`AccountFactory.java`)
   - Centralizes object creation for different account types
   - Simplifies account instantiation throughout the application
   - Makes the system extensible for new account types

2. **Singleton Pattern** (`CategoryService.java`)
   - Ensures single instance of CategoryService across the application
   - Manages shared category data efficiently
   - Reduces memory overhead and prevents duplicate instances

3. **Observer Pattern** (Future Integration)
   - Prepared structure for event-driven functionality
   - Enables notification system for transaction events

### Advanced Techniques

- **Java Reflection**: Dynamic method invocation for flexible menu routing and testing
- **Functional Programming**: Extensive use of Streams, Lambda expressions, and method references
- **Clean Code**: Adherence to SOLID principles, DRY, and separation of concerns

### Refactoring Highlights

The project demonstrates extensive refactoring to eliminate code smells:
- **Long Method**: Extracted methods for improved readability
- **Primitive Obsession**: Replaced with domain objects
- **Feature Envy**: Moved logic to appropriate classes
- **Data Clumps**: Consolidated related data into objects
- **God Class**: Separated responsibilities into focused services

## 📦 Prerequisites

- **Java Development Kit (JDK) 21** or higher
- **Apache NetBeans IDE** (recommended) or any Java IDE
- **Apache Ant** (for building via command line)
- **Git** (for version control)

## 🚀 Installation

### 1. Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/personal-finance-tracker.git
cd personal-finance-tracker
```

### 2. Open in NetBeans

1. Open NetBeans IDE
2. Go to `File` → `Open Project`
3. Navigate to the cloned `personal-finance-tracker` folder
4. Click `Open Project`

### 3. Build the Project

**Using NetBeans:**
- Right-click on the project → `Clean and Build`

**Using Command Line:**
```bash
ant clean
ant compile
```

## 💻 Usage

### Running the Application

**Using NetBeans:**
1. Right-click on the project
2. Select `Run`

**Using Command Line:**
```bash
ant run
```

Or directly with Java:
```bash
java -cp build/classes Main
```

### Default Credentials

Check the `data/users.txt` file for existing user credentials, or create new accounts through the application's registration feature.

### Application Flow

1. **Login/Register**: Authenticate or create a new account
2. **Main Menu**: Access various features
   - Account Management
   - Transaction Management
   - Category Management
   - Financial Reports
3. **Manage Data**: Create, edit, or delete records
4. **View Reports**: Generate financial summaries and insights

## 🧪 Running Tests

### Using NetBeans
1. Right-click on the project
2. Select `Test`

### Using Command Line
```bash
ant test
```

### Test Coverage

The project includes comprehensive unit tests covering:
- Model classes (Account, Category, Transaction)
- Service layer logic
- Reflection-based utilities
- File operations
- Business logic validation

## 📊 Code Quality

This project demonstrates:

- ✅ **Clean Code Principles**: Meaningful names, small focused methods
- ✅ **SOLID Principles**: Single responsibility, open/closed, dependency inversion
- ✅ **Design Patterns**: Factory, Singleton, with Observer pattern structure
- ✅ **Unit Testing**: Comprehensive JUnit test coverage
- ✅ **Functional Programming**: Streams, lambdas, immutability
- ✅ **Java Reflection**: Dynamic method invocation for flexibility
- ✅ **Code Refactoring**: Eliminated code smells systematically

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Coding Standards

- Follow Java naming conventions
- Write unit tests for new features
- Maintain clean code principles
- Update documentation as needed

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👤 Author

**M.L.M. Lakshan Perera**

## 🙏 Acknowledgments

- NetBeans Community
- Java Community for extensive documentation and support

---

**Note**: This project was developed as part of an academic assessment to demonstrate advanced software engineering concepts including refactoring, design patterns, reflection, and functional programming in Java.
