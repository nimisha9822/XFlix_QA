package demo;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    ChromeDriver driver;
    WebDriverWait wait;

    public TestCases() {
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().timeout(120).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01() {
        System.out.println("Start Testcase01");
        // Navigate to Xflix homepage
        driver.get("https://xflix-qa.vercel.app/");

        // Get the current URL
        String currentUrl = driver.getCurrentUrl();

        // Verify if the URL contains "xflix"
        boolean isURLValid = currentUrl.contains("xflix");

        // Print the result
        if (isURLValid) {
            System.out.println("Test Passed: The URL of the Xflix homepage contains 'xflix'.");
        } else {
            System.out.println("Test Failed: The URL of the Xflix homepage does not contain 'xflix'.");
        }

        System.out.println("Ending Testcase01");
    }

    public void testCase02() throws InterruptedException {
        System.out.println("Start Testcase02");
        // Navigate to the Xflix homepage
        driver.get("https://xflix-qa.vercel.app/");

        // Locate the search bar and enter a valid keyword
        WebElement searchInput = driver.findElement(By.xpath("//*[@id='root']/div[1]/div[2]/input"));
        searchInput.sendKeys("frameworks");
        Thread.sleep(3000);
        searchInput.click();

        // Verify relevant results are displayed
        WebElement resultsMessage=driver.findElement(By.xpath("//*[@id='root']/div[4]"));
        if (resultsMessage.isDisplayed()) {
            System.out.println("Test Passed: Relevant results are displayed for a valid keyword.");
        } else {
            System.out.println("Test Failed: Relevant results are not displayed for a valid keyword.");
        }

        // Clear the search bar and enter an invalid keyword
        searchInput.clear();
        searchInput.sendKeys("selenium");
        Thread.sleep(3000);
        searchInput.click();

         // Verify error message is displayed for invalid keyword
         WebElement errorMessage = driver.findElement(By.xpath("//div[contains(text(),'No Search is found')]"));
        
         if (errorMessage.isDisplayed()) {
             System.out.println("Test Passed: Error message is displayed for an invalid keyword.");
         } else {
             System.out.println("Test Failed: Error message is not displayed for an invalid keyword.");
         }

         System.out.println("Ending Testcase02");
    }

    public void testCase03() throws InterruptedException {
        System.out.println("Start testcase03");
         // Navigate to the Xflix homepage
         driver.get("https://xflix-qa.vercel.app/");

         // Record all movies displayed in default view
         List<WebElement> defaultMovies = driver.findElements(By.xpath("//*[@id='root']/div[4]"));
         List<String> defaultMovieTitles = new ArrayList<>();
         for (WebElement movie : defaultMovies) {
             defaultMovieTitles.add(movie.getText());
         }
        
 
         // Click on the Sort filter and select 'View Count'
         WebElement sortFilter = driver.findElement(By.xpath("//*[@id='sortBySelect']"));
         Thread.sleep(2000);
         Select selectSortFilter = new Select(sortFilter);
         selectSortFilter.selectByVisibleText("Sort By: View Count");
 
         // Wait for the results to update using the WebDriverWait from the constructor
         wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='root']/div[4]")));
 
         // Get the sorted movies after applying the filter
         List<WebElement> sortedMovies = driver.findElements(By.xpath("//*[@id='root']/div[4]"));
         List<String> sortedMovieTitles = new ArrayList<>();
         for (WebElement movie : sortedMovies) {
             sortedMovieTitles.add(movie.getText());
         }
 
         // Compare the sorted results with the default view
         if (!defaultMovieTitles.equals(sortedMovieTitles)) {
             System.out.println("Test Passed: The order of results changes when the Sort filter is applied.");
         } else {
             System.out.println("Test Failed: The order of results remains the same after applying the Sort filter.");
         }

         System.out.println("Ending testcase03");
    }

    public void testCase04() throws InterruptedException {
        System.out.println("Starting testcase04");
        // Navigate to the upload page
        driver.get("https://xflix-qa.vercel.app/");

        // Click on the upload button without filling any fields
        WebElement uploadButton = driver.findElement(By.xpath("//*[@id='root']/div[1]/button"));
        Thread.sleep(3000);
        uploadButton.click();

        WebElement clickUpload=driver.findElement(By.xpath("//*[@id='root']/div[1]/div[3]/div/form/div/button[1]"));
        Thread.sleep(2000);
        clickUpload.click();

        wait.until(ExpectedConditions.alertIsPresent());

        // Confirm that an alert is displayed for empty fields
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println(alertText);
        if (alertText.contains("VideoLink should not be empty.")) {
            System.out.println("Test Passed: An alert is displayed when fields are left empty.");
            alert.accept(); // Accept the alert
        } else {
            System.out.println("Test Failed: An alert is not displayed when fields are left empty.");
            alert.dismiss(); // Dismiss the alert
        }

        // Fill in all required fields
        WebElement vdoLink = driver.findElement(By.xpath("//*[@id='root']/div[1]/div[3]/div/form/input[1]"));
        Thread.sleep(2000);
        // vdoLink.sendKeys("<iframe width='966' height='543' src='https://www.youtube.com/embed/MUMCZZl9QCY' title='smthn' KEYS &amp; IDEAS 🔧 Hindi Cartoons for Kids' frameborder='0' allow='accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share' referrerpolicy='strict-origin-when-cross-origin' allowfullscreen></iframe>");
        vdoLink.sendKeys("<iframe width='560' height='315' src='https://www.youtube.com/embed/hz10meosqeE?si=RoVQ_c1eHGKzSolr' title='YouTube video player' frameborder='0' allow='accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share' referrerpolicy='strict-origin-when-cross-origin' allowfullscreen></iframe>");

        WebElement imgLink = driver.findElement(By.xpath("//*[@id='root']/div[1]/div[3]/div/form/input[2]"));
        Thread.sleep(2000);
        imgLink.sendKeys("https://img.freepik.com/free-photo/colorful-design-with-spiral-design_188544-9588.jpg");

        WebElement title=driver.findElement(By.xpath("//*[@id='root']/div[1]/div[3]/div/form/input[3]"));
        Thread.sleep(2000);
        title.sendKeys("Sample title");

        WebElement genre=driver.findElement(By.xpath("//*[@id='genre-modal-dropdown']"));
        // WebElement genreDropdown = driver.findElement(By.xpath("//select[@id='genreDropdown']"));

        // Create a Select object for the dropdown
        Select selectGenre = new Select(genre);

        // Select an option from the dropdown by visible text
        selectGenre.selectByVisibleText("Education"); // Change "Action" to the desired genre option

        // Get the selected option to verify
        WebElement selectedOption = selectGenre.getFirstSelectedOption();
        String selectedGenre = selectedOption.getText();
        
        // Print the selected genre for verification
        System.out.println("Selected Genre: " + selectedGenre);
        
      // Locate the age dropdown element
     WebElement ageDropdown = driver.findElement(By.xpath("//select[@id='age-modal-dropdown']"));

      // Create a Select object for the dropdown
      Select selectAge = new Select(ageDropdown);

     // Select an option from the dropdown by visible text
     selectAge.selectByVisibleText("7+"); // Change "18-24" to the desired age range option

    // Get the selected option to verify
     WebElement selectedOption1= selectAge.getFirstSelectedOption();
     String selectedAgeRange = selectedOption1.getText();

    // Print the selected age range for verification
     System.out.println("Selected Age Range: " + selectedAgeRange);
        //   uploadButton.click();

    //sending the dates
    WebElement dates=driver.findElement(By.xpath("//*[@name='releaseDate']"));
    Thread.sleep(2000);
    dates.sendKeys("30/05/2024");

    //click on uplaod again 
    WebElement clickUpload1=driver.findElement(By.xpath("//*[@id='root']/div[1]/div[3]/div/form/div/button[1]"));
        Thread.sleep(2000);
        clickUpload1.click();

        Alert alert1 = driver.switchTo().alert();
        String alertText1 = alert1.getText();
        System.out.println(alertText1);
        if (alertText.contains("Video Posted Successfully!")) {
            System.out.println("Test Passed: Video Posted Successfully!.");
            alert.accept(); // Accept the alert
        } else {
            System.out.println("Test Failed: Video not Posted Successfully!");
            alert.dismiss(); // Dismiss the alert
        }

      System.out.println("Ending testcase04");
    }

    public void testCase05() throws InterruptedException {
        System.out.println("Starting testcase05");
         driver.get("https://xflix-qa.vercel.app/");

        // Click on the video to open it
        WebElement videoElement = driver.findElement(By.xpath("//*[@id='60331f421f1d093ab5424475']/div"));
        Thread.sleep(2000);
        videoElement.click();

        // Get the initial like count
        WebElement likeCounterElement = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/button[1]"));
        System.out.println(likeCounterElement.getText());
        // String initialLikeCountText = likeCounterElement.getText().trim();
        
        WebElement buttonEle = driver.findElement(By.xpath("//button[@class='btn btn-like']"));
        System.out.println(buttonEle.getText());

        String currURL = driver.getCurrentUrl();
        String parentWindow = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        ArrayList<String> windowHandlesList = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windowHandlesList.get(1));
        driver.get(currURL);
        buttonEle = driver.findElement(By.xpath("//button[@class='btn btn-like']"));
        System.out.println(buttonEle.getText());
        driver.close();
        driver.switchTo().window(parentWindow);
        System.out.println("End Test case: testCase05");


    }
}