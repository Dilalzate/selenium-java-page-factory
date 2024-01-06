package selenium.java.page.factory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupLoginPage {

  protected WebDriver driver;

  @FindBy(css = "[data-qa='login-email']")
  private WebElement loginEmail;

  @FindBy(name = "password")
  private WebElement password;

  @FindBy(css = ".login-form > h2")
  private WebElement loginTitle;

  @FindBy(css = ".signup-form > h2")
  private WebElement signupTitle;

  @FindBy(name = "name")
  private WebElement userName;

  @FindBy(css = "[data-qa='signup-email']")
  private WebElement signupEmail;

  @FindBy(css = "[data-qa='signup-button']")
  private WebElement signupButton;

  @FindBy(css = "[data-qa='login-button']")
  private WebElement loginButton;

  @FindBy(css = "[name=password] + p")
  private WebElement errorMessage;

  public SignupLoginPage(final WebDriver driver) {

    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public SignupLoginPage typeLoginEmail(String email) {

    loginEmail.sendKeys(email);
    return this;
  }

  public SignupLoginPage typePassword(String pass) {

    password.sendKeys(pass);
    return this;
  }

  public SignupLoginPage typeName(String name) {

    userName.sendKeys(name);
    return this;
  }

  public SignupLoginPage typeSignupEmail(String email) {

    signupEmail.sendKeys(email);
    return this;
  }

  public HomePage submitLogin() {

    loginButton.click();
    return new HomePage(driver);
  }

  public HomePage submitSignup() {

    signupButton.click();
    return new HomePage(driver);
  }

  public String getLoginTitle() {
    return loginTitle.getText();
  }

  public String getSignupTitle() {
    return signupTitle.getText();
  }

  public String getLoginErrorMessage() {
    return errorMessage.getText();
  }

  public HomePage loginWithUser(String userName, String password) {

    typeLoginEmail(userName);
    typePassword(password);
    return submitLogin();
  }
}
