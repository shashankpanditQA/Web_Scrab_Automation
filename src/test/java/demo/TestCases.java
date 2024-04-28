package demo;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.out.println("Setting up the test environment...");
        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(option);
        driver.get("https://www.scrapethissite.com/pages/");
        driver.manage().window().maximize();

    }

    @AfterMethod
    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.quit();

    }

    @Test
    public void testCase01() throws InterruptedException, JsonGenerationException, JsonMappingException, IOException {
        System.out.println("Start Test case: testCase01");
        String xpath = Utilities.generateXPath("Hockey Teams: Forms, Searching and Pagination");
        WebElement sandboxlinks = Utilities.selectSandboxWithName(driver, xpath);
        SeleniumWrapper.clickAction(driver, sandboxlinks);
        ArrayList<HashMap<String, Object>> dataList = Utilities.scrapeTableData(driver,
                "//table[@class='table']/tbody/tr", 4);
        Utilities.writeDataToJsonFile(dataList, "src/test/resources/hockey-team-data.json");
        File jsonFile = new File("src/test/resources/hockey-team-data.json");
        assert jsonFile.exists() && jsonFile.length() > 0;
    }

    @Test
    public void testCase02() throws InterruptedException, JsonGenerationException, JsonMappingException, IOException {
        System.out.println("Start Test case: testCase02");
        String xpath =Utilities.generateXPath("Oscar Winning Films: AJAX and Javascript");
        WebElement movies_sandbox =  Utilities.selectSandboxWithName(driver, xpath);
        Thread.sleep(5000);
        SeleniumWrapper.clickAction(driver,movies_sandbox);
        Thread.sleep(5000);
        ArrayList<HashMap<String, Object>> dataList = Utilities.scrapeMoviesData(driver);
        Utilities.writeDataToJsonFile(dataList, "src/test/resources/movies.json");
        File jsonFile = new File("src/test/resources/movies.json");
        assert jsonFile.exists() && jsonFile.length() > 0;

    }

}
