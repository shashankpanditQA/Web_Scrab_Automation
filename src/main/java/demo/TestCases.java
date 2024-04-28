package demo;

import java.util.Arrays;
import java.util.List;

import org.bouncycastle.oer.its.ieee1609dot2.basetypes.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Instant;
import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    WebDriver driver;



    public TestCases() {
        System.out.println("Constructor: TestCases");
          WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01() throws InterruptedException {
        System.out.println("Start Test case:Google Form");
        driver.get("https://forms.gle/wjPkzeSEk1CM7KgGA");
        Thread.sleep(7000);
        By Selector =By.xpath("//span[text()='Name']/ancestor::div[@class='geS5n']//div/input");
        writeText(driver,Selector,"Shashank Pandit");
       By Selector_practing_Automation_text= By.xpath("//textarea[@aria-label='Your answer']");
       write_practing_Automation(driver,Selector_practing_Automation_text,"I want to be the best QA Engineer!"+calculateEpoachTimeToString(0));
       
        scrollByPixels(driver,500);
       //How much experience do you have in Automation Testing?
       By selectorradio = By.xpath("//span[text()='3 - 5']/ancestor::div[@class='lLfZXe fnxRtf cNDBpf']//div[@class='Od2TWd hYsg7c']");
       selectExpRadioButton(driver, selectorradio, "0 - 3");
       
       
        // How should you be addressed?
        By selector = By.xpath("//span[text()='How should you be addressed?']/ancestor::div[@class='geS5n']//div//span[@class='vRMGwf oJeWuf']");
        selectFromDropdown(driver, selector, "Rather not say");

    //    //Checkbox 
    //    By SelectorCheckbox = By.xpath("//span[text()='Which of the following have you learned in Crio.Do for Automation Testing?']/ancestor::div[4]//span[text()='TestNG']");
    //    selectCheckBox(driver,SelectorCheckbox);

        // By SelectorAllCheckBoxes = By.xpath("//span[text()='Which of the following have you learned in Crio.Do for Automation Testing?']/ancestor::div[4]//span[@class='aDTYNe snByac n5vBHf OIC90c']");
        // selectAllCheckboxOptions(driver,SelectorAllCheckBoxes);
       List<String> options = Arrays.asList("Java", "Selenium", "TestNG");
        selectCheckboxesByOptions(driver,options);
        scrollByPixels(driver,1000);
        // By SelectorPM_AM_dropdown = By.xpath("//div[@class='ry3kXd']/ancestor::div[@class='jgvuAb ybOdnf f0pfFe t9kgXb llrsB']//span");
        // selectPM_AM_dropDown(driver,SelectorPM_AM_dropdown,"PM");
         By selectorDate = By.xpath("//input[@type='date']");
         enterDate(driver, selectorDate, calculateDateTimeToString("dd/MM/YYY",(long)6.048e+8));


         By Selector_Hour = By.xpath("//div[@class='vEXS5c']//input[@aria-label='Hour']");
         By Selector_min = By.xpath("//div[@class='vEXS5c']//input[@aria-label='Minute']");
         writeText_HHFormat(driver,Selector_Hour,calculateDateTimeToString("HH",0));
         writeText_MMFormat(driver,Selector_min,calculateDateTimeToString("mm", 0));
         
         By selector_submit=By.xpath("//span[text()='Submit']");
         SubmitButton(driver,selector_submit);

         // Validate Response Message 
         By SelectorResponse = By.xpath("//div[text()='Thanks for your response, Automation Wizard!']");
         String actual_Result = validateResponse(driver,SelectorResponse);
         System.out.println(actual_Result.equalsIgnoreCase("'Thanks for your response, Automation Wizard!'"));
        System.out.println("End Test case: testCase01");
 
    }
    // Wrapper methods

    private static void writeText(WebDriver driver, By selector, String sendText) throws InterruptedException {
        System.out.println("Trying to send text: " + sendText);
        WebElement element = driver.findElement(selector);
        element.clear();
        Thread.sleep(2000);
        element.sendKeys(sendText);
        System.out.print("Sucess");
    }

    private static void selectExpRadioButton(WebDriver driver,By Selector,String exp) throws InterruptedException
    {
        // WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        // WebElement radioButtonElement = wait.until(ExpectedConditions.elementToBeClickable(Selector));
        WebElement radio= driver.findElement(Selector);
        radio.click();
    


    //     List<WebElement> checkboxes = driver.findElements(Selector);
    //    for (WebElement option : checkboxes) {
    //     String optionText = option.getText().trim();
    //     if (optionText.equalsIgnoreCase(exp)) {
    //         option.click();
    //         System.out.println("Selected option: " + exp);
    //         return; // Exit the method after selecting the option
    //     }
    // }

    // // If the loop completes without finding the desired option
    // System.out.println("Option not found: " + exp);



    }
    public void testCase02() throws InterruptedException {
        System.out.println("Start Test case:Flipkart");
        driver.get("https://www.flipkart.com/");
    
        try {
            // Find the search input field using XPath
            WebElement searchInput = new WebDriverWait(driver,java.time.Duration.ofSeconds(20))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@title,'Search for products')]")));
            
            // Enter "Washing Machine" into the search input field
            searchInput.sendKeys("Washing Machine");

            // Simulate pressing Enter key
            searchInput.sendKeys(Keys.ENTER);

            // Wait for search results page to load
            new WebDriverWait(driver, java.time.Duration.ofSeconds(20))
                    .until(ExpectedConditions.urlContains("/search?q=Washing+Machine"));

            // Printing success message
            System.out.println("Search for Washing Machine completed successfully.");
        } catch (Exception e) {
            // Print error message if any exception occurs
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            // Close the browser
            driver.quit();
        }
    

    
    }



    private static void selectFromDropdown(WebDriver driver, By selector, String text) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
       WebElement dropdownElement = wait.until(ExpectedConditions.presenceOfElementLocated(selector));
       dropdownElement.click();
       Thread.sleep(5000);
       List<WebElement> dropdownOptions = driver.findElements(selector);
        for (WebElement option : dropdownOptions) {
            String optionText = option.getText().trim();
            if (optionText.equalsIgnoreCase(text)) {
                option.click();
                System.out.println("Selected option: " + text);
                return; // Exit the method after selecting the option
            }
        }

        // If the loop completes without finding the desired option
        System.out.println("Option not found: " + text);

    }


    
    public static void scrollByPixels(WebDriver driver, int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + pixels + ");");
    }

    private static void selectMultipleCheckBoxes(WebDriver driver, By selector) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
        WebElement checkboxElement = wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        
        // Get the text of the checkbox
        String checkboxText = checkboxElement.getText();
        
        // Check if the text contains "Java"
        if (checkboxText.contains("TestNG")) {
            System.out.println("Checkbox with text 'TestNG' is present");
            Thread.sleep(500);
            checkboxElement.click();
        } else {
            System.out.println("Checkbox with text 'TestNG' is not present");
        }
    }


    private static void selectAllCheckboxOptions(WebDriver driver, By selector) throws InterruptedException
    {
        List<WebElement> options = driver.findElements(selector);
        for (WebElement option : options) {
            Thread.sleep(500);
            option.click();
         option.click();
        }

    }
   
    public static void selectCheckboxesByOptions(WebDriver driver, List<String>otions) throws InterruptedException
    {
        for (String option : otions) {
        {
             String xpath = "//span[text()='Which of the following have you learned in Crio.Do for Automation Testing?']/ancestor::div[4]//span[text()='"+option+"']";
             By Selector = By.xpath(xpath);
             selectChbox(driver,Selector);
        }
    }
}

private static void selectChbox(WebDriver driver, By selector) throws InterruptedException 
{
    WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
    WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(selector));
    Thread.sleep(2000);
    checkbox.click();
}

// private static void selectPM_AM_dropDown(WebDriver driver ,By Selector,String Element) throws InterruptedException
// {
//     WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
//     WebElement dropdownElement = wait.until(ExpectedConditions.presenceOfElementLocated(Selector));
//     dropdownElement.click();
//     List<WebElement> dropDownElement = driver.findElements(Selector);

//      for (WebElement option :dropDownElement)
//      {
//           String ele = option.getText();
//           if(ele.equalsIgnoreCase(Element))
//           {
//             Thread.sleep(2000);
//             option.click();
//           }
//      }
     
// }

private static void writeText_HHFormat(WebDriver driver, By selectorhour, String hours) throws InterruptedException {
    System.out.println("Trying to send text: " + hours);
    WebElement hour_element = driver.findElement(selectorhour);
    hour_element.clear();
    Thread.sleep(2000);
    hour_element.sendKeys(hours);
    System.out.print("Sucess");
}

private static void writeText_MMFormat(WebDriver driver, By selectormin, String mm) throws InterruptedException {
    System.out.println("Trying to send text: " + mm);
    WebElement min_element = driver.findElement(selectormin);
    min_element.clear();
    Thread.sleep(2000);
    min_element.sendKeys(mm);
    System.out.print("Sucess");
}

private static void write_practing_Automation(WebDriver driver,By selector,String sendtext) throws InterruptedException
{
    
    System.out.println("Trying to send text: "+ sendtext);
    WebElement text = driver.findElement(selector);
    text.clear();
    Thread.sleep(2000);
    text.sendKeys(sendtext);
}

private static String calculateEpoachTimeToString(int offsetInMs)
{
    Instant now = Instant.now();
    // Apply the offset in milliseconds to the current instant
    Instant newInstant = now.plusMillis(offsetInMs);
    // Convert the instant to epoch time in milliseconds
    long epochTimeWithOffset = newInstant.toEpochMilli();
    // Convert epoch time to string
    return String.valueOf(epochTimeWithOffset);
}

private static String calculateDateTimeToString(String formatedString,long offsetInMs)
{       LocalDateTime myDateObj = LocalDateTime.now();
        Long seconds =offsetInMs /1000;
        Long nano=(offsetInMs%1000)*1000000;
        LocalDateTime newDateTime = myDateObj.minus(java.time.Duration.ofSeconds(seconds,nano));
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern(formatedString);
        String formattedTime = newDateTime.format(formatter);
        return formattedTime;

}

private static void enterDate(WebDriver driver,By Selector,String date)
{
    System.out.println("Trying to send text: " +date);
    WebElement dat = driver.findElement(Selector);
    dat.clear();
    dat.sendKeys(date);
   
}

private static void SubmitButton(WebDriver driver , By Selector) throws InterruptedException
{
   WebElement submitButton = driver.findElement(Selector);
   Thread.sleep(2000);
   submitButton.click();

   Thread.sleep(5000);
}


private static String validateResponse(WebDriver driver ,By Selector)
{
   WebElement response = driver.findElement(Selector);
   String responseText = response.getText();
   return responseText;

}
















}