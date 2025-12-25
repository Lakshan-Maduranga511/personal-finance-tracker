# Contributing to Personal Finance Tracker

Thank you for considering contributing to the Personal Finance Tracker project! This document provides guidelines and instructions for contributing.

## 📋 Table of Contents

- [Code of Conduct](#code-of-conduct)
- [How Can I Contribute?](#how-can-i-contribute)
- [Development Setup](#development-setup)
- [Coding Standards](#coding-standards)
- [Commit Guidelines](#commit-guidelines)
- [Pull Request Process](#pull-request-process)
- [Testing Guidelines](#testing-guidelines)

## 🤝 Code of Conduct

This project adheres to a code of conduct that all contributors are expected to follow:

- Be respectful and inclusive
- Welcome newcomers and help them learn
- Focus on what is best for the community
- Show empathy towards other community members

## 🎯 How Can I Contribute?

### Reporting Bugs

Before creating bug reports, please check existing issues to avoid duplicates. When creating a bug report, include:

- **Clear title and description**
- **Steps to reproduce** the issue
- **Expected behavior** vs actual behavior
- **Java version** and OS
- **Screenshots** if applicable
- **Error logs** or stack traces

### Suggesting Enhancements

Enhancement suggestions are tracked as GitHub issues. When creating an enhancement suggestion:

- Use a clear and descriptive title
- Provide a detailed description of the proposed feature
- Explain why this enhancement would be useful
- Include examples of how the feature would work

### Code Contributions

1. **Fork the repository**
2. **Create a feature branch** from `main`
3. **Make your changes** following the coding standards
4. **Write or update tests** for your changes
5. **Ensure all tests pass**
6. **Commit your changes** with clear messages
7. **Push to your fork**
8. **Submit a pull request**

## 🛠 Development Setup

### Prerequisites

- JDK 21 or higher
- NetBeans IDE (or your preferred Java IDE)
- Git

### Setting Up Your Development Environment

1. **Fork and clone the repository:**
   ```bash
   git clone https://github.com/YOUR_USERNAME/personal-finance-tracker.git
   cd personal-finance-tracker
   ```

2. **Open in NetBeans:**
   - File → Open Project
   - Navigate to the cloned directory
   - Open the project

3. **Build the project:**
   ```bash
   ant clean
   ant compile
   ```

4. **Run tests:**
   ```bash
   ant test
   ```

## 📝 Coding Standards

### Java Style Guide

Follow these conventions to maintain code consistency:

#### Naming Conventions

- **Classes**: PascalCase (e.g., `AccountService`, `TransactionManager`)
- **Methods**: camelCase (e.g., `addTransaction`, `calculateBalance`)
- **Variables**: camelCase (e.g., `accountName`, `totalAmount`)
- **Constants**: UPPER_SNAKE_CASE (e.g., `MAX_AMOUNT`, `DEFAULT_CATEGORY`)
- **Packages**: lowercase (e.g., `services`, `models`, `util`)

#### Code Structure

- **One class per file**
- **Maximum method length**: 20-30 lines (prefer smaller)
- **Maximum class length**: 300 lines
- **Use meaningful names**: No single-letter variables except in loops
- **Add comments** for complex logic
- **Keep methods focused**: One responsibility per method

#### Design Principles

- **SOLID Principles**: Follow Single Responsibility, Open/Closed, Liskov Substitution, Interface Segregation, and Dependency Inversion
- **DRY (Don't Repeat Yourself)**: Avoid code duplication
- **KISS (Keep It Simple, Stupid)**: Write simple, clear code
- **Clean Code**: Meaningful names, small functions, clear structure

### Functional Programming

When applicable, use functional programming concepts:

```java
// Good: Using streams
List<Account> activeAccounts = accounts.stream()
    .filter(account -> account.isActive())
    .collect(Collectors.toList());

// Avoid: Traditional loops when streams are clearer
List<Account> activeAccounts = new ArrayList<>();
for (Account account : accounts) {
    if (account.isActive()) {
        activeAccounts.add(account);
    }
}
```

### Error Handling

- Use specific exception types
- Provide meaningful error messages
- Handle exceptions at appropriate levels
- Don't swallow exceptions silently

```java
// Good
try {
    processTransaction(transaction);
} catch (InsufficientFundsException e) {
    System.err.println("Error: " + e.getMessage());
    // Handle specific exception
}

// Avoid
try {
    processTransaction(transaction);
} catch (Exception e) {
    // Empty catch block
}
```

## 📤 Commit Guidelines

### Commit Message Format

```
<type>(<scope>): <subject>

<body>

<footer>
```

### Types

- **feat**: New feature
- **fix**: Bug fix
- **docs**: Documentation changes
- **style**: Code style changes (formatting, missing semicolons, etc.)
- **refactor**: Code refactoring
- **test**: Adding or updating tests
- **chore**: Maintenance tasks

### Examples

```
feat(transaction): add support for recurring transactions

- Added RecurringTransaction model
- Updated TransactionService to handle recurring logic
- Added unit tests for recurring transactions

Closes #123
```

```
fix(account): resolve balance calculation error

Fixed issue where negative balances were not properly handled
in the calculateBalance method.

Fixes #456
```

## 🔄 Pull Request Process

1. **Update documentation** if needed (README, JavaDoc, etc.)
2. **Add tests** for new functionality
3. **Ensure all tests pass** before submitting
4. **Update the CHANGELOG** if applicable
5. **Follow the PR template** (if provided)
6. **Request review** from maintainers
7. **Address review comments** promptly

### PR Title Format

Use the same format as commit messages:
```
feat(scope): brief description of changes
```

### PR Description Template

```markdown
## Description
Brief description of what this PR does

## Type of Change
- [ ] Bug fix
- [ ] New feature
- [ ] Breaking change
- [ ] Documentation update

## Testing
- [ ] Unit tests added/updated
- [ ] All tests passing
- [ ] Manual testing completed

## Checklist
- [ ] Code follows project style guidelines
- [ ] Self-review completed
- [ ] Comments added for complex code
- [ ] Documentation updated
- [ ] No new warnings generated
```

## 🧪 Testing Guidelines

### Writing Tests

- **Test each public method** in service and model classes
- **Use descriptive test names**: `testAddTransactionWithValidData()`
- **Follow AAA pattern**: Arrange, Act, Assert
- **Use meaningful assertions** with clear messages
- **Mock external dependencies** when needed

### Test Structure

```java
@Test
public void testAddTransactionWithValidData() {
    // Arrange
    TransactionService service = new TransactionService();
    Transaction transaction = new Transaction("Income", "Salary", 5000.0);
    
    // Act
    boolean result = service.addTransaction(transaction);
    
    // Assert
    assertTrue("Transaction should be added successfully", result);
    assertEquals("Transaction count should be 1", 1, service.getTransactionCount());
}
```

### Test Coverage

Aim for:
- **Minimum 70% code coverage**
- **100% coverage** for critical business logic
- Test **happy paths and edge cases**
- Test **error conditions**

## 🎨 Design Pattern Usage

When contributing, consider using appropriate design patterns:

- **Factory Pattern**: For object creation
- **Singleton Pattern**: For shared resources
- **Observer Pattern**: For event handling
- **Strategy Pattern**: For algorithm variations

## 📚 Documentation

- **JavaDoc**: Add for all public classes and methods
- **Inline comments**: For complex logic
- **README updates**: For new features or setup changes
- **CHANGELOG**: Document notable changes

### JavaDoc Example

```java
/**
 * Adds a new transaction to the system.
 * 
 * @param transaction The transaction to add
 * @return true if transaction was added successfully, false otherwise
 * @throws IllegalArgumentException if transaction is null
 * @throws InsufficientFundsException if account balance is insufficient
 */
public boolean addTransaction(Transaction transaction) {
    // Implementation
}
```

## 🤔 Questions?

If you have questions:
- Open an issue with the `question` label
- Reach out to the maintainers
- Check existing documentation

## 🙏 Thank You!

Your contributions help make this project better for everyone. Thank you for taking the time to contribute!

---

**Note**: These guidelines may evolve over time. Please check back periodically for updates.