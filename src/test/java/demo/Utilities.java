package demo;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Utilities {

    public static void waitForElementClickable(WebDriver driver, WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForElementVisibility(WebDriver driver, WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    public static void scrollToBottom(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }


    public static ArrayList<HashMap<String, Object>> scrapeTableData(WebDriver driver, String rowXpath, int pageCount) throws InterruptedException {
        ArrayList<HashMap<String, Object>> dataList = new ArrayList<>();
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        for (int page = 1; page <= pageCount; page++) {
            List<WebElement> rows = driver.findElements(By.xpath(rowXpath));
            for (WebElement row : rows) {
                String teamName = row.findElement(By.xpath("//td[1]")).getText();
                int year = Integer.parseInt(row.findElement(By.xpath("//td[2]")).getText());
                double winPercentage = Double.parseDouble(row.findElement(By.xpath("//td[6]")).getText());
                if (winPercentage > 0.40) {
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("Epoch Time", Instant.now().toEpochMilli());
                    data.put("Team Name", teamName);
                    data.put("Year", year);
                    data.put("Win Percentage", winPercentage);
                    dataList.add(data);
                    Thread.sleep(2000);
                }
            }
            try {
                WebElement nextPageLink = driver.findElement(By.xpath("//ul[@class='pagination']//li[" + pageCount + "]/a"));
                wait.until(ExpectedConditions.elementToBeClickable(nextPageLink)).click();
            } catch (Exception e) {
                break;
            }
            Thread.sleep(2000);
        }
        return dataList;
    }

    public static void writeDataToJsonFile(List<HashMap<String, Object>> dataList, String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(filePath),dataList);
    }

    public static ArrayList<HashMap<String, Object>> scrapeMoviesData(WebDriver driver) throws InterruptedException {
        ArrayList<HashMap<String, Object>> dataList = new ArrayList<>();
        List<WebElement> years = driver.findElements(By.xpath("//div[@class='col-md-12 text-center']//a"));
        for (WebElement year : years) {
            year.click();
            Thread.sleep(5000);
            List<WebElement> movies = driver.findElements(By.xpath("//table[@class='table']//tr"));
            int count = 0;
            for (WebElement movie : movies) {
                count++;
                if (count>5) 
                {
                    break;
                }
                String movieTitle = movie.findElement(By.xpath("//td[1]")).getText();
                int nominations = Integer.parseInt(movie.findElement(By.xpath("//td[2]")).getText());
                int awards = Integer.parseInt(movie.findElement(By.xpath("//td[3]")).getText());
                String movie_year = year.getText();
                boolean isWinner = count == 1;
                HashMap<String, Object> movieData = new HashMap<>();
                movieData.put("EPOCH TIME", Instant.now().toEpochMilli());
                movieData.put("Movie Title", movieTitle);
                movieData.put("Nominations", nominations);
                movieData.put("Awards", awards);
                movieData.put("Years", movie_year);
                movieData.put("Is Winner", isWinner);
                dataList.add(movieData);
                System.out.print(movieData);
            }
            driver.navigate().back();
            Thread.sleep(2000);
        }
        return dataList;
    }


    public static String generateXPath(String linkText) {
        return "//h1[text()='Web Scraping Sandbox']/..//div//h3/a[text()='" + linkText + "']";
    }

    public static WebElement selectSandboxWithName(WebDriver driver, String xpathExpression) {
        return driver.findElement(By.xpath((xpathExpression)));
    }





}
