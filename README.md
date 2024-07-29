# API TESTING- PetStore Project

This project is an automated testing framework for the RESTful Pet Store API, developed using Maven, Java, and Cucumber. The goal is to validate the API's functionality through behavior-driven development (BDD) practices. Tests are designed to cover various endpoints of the API, ensuring reliability and performance. The framework utilizes RestAssured for seamless integration with REST services, enabling easy and effective API testing.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

Ensure you have the following software installed on your machine:

- Java 17
- Maven
- IntelliJ IDEA


### Installing
First, clone the repository to your local machine:
```bash 
git clone https://github.com/roxanapaula23/api-testing.git
```
This project uses Maven for managing dependencies. To install all the necessary dependencies, run the following command in the project root directory:
```bash
mvn clean install
```

#### Install IntelliJ IDEA Required Plugins:

When you first open the project in IntelliJ IDEA, it will prompt you to install two plugins: Cucumber and Gherkin. Follow these steps to install the plugins:
1. Navigate to File > Settings > Plugins
2. Search for Cucumber for Java and Gherkin plugins
3. Install the plugins by clicking the Install button next to each
4. Restart IntelliJ IDEA when prompted

### Running tests
To run the tests in the project setup, follow these steps:
1. Navigate to src/test/java/com/automation/api/CucumberTestRunner.java
2. To run the tests, you can right-click on the CucumberTestRunner.java file and select Run 'CucumberTestRunner'.
   The tests should start executing in the console of your IntelliJ IDEA
