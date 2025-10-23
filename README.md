# Library Management System

![CI Pipeline](https://github.com/YOUR_USERNAME/YOUR_REPO/workflows/CI%20Pipeline/badge.svg)

A simple Java library management system for evaluating code quality tools like DeepSource and Codacy.

## Project Overview

This is a plain vanilla Java project (no Spring or other frameworks) that implements a realistic library management system with:

- **Domain Models**: Book, Member, Loan with proper encapsulation
- **Service Layer**: Business logic for library operations
- **Utility Classes**: Validation, date handling, and string operations
- **Exception Handling**: Custom exceptions for business logic errors
- **Comprehensive Tests**: 160+ unit tests with 95% code coverage

## Features

### Book Management
- Add, update, and remove books
- Search books by title, author, or category
- Track available copies

### Member Management
- Register and manage library members
- Different membership types (Basic, Premium, Student, Faculty)
- Member activation/deactivation

### Loan Management
- Issue books to members
- Return books with automatic fine calculation
- Track overdue loans
- Enforce loan limits based on membership type

## Project Structure

```
src/
├── main/java/com/bidgely/library/
│   ├── model/          # Domain models (Book, Member, Loan)
│   ├── service/        # Business logic (LibraryService)
│   ├── util/           # Utility classes (Validation, Date, String)
│   └── exception/      # Custom exceptions
└── test/java/com/bidgely/library/
    ├── model/          # Model tests
    ├── service/        # Service tests
    └── util/           # Utility tests
```

## Building and Testing

### Prerequisites
- Java 8 or higher
- Maven 3.6 or higher

### Build the project
```bash
mvn clean compile
```

### Run tests
```bash
mvn test
```

### Generate code coverage report
```bash
mvn clean test
```

The JaCoCo coverage report will be generated at `target/site/jacoco/index.html`

## CI/CD Pipeline

This project includes a GitHub Actions workflow that automatically:
- ✅ Builds the project on every push to `main` branch
- ✅ Runs all 160 unit tests
- ✅ Generates JaCoCo coverage reports (XML and HTML)
- ✅ Uploads coverage to DeepSource for diff coverage analysis
- ✅ Publishes test results

The workflow runs on:
- Push to `main` branch
- Pull requests targeting `main` branch

### DeepSource Integration

The workflow automatically uploads coverage reports to DeepSource for:
- **Diff Coverage Analysis** - See coverage changes in pull requests
- **Code Quality Metrics** - Track code quality over time
- **Automated Code Review** - Get suggestions for improvements

#### Setup DeepSource:

1. Sign up at [DeepSource](https://deepsource.io/)
2. Activate your repository
3. Get your DSN (Data Source Name) from DeepSource dashboard
4. Add it as a GitHub secret: `Settings` → `Secrets and variables` → `Actions` → `New repository secret`
   - Name: `DEEPSOURCE_DSN`
   - Value: Your DSN from DeepSource

The `.deepsource.toml` configuration file is already included in the repository.

### Viewing CI Results

1. Go to the **Actions** tab in your GitHub repository
2. Click on the latest workflow run
3. Check the test results in the workflow summary
4. View coverage analysis on DeepSource dashboard

## Code Coverage

Current coverage metrics:
- **Instruction Coverage**: 95%
- **Branch Coverage**: 83%
- **Line Coverage**: 95%
- **Total Tests**: 160

## Code Quality

This project is designed to be analyzed by code quality tools like:
- DeepSource
- Codacy
- SonarQube

The codebase includes:
- Well-structured code with proper separation of concerns
- Comprehensive test coverage
- Proper exception handling
- Input validation
- Clean code practices

## License

This is a sample project for evaluation purposes.

