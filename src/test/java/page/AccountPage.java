package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPage extends AbstractPage
{
	private final Logger logger = LogManager.getRootLogger();
	private final String PAGE_URL = "https://salomon.ru/account/#/";

	private final By accountEmailInfo = By.xpath("//div[@class='account-main']/section/p[3]");

	@FindBy(xpath = "//a[@href='/?logout=yes']")
	WebElement logout;

	public AccountPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	public AccountPage openPage()
	{
		driver.get(PAGE_URL);
		logger.info("Login page opened");
		return this;
	}

	public String getUserEmail()
	{
		WebElement linkLoggedInUser = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
				.until(ExpectedConditions.presenceOfElementLocated(accountEmailInfo));
		return linkLoggedInUser.getText();
	}

	public MainPage logout(){
		new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
				.until(ExpectedConditions.elementToBeClickable(logout));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

		logout.click();

		return new MainPage(driver);
	}

}
