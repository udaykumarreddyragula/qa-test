Solution for Problem Statement 1:


Steps followed to Kubernetes Deployment:

Project Repository: https://github.com/Vengatesh-m/qa-test 

I have cloned the folder from the above repo to my local space and followed the below steps.


Steps to deploy the pods and to verify the integration between frontend and backend services :
    
Step1:
        created the images locally using the commangs post traversing to the respective directories:

             docker build -t backend backend
             docker build -t frontend frontend
             
Step2: Pushed the images to the docker hub repository:  udaykumarreddy046/udaykumarreddyragula

Images are available @ https://hub.docker.com/r/udaykumarreddy046/udaykumarreddyragula/tags

    The commands used in this step are:
    
         docker tag frontend udaykumarreddy046/udaykumarreddyragula:frontend
         docker push  udaykumarreddy046/udaykumarreddyragula:frontend
         
        docker tag backend udaykumarreddy046/udaykumarreddyragula:backend
        docker push  udaykumarreddy046/udaykumarreddyragula:backend
    
Step3: Made changes to the YAML files as follows which are need to pull the images from docker hub repository:
        
        Changes made to the frontend-deployment.yaml:
        
    containers:
      - name: frontend
        image: udaykumarreddy046/udaykumarreddyragula:frontend
        ports:
        - containerPort: 8080
        imagePullPolicy: IfNotPresent
        
       Changes made to the backend-deployment.yaml:
       
  containers:
      - name: backend
        image: udaykumarreddy046/udaykumarreddyragula:backend
        ports:
        - containerPort: 3000
        imagePullPolicy: Always
        
        
Step4: Brought up the pods using the below commands

          kubectl apply -f frontend-deployment.yaml
          kubectl apply -f backend-deployment.yaml
          kubectl get pods 

Step5: Checked the frontend service Details to get the details of port exposed.

kubectl get service frontend-service

NAME               TYPE           CLUSTER-IP      EXTERNAL-IP   PORT(S)        AGE
frontend-service   LoadBalancer   10.110.130.77   localhost     80:32227/TCP   15h

Verification:

I have verified the integration and communication between the frontend and backend by checking URL "http://localhost:80" on browser which displayed "Hello from the Backend!".

Automated Testing: I have wrote a selenium script with which I have automated the task to check that the frontend correctly displays the message returned by the backend which ensures the integration between the frontend and backend services.

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

Instructions to set up and run the automated test script:

>Import the TestScript project into any IDE which suppports java(I used Eclipse).
>Make sure that all the dependencies and jar files reuired are present in the project libraries.
>Make sure the project is of type Maven.
>select VerifyIntegrationOfFEBEServicesTest.java script under default package in the src/maim/java directory.
>Select the file VerifyIntegrationOfFEBEServicesTest.java and choose run option-Run As- TestNG Test.
>Observe that the script getting passed in case only of Proper message from backend.

Solutions for Problem Statement 2:

I have chosen : 1. System Health Monitoring Script & 4. Application Health Checker and wrote basic bash/Shell script for these two tasks and shared them
