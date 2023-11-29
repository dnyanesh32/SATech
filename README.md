# Tendable Automation Challenge

## How to Run

1. Clone the repository from GitHub.
2. Set the path to your ChromeDriver executable in the `SeleniumAutomation.java` file.
3. Run the `SeleniumAutomation.java` file.

## Description

This test project automates the evaluation of elements on the Tendable website according to the specified requirements.

### Test Scenarios

1. **Confirm accessibility of the top-level menus:**
   - Navigates to Home, Our Story, Our Solution, and Why Tendable pages.

2. **Verify "Request a Demo" button:**
   - Checks the presence and activity of the "Request a Demo" button on each top-level menu page.

3. **Submit form with empty "Message" field:**
   - Navigates to "Contact Us", chooses "Marketing", and submits the form with an empty "Message" field.
   - Verifies the appearance of the error message.

4. **Bug Report:**
   - If the test for point 3 fails, a bug report is generated.

## Strategy

The test script uses Selenium WebDriver to automate interactions with the Tendable website. It follows a modular structure with methods for each test scenario. The `Readme.md` file provides instructions for running the test project and describes the implemented test scenarios.

Feel free to adapt the code and instructions based on your specific needs or preferences. If you have any questions or need further clarification, please let me know.
