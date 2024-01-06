package selenium.java.page.factory.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import selenium.java.page.factory.pages.HomePage;
import selenium.java.page.factory.pages.SignupLoginPage;

import java.time.Duration;

/*
The purpose of this class is to implement test cases from https://automationexercise.com/test_cases
using Page object model and Page Factory.
 */
public class LoginTest {


  private WebDriver driver;
  public static final String URL_AUTOMATION_EXERCISE = "https://automationexercise.com/";
  public static final String EMAIL = "dil-automation-test@mail.com";
  public static final String WRONG_EMAIL = "dil-automation-test1@mail.com";
  public static final String PASSWORD = "password";
  public static final String WRONG_PASSWORD = "***";
  public static final String LOGGED_IN_MESSAGE = "Logged in as Test Automation user";
  public static final String ERROR_MESSAGE = "Your email or password is incorrect!";

  @BeforeMethod
  public void setUp() {

    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.get(URL_AUTOMATION_EXERCISE);
  }

  @Test(description = "Test Case 2: Login User with correct email and password")
  public void testLoginSuccessfully() {

    HomePage homePage = new HomePage(driver);
    SignupLoginPage signupLoginPage = homePage.clickSignupLogin();
    signupLoginPage.loginWithUser(EMAIL, PASSWORD);

    Assert.assertEquals(homePage.getLoggedUserName(), LOGGED_IN_MESSAGE);
  }

  @Test(description = "Test Case 3: Login User with incorrect email and password",
    dataProvider = "LoginDataProvider")
  public void testLoginWrongCredentials(String email, String password) {

    HomePage homePage = new HomePage(driver);
    SignupLoginPage signupLoginPage = homePage.clickSignupLogin();
    signupLoginPage.loginWithUser(email, password);

    Assert.assertEquals(signupLoginPage.getLoginErrorMessage(), ERROR_MESSAGE);
  }

  @DataProvider(name = "LoginDataProvider")
  public Object[][] LoginData(){

    return new Object[][]
      {
        { WRONG_EMAIL, PASSWORD },
        { EMAIL, WRONG_PASSWORD},
        { WRONG_EMAIL, WRONG_PASSWORD }
      };
  }

  @AfterMethod
  public void tearDown() {
    driver.quit();
  }
}
