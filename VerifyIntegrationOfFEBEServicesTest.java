        import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.chrome.ChromeOptions;
        import org.openqa.selenium.edge.EdgeDriver;
        import org.openqa.selenium.edge.EdgeOptions;
        import org.testng.Assert;
        import org.testng.annotations.AfterTest;
        import org.testng.annotations.BeforeTest;
        import org.testng.annotations.Test;
        import com.fininfra.utilities.TestUtil;
        import io.github.bonigarcia.wdm.WebDriverManager;
        
        public class VerifyIntegrationOfFEBEServicesTest {  
             WebDriver driver;
             String browserName="edge";//can be chrome-Google Chrome browser or edge-Microsoft edge browser
             String ExpectedMsgfromBE ="Hello from the Backend!" ;
             
            @BeforeTest
            public void setUp() {
                if (browserName.equals("edge")) {
            
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions options = new EdgeOptions();
                    options.setAcceptInsecureCerts(true);
                    driver = new EdgeDriver(options);
                } else if (browserName.equals("chrome")) {
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.setAcceptInsecureCerts(true);
                    driver = new ChromeDriver(options);    
                }
                driver.manage().window().maximize();
                driver.manage().deleteAllCookies();
                // driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT);
                driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT);
                //driver.get("\"D:\\workCS\\localhost.html\"");
            }
        
            @Test
            public void verifyBackendMessage() {
                driver.get("http://localhost/"); 
        
                WebElement messageElement = driver.findElement(By.tagName("h1"));
                String actualMessage = messageElement.getText();
        
                Assert.assertEquals(actualMessage, "Hello from the Backend!");
            }
        
            @AfterTest
            public void tearDown() {
                driver.quit();
            }
            }
