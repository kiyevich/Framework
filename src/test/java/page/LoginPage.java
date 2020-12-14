package page;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends AbstractPage
{
	private final Logger logger = LogManager.getRootLogger();
	private final String PAGE_URL = "https://salomon.ru/auth/#/";

	@FindBy(id = "auth-email-2")
	private WebElement inputLogin;

	@FindBy(id = "auth-password")
	private WebElement inputPassword;

	@FindBy(xpath = "(//button[@class='button m-third'])[1]")
	private WebElement buttonSubmit;

	@FindBy(xpath = "(//div[@class='auth__form']/div[@class='field']/p[@class='field__error'])[1]")
	private WebElement emailFieldError;

	@FindBy(xpath = "(//div[@class='auth__form']/div[@class='field']/p[@class='field__error'])[2]")
	private WebElement passwordFieldError;

	@FindBy(xpath = "//p[@class='notify__text']")
	WebElement notifyText;

	@FindBy(xpath = "//h1")
	WebElement title;

	public LoginPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	public LoginPage openPage()
	{
		driver.get(PAGE_URL);
		logger.info("Login page opened");
		return this;
	}

	public AccountPage login(User user)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");

		inputLogin.sendKeys(user.getUsername());
		inputPassword.sendKeys(user.getPassword());
		buttonSubmit.click();
		logger.info("Login performed");
		return new AccountPage(driver);
	}

	public String getEmailFieldError()
	{
		String resultEmailFieldError = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
				.until(ExpectedConditions.visibilityOf(emailFieldError))
				.getText();

		return resultEmailFieldError;
	}

	public String getPasswordFieldError()
	{
		String resultPasswordFieldError = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
				.until(ExpectedConditions.visibilityOf(passwordFieldError))
				.getText();

		return resultPasswordFieldError;
	}

	public String getNotifyText(){
		String resultNotifyText = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
				.until(ExpectedConditions.visibilityOf(notifyText))
				.getText();
		return resultNotifyText;
	}

	public String getTile(){
		String resultTitle = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
				.until(ExpectedConditions.visibilityOf(title))
				.getText();
		return resultTitle;
	}
}
