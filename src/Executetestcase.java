import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class Executetestcase {
	public static WebDriver driver;
	public static String driverPath = "E:\\Chrome\\";
	public static String Userlist = "//table[@table-title='Smart Table example']";

	public static void initWebDriver(String URL) {
        // Setting up Chrome driver path.
		System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
		// Launching Chrome browser.
		driver = new ChromeDriver();
		driver.get(URL);
		driver.manage().window().maximize();
	}

	public static void main(String[] args) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		//init WebDriver
        initWebDriver("http://www.way2automation.com/angularjs-protractor/webtables/");
        //Validate for we are on user list table
        boolean Userlist = driver.findElement(By.xpath("//table[@table-title='Smart Table example']")).isDisplayed();
        //boolean Userlist;
        if(Userlist) {
          System.out.println("Yes you are on the User List Table");
          
          for (int i = 1; i < 3; i++) {
        	  //click add user button
              driver.findElement(By.xpath("//button[text()=' Add User']")).click();
              //wait until element to load
        	  wait.until(ExpectedConditions.elementToBeClickable((By.name("FirstName")))).sendKeys("FName" + i);
        	  wait.until(ExpectedConditions.elementToBeClickable((By.name("LastName")))).sendKeys("LName" + i);
        	  //ensure for User name uniqueness
        	  wait.until(ExpectedConditions.elementToBeClickable((By.name("User")))).sendKeys("FName" + i);
        	  wait.until(ExpectedConditions.elementToBeClickable((By.name("Password")))).sendKeys("Pass" + i);
        	  getElement(By.xpath("//input[@value='15']")).click();
	          Select dropdown1 = new Select(driver.findElement(By.name("RoleId")));  
	     	  dropdown1.selectByVisibleText("Admin");
	          driver.findElement(By.name("Email")).sendKeys("admin@mail.com");
	          driver.findElement(By.name("Mobilephone")).sendKeys("082555");
	          driver.findElement(By.className("btn-success")).click();
	          //ensure for users are added to the list
	          System.out.println("User"+ i + "added");
	          
          }
        }  
        else {
        	System.out.println("No you are not on the User List Table");
        }
        //pause for a second and close the browser.
		Thread.sleep(5000);
 		endSession();
	}

	public static WebElement getElement(final By locator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            @Override
			public WebElement apply(WebDriver arg0) {
				return arg0.findElement(locator);
			}
        });
        return element;
	}

	public static void endSession() {
		driver.close();
		driver.quit();
	}

}