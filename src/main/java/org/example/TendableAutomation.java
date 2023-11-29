package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileWriter;
import java.io.IOException;

public class TendableAutomation {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\dnyaneshwarmisal\\Downloads\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        try {
            // 1. Confirm accessibility of the top-level menus
            String[] menuItems = {"Our Story", "Our Solution", "Why Tendable"};
            for (String menuItem : menuItems) {
                navigateToMenuItem(driver, menuItem);
            }

            // 2. Verify "Request a Demo" button presence and activity
            verifyRequestDemoButton(driver);

            // 3. Navigate to "Contact Us", choose "Marketing", and submit form with empty "Message" field
            clickOnContactUs(driver, "Contact Us");

            chooseContactCategory(driver, "Marketing");
            submitContactForm(driver, "");
            verifyErrorMessage(driver);
            // 4. Verify the error message and generate bug report if test fails
//            if (!verifyErrorMessage(driver)) {
//                generateBugReport("Test Case 123", "Submitting the form without filling the 'Message' field does not display the expected error message.");
//            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    private static void navigateToMenuItem(WebDriver driver, String menuItem) throws InterruptedException {
        driver.get("https://www.tendable.com");
        driver.manage().window().maximize();
        Thread.sleep(6000);
        WebElement menu = driver.findElement(By.xpath("//nav[@id=\"main-navigation-new\"]/ul/li/a"));
        menu.click();
        System.out.println("Navigated to: " + menuItem);
    }

    private static void clickOnContactUs(WebDriver driver, String menuItem) throws InterruptedException {
        driver.get("https://www.tendable.com");

        WebElement contactUsLink = driver.findElement(By.xpath("//a[text()='Contact Us']"));
        contactUsLink.click();
        Thread.sleep(6000);

        System.out.println("Navigated to: " + menuItem);
    }

    private static void verifyRequestDemoButton(WebDriver driver) {
        WebElement requestDemoButton = driver.findElement(By.linkText("Request a Demo"));
        if (requestDemoButton.isDisplayed() && requestDemoButton.isEnabled()) {
            System.out.println("Request a Demo button is present and active.");
        } else {
            System.out.println("Request a Demo button is NOT present or active.");
        }
    }

    private static void chooseContactCategory(WebDriver driver, String category) {
        WebElement contactButton = driver.findElement(By.xpath("//div[contains(text(), '" + category + "')]/following-sibling::div/button[@class='body-button bg-plain-600 toggle-control']"));
        contactButton.click();

        System.out.println("Selected Marketing category and clicked on contact button link");
    }

    private static void submitContactForm(WebDriver driver, String message) throws InterruptedException {
        WebElement submitButton = driver.findElement(By.xpath("//button[@class='button' and text()='Submit']"));
        // Thread.sleep(5000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
        System.out.println("Contact Form Successfully Submitted");
    }

    private static boolean verifyErrorMessage(WebDriver driver) throws InterruptedException {
        Thread.sleep(6000);
        try {
            WebElement errorMessage = driver.findElement(By.xpath("//div[@class='ff-form-errors']/p[text()='Sorry, there was an error submitting the form. Please try again.']"));
            if (errorMessage.isDisplayed()) {
                System.out.println("Error message is displayed. Test PASSED.");
                return true;
            } else {
                System.out.println("Error message is NOT displayed. Test FAILED.");
                generateBugReport("Test Case 123", "Error message element is not found on the page. Incorrect XPath used.");
                return false;
            }
        } catch (NoSuchElementException e) {
            System.out.println("Error message element not found. Test FAILED.");
            generateBugReport("Test Case 123", "Error message element not found on the page. Incorrect XPath used.");
            return false;
        }
    }


    private static void generateBugReport(String testName, String description) {
        try (FileWriter writer = new FileWriter("bug_reports.txt", true)) {
            writer.write("Test Name: " + testName + "\n");
            writer.write("Description: " + description + "\n");
            writer.write("-------------\n");
            System.out.println("Bug report generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error generating bug report: " + e.getMessage());
        }
    }

}
