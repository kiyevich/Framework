package page;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends AbstractPage
{
	private final Logger logger = LogManager.getRootLogger();
	private final String PAGE_URL = "https://salomon.ru";

	@FindBy(xpath = "//button[@class='search__icon']")
	WebElement SearchButton;

	@FindBy(id = "header-search")
	WebElement SearchField;

	@FindBy(xpath = "//p[@class='item__title']")
	WebElement itemTitle;

	@FindBy(id = "footer-subscribe")
	WebElement mailingField;

	@FindBy(xpath = "//form[@class='subscribe__form']/div/div/p[@class='field__error']")
	WebElement mailingFieldError;

	@FindBy(xpath = "//a[@href='/auth/#']")
	WebElement loginButton;


	public MainPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	public MainPage openPage()
	{

		driver.get(PAGE_URL);
		logger.info("Main page opened");
		return this;
	}



    public MainPage typeSearchRequest(String searchRequest){

		new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
				.until(ExpectedConditions.elementToBeClickable(SearchButton))
				.click();

		new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
				.until(ExpectedConditions.elementToBeClickable(SearchField))
				.click();

		new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
				.until(ExpectedConditions.elementToBeClickable(SearchField))
				.sendKeys(searchRequest);
		return this;
	}

	public String getItemTitle(){
		String ResultItemTitle = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
				.until(ExpectedConditions.elementToBeClickable(itemTitle))
				.getText();
		return ResultItemTitle;
	}

	public CatalogPage searchItem(String searchRequest){
		this.typeSearchRequest(searchRequest);
		new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
				.until(ExpectedConditions.elementToBeClickable(SearchField))
				.sendKeys(Keys.ENTER);
		logger.info("An item search has been made");
		return new CatalogPage(driver);
	}

	public MainPage sendEmailToMailingField(User user)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");

		new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
				.until(ExpectedConditions.elementToBeClickable(mailingField))
				.sendKeys(user.getUsername(),Keys.ENTER);
		logger.info("Email has been sent to the mailing box");
		return this;
	}

	public String getMailingFieldError(){
		String resultMailingFieldError = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
				.until(ExpectedConditions.visibilityOf(mailingFieldError))
				.getText();
		return resultMailingFieldError;
	}

	public LoginPage pressLoginButton(){

		new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
				.until(ExpectedConditions.elementToBeClickable(loginButton))
				.click();
		logger.info("Login button pressed");
		return new LoginPage(driver);
	}

}
