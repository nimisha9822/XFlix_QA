# XFlix Automation

## Scope of Work

This project focuses on automating the following test cases for the XFlix application:

1. **Verify the XFlix Homepage URL**
   - Ensures that the homepage URL loads correctly.

2. **Verify Search Functionality**
   - Validates the search feature by checking results for various search queries.

3. **Verify the Functionality of Filters**
   - Tests the filtering options to ensure that they work as expected.

4. **Verify the Functionality of Upload Video**
   - Automates the process of uploading a video and validates the success of the operation.

5. **Verify the Like Counter Functionality**
   - Tests the like counter feature, ensuring that likes are counted and preserved across sessions and tabs.

---

## Skills Used

- **Selenium**: Used for automating the browser-based testing of the XFlix web application.
- **Java**: The programming language used to implement the automation scripts.
- **XPath**: Utilized to locate elements on the web pages effectively.

---

## Directory Structure

```
XFlix_Automation/
├── src/
│   ├── testcases/
│   │   ├── VerifyHomepageURLTest.java
│   │   ├── VerifySearchFunctionalityTest.java
│   │   ├── VerifyFiltersFunctionalityTest.java
│   │   ├── VerifyUploadVideoTest.java
│   │   ├── VerifyLikeCounterFunctionalityTest.java
│   ├── utils/
│       ├── WebDriverSetup.java
│       ├── TestDataLoader.java
├── testng.xml
├── README.md
```

---

## Setup Instructions

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd XFlix_Automation
   ```

2. Install dependencies:
   - Ensure you have **Java JDK 11+** installed.
   - Install **Selenium WebDriver** and required browser drivers (e.g., ChromeDriver).
   
3. Configure `testng.xml`:
   - Define your test suite and include/exclude test cases as required.

4. Run the test cases:
   ```bash
   mvn test
   ```

---

## Features

- **Comprehensive Test Coverage**: Ensures key functionalities of XFlix are tested.
- **Reusable Components**: Includes reusable methods for WebDriver setup and data loading.
- **Cross-Browser Compatibility**: Supports tests on multiple browsers.
- **Detailed Reporting**: Generates test execution reports with TestNG.

---

## Notes

- Ensure the required browser driver is compatible with the browser version in use.
- Keep the test data up-to-date in the `TestDataLoader.java` file.
- The `VerifyLikeCounterFunctionalityTest.java` ensures that like states are preserved across tabs and sessions.

