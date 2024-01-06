package selenium.java.page.factory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

  protected WebDriver driver;

  @FindBy(css = ".shop-menu ul > li:nth-child(10)")
  private WebElement loggedUser;

  @FindBy(css = ".shop-menu ul > li:nth-child(4)")
  private WebElement signupLoginButton;

  public HomePage(final WebDriver driver) {

    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public String getLoggedUserName() {

    return loggedUser.getText();
  }

  public SignupLoginPage clickSignupLogin() {

    signupLoginButton.click();
    return new SignupLoginPage(driver);
  }
}
